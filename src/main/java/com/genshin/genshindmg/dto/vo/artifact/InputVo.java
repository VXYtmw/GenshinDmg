package com.genshin.genshindmg.dto.vo.artifact;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class InputVo implements Serializable {
    private String userName;
    private String suit;
    private String part;
    private List<AttributeVo> attributes;

}


