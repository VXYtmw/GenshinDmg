package com.genshin.genshindmg.dto.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterVo implements Serializable {
    private String userName;
    private String password;
    private String mail;
    private String verification;
}
