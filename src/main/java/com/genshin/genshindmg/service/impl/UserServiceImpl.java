package com.genshin.genshindmg.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.genshin.genshindmg.dto.UserDTO;
import com.genshin.genshindmg.dto.vo.RegisterVo;
import com.genshin.genshindmg.dto.vo.SignVo;
import com.genshin.genshindmg.dto.vo.VerCodeVo;
import com.genshin.genshindmg.entities.Artifact;
import com.genshin.genshindmg.entities.User;
import com.genshin.genshindmg.mapper.ArtifactMapper;
import com.genshin.genshindmg.mapper.UserMapper;
import com.genshin.genshindmg.service.UserService;
import com.genshin.genshindmg.service.ex.ShSystemException;
import com.genshin.genshindmg.utils.Email;
import com.genshin.genshindmg.utils.EmailUtil;
import com.genshin.genshindmg.utils.ResultEnum;
import com.genshin.genshindmg.utils.ShaUtil;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author DELL
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2024-06-16 15:41:23
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    private final UserMapper userMapper;
    private final EmailUtil emailUtil;

    private final ArtifactMapper artifactMapper;

    @Value("${session-key.ver-code}")
    private String VER_CODE;

    @Value("${vercode.length}")
    private Integer length;

    @Value("${vercode.minutes}")
    private Integer minutes;

    @Value("${vercode.theme}")
    private String theme;

    @Value("${vercode.email-text}")
    private String emailText;


    @Autowired
    public UserServiceImpl(UserMapper u, EmailUtil e, ArtifactMapper a) {
        this.userMapper = u;
        this.emailUtil = e;
        this.artifactMapper = a;
    }

    @Override
    @Transactional
    public UserDTO sign(SignVo signVo) {
        LambdaQueryWrapper<User> q = new LambdaQueryWrapper<>();
        q.eq(User::getUserName, signVo.getUserName());
        // 数据库查询用户
        User user = userMapper.selectOne(q);
        if (user != null) {
            log.info("从数据库中查出来的用户为:{}", user);
            // 用户存在则检验用户名和密码是否匹配
            String pswd = ShaUtil.SHA256(signVo.getPassword());
            if (!user.getUserPassword().equalsIgnoreCase(pswd)) {
                // 用户名密码不匹配，抛出异常
                throw new ShSystemException(ResultEnum.PSWD_ERR);
            }
            // 用户名密码匹配，进行下一步
            UserDTO res = new UserDTO();
            res.setUserId(user.getUserId());
            res.setUserName(user.getUserName());
            log.info("返回的 UserDTO:{}", res);
            // 返回部分用户信息
            return res;
        }
        // 用户不存在则抛出异常
        throw new ShSystemException(ResultEnum.USER_NOT_FOUND);
    }

    @Override
    public UserDTO register(RegisterVo registerVo, HttpSession session) {
        LambdaQueryWrapper<User> q = new LambdaQueryWrapper<>();
        q.eq(User::getUserName, registerVo.getUserName());
        UserDTO res = new UserDTO();
        // 数据库查询用户
        User user = userMapper.selectOne(q);
        if (user != null) {
            // 用户存在则抛出异常
            log.info("从数据库中查出来的用户为:{}", user);
            throw new ShSystemException(ResultEnum.USER_FOUND);
        }
        // 用户不存在则判断验证码是否正确
        // 取出验证码
        VerCodeVo vcode = (VerCodeVo) session.getAttribute(VER_CODE);
        if (vcode == null) {
            // 验证码不存在
            throw new ShSystemException(ResultEnum.VC_NOT_FOUND);
        }
        if (!vcode.getVercode().equals(registerVo.getVerification())) {
            // 验证码不匹配，抛出异常
            // 此时应该将验证码抹除，以免重复验证
            session.removeAttribute(VER_CODE);
            throw new ShSystemException(ResultEnum.VC_ERR);
        }
        // 计算是否超时
        if (System.currentTimeMillis() > vcode.getTimeout()) {
            // 超时
            throw new ShSystemException((ResultEnum.VC_TIME_OUT));
        }
        // 验证成功
        // 此时应该将验证码抹除，以免重复验证
        session.removeAttribute(VER_CODE);
        // 保存用户数据到数据库
        User u = new User();
        u.setUserName(registerVo.getUserName());
        u.setMail(registerVo.getMail());
        u.setUserPassword(ShaUtil.SHA256(registerVo.getPassword()));
        if (1 != userMapper.insert(u)) {
            throw new ShSystemException(ResultEnum.INSERT);
        }
        // 返回部分用户信息
        res.setUserId(u.getUserId());
        res.setUserName(u.getUserName());
        log.info("返回的 UserDTO:{}", res);
        return res;
    }

    @Override
    public Boolean checkName(String userName) {
        LambdaQueryWrapper<User> q = new LambdaQueryWrapper<>();
        q.eq(User::getUserName, userName);
        User user = userMapper.selectOne(q);
        if (user != null) {
            // 用户名已存在
            throw new ShSystemException(ResultEnum.NAME_DUP);
        }
        return true;
    }

    @Override
    public Boolean checkMail(String email) {
        LambdaQueryWrapper<User> q = new LambdaQueryWrapper<>();
        q.eq(User::getMail, email);
        User user = userMapper.selectOne(q);
        if (user != null) {
            // 邮箱已存在
            throw new ShSystemException(ResultEnum.EMAIL_DUP);
        }
        return true;
    }

    @Override
    public Boolean getCode(String email, HttpSession session) {
        // 1. 首先检查该邮箱是否合法
        if (false) {
            // 2. 不符合，返回false
            return false;
        }
        // 上面的检测功能暂时没做，不影响正确逻辑
        ///////////////////////////////////////
        // 3. 符合，生成验证码
        String vcode = RandomUtil.randomNumbers(length);
        // 4. 保存验证码到session
        VerCodeVo vv = new VerCodeVo();
        vv.setVercode(vcode);
        vv.setTimeout(System.currentTimeMillis() + minutes * 60 * 1000);
        session.setAttribute(VER_CODE, vv);
        // 5. 发送验证码
        sendEmail(email, vcode);
        return true;
    }

    @Override
    @Transactional
    public Boolean logout(String userName) {
        LambdaQueryWrapper<User> q = new LambdaQueryWrapper<>();
        q.eq(User::getUserName, userName);
        Integer uid = userMapper.selectOne(q).getUserId();
        LambdaQueryWrapper<Artifact> qq = new LambdaQueryWrapper<>();
        qq.eq(Artifact::getUserId, uid);
        artifactMapper.delete(qq);
        return userMapper.delete(q) == 1;
    }

    // 抽取出的发送验证码的功能
    private void sendEmail(String email, String vcode) {
        Email mail = new Email();
        mail.setEmailTheme(theme);
        emailText = emailText.replace("@{theme}", theme);
        emailText = emailText.replace("@{vcode}", vcode);
        emailText = emailText.replace("@{minutes}", String.valueOf(minutes));
        mail.setEmailText(emailText);
        mail.setReceiver(email);
        try {
            emailUtil.sendSimpleMail(mail);
        } catch (Exception e) {
            throw new ShSystemException(ResultEnum.EMAIL);
        }
    }

}




