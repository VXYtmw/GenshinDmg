package com.genshin.genshindmg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.genshin.genshindmg.dto.UserDTO;
import com.genshin.genshindmg.dto.vo.SignVo;
import com.genshin.genshindmg.entities.User;
import com.genshin.genshindmg.service.UserService;
import com.genshin.genshindmg.mapper.UserMapper;
import com.genshin.genshindmg.service.ex.ShSystemException;
import com.genshin.genshindmg.utils.EmailUtil;
import com.genshin.genshindmg.utils.ResultEnum;
import com.genshin.genshindmg.utils.ShaUtil;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    implements UserService{
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper u) {
        this.userMapper = u;
    }

    @Override
    @Transactional
    public UserDTO sign(SignVo signVo) {
        LambdaQueryWrapper<User> q = new LambdaQueryWrapper<>();
        User user;
        UserDTO res = new UserDTO();
        // 数据库查询用户
        user = userMapper.selectOne(q);
        log.info("从数据库中查出来的用户为:{}", user);
        if (user != null) {
            // 用户存在则检验用户名和密码是否匹配
            String pswd = ShaUtil.SHA256(signVo.getPassword());
            log.info(pswd);
            if (!user.getUserPassword().equalsIgnoreCase(pswd)) {
                // 用户名密码不匹配，抛出异常
                throw new ShSystemException(ResultEnum.PSWD_ERR);
            }
            // 用户名密码匹配，进行下一步
            res.setUserId(user.getUserId());
            res.setUserName(user.getUserName());
            log.info("返回的 UserDTO:{}", res);
            // 返回部分用户信息
            return res;
        }
        // 用户不存在则抛出异常
        throw new ShSystemException(ResultEnum.USER_NOT_FOUND);
    }

}




