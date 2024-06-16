package com.genshin.genshindmg.dto.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class VerCodeVo implements Serializable {
    private String vercode;
    private Long timeout;
}
