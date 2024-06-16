package com.genshin.genshindmg.controller;

import com.genshin.genshindmg.controller.BaseController;
import com.genshin.genshindmg.dto.UserDTO;
import com.genshin.genshindmg.dto.vo.SignVo;
import com.genshin.genshindmg.entities.User;
import com.genshin.genshindmg.service.UserService;
import com.genshin.genshindmg.utils.JsonResult;
import com.genshin.genshindmg.utils.ResultEnum;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
//@RequestMapping("/user")
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
}
