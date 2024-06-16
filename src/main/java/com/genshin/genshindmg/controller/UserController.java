package com.genshin.genshindmg.controller;

import com.genshin.genshindmg.dto.UserDTO;
import com.genshin.genshindmg.dto.vo.RegisterVo;
import com.genshin.genshindmg.dto.vo.SignVo;
import com.genshin.genshindmg.service.UserService;
import com.genshin.genshindmg.utils.JsonResult;
import com.genshin.genshindmg.utils.ResultEnum;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController extends BaseController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 用户名密码登录
    @PostMapping("/login")
    public JsonResult<UserDTO> signin(@RequestBody SignVo signVo) {
        try {
            UserDTO res = userService.sign(signVo);
            return new JsonResult<>(ResultEnum.OK, res);
        } catch (Exception e) {
            log.error(e.getClass().getName(), e);
            return handleException(e);
        }
    }

    // 用户注册
    @PostMapping("/register")
    public JsonResult<UserDTO> signin(@RequestBody RegisterVo registerVo, HttpSession session) {
        try {
            UserDTO res = userService.register(registerVo, session);
            return new JsonResult<>(ResultEnum.OK, res);
        } catch (Exception e) {
            log.error(e.getClass().getName(), e);
            return handleException(e);
        }
    }

    //     验证用户名是否可用
    @GetMapping("/checkUserName")
    public JsonResult<Boolean> checkName(@RequestParam String userName) {
        try {
            Boolean res = userService.checkName(userName);
            return new JsonResult<>(ResultEnum.OK, res);
        } catch (Exception e) {
            log.error(e.getClass().getName(), e);
            return handleException(e);
        }
    }

    //     验证邮箱是否可用
    @GetMapping("/checkMail")
    public JsonResult<Boolean> checkMail(@RequestParam String email) {
        try {
            Boolean res = userService.checkName(email);
            return new JsonResult<>(ResultEnum.OK, res);
        } catch (Exception e) {
            log.error(e.getClass().getName(), e);
            return handleException(e);
        }
    }

    // 获取验证码
    @GetMapping("/verify")
    public JsonResult<Boolean> getCode(@RequestParam String mail, HttpSession session) {
        try {
            Boolean res = userService.getCode(mail, session);
            return new JsonResult<>(ResultEnum.OK, res);
        } catch (Exception e) {
            log.error(e.getClass().getName(), e);
            return handleException(e);
        }
    }

    // 删除用户
    @DeleteMapping("")
    public JsonResult<Boolean> logout(@RequestParam String userName) {
        try {
            Boolean res = userService.logout(userName);
            return new JsonResult<>(ResultEnum.OK, res);
        } catch (Exception e) {
            log.error(e.getClass().getName(), e);
            return handleException(e);
        }
    }


}
