package com.genshin.genshindmg.dto.vo.artifact;

import lombok.Data;

import java.io.Serializable;


@Data
public class AttributeVo implements Serializable {
    private String name;
    private Double value;
}
