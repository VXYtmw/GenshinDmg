package com.genshin.genshindmg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.genshin.genshindmg.dto.UserDTO;
import com.genshin.genshindmg.dto.vo.RegisterVo;
import com.genshin.genshindmg.dto.vo.SignVo;
import com.genshin.genshindmg.entities.User;
import jakarta.servlet.http.HttpSession;

/**
 * @author DELL
 * @description 针对表【user】的数据库操作Service
 * @createDate 2024-06-16 15:41:23
 */
public interface UserService extends IService<User> {
    // 用户登录
    UserDTO sign(SignVo signVo);

    // 用户注册
    UserDTO register(RegisterVo registerVo, HttpSession session);

    // 用户名检测
    Boolean checkName(String userName);

    // 用户名检测
    Boolean checkMail(String email);

    // 获取邮箱验证码
    Boolean getCode(String email, HttpSession session);

    // 删除用户
    Boolean logout(String userName);
}
