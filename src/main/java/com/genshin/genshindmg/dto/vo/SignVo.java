package com.genshin.genshindmg.dto.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SignVo implements Serializable {
    private String userName; // 用户名
    private String password; // 密码
}
