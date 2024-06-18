package com.genshin.genshindmg.dto.vo.artifact;

import lombok.Data;

import javax.print.attribute.Attribute;
import java.io.Serializable;
import java.util.List;

@Data
public class InputVo implements Serializable {
    private String userName;
    private String set;
    private String slot;
    private StatVo mainStat;
    private List<StatVo> subStat;
}


