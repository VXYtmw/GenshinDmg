package com.genshin.genshindmg.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName artifact
 */
@TableName(value ="artifact")
@Data
public class Artifact implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer artifactId;

    /**
     * 
     */
    private Integer userId;

    /**
     * 
     */
    private Integer artifactSet;

    /**
     * 
     */
    private Integer slot;

    /**
     * 
     */
    private Integer mainStat;

    /**
     * 
     */
    private Double mainValue;

    /**
     * 
     */
    private Integer secStat1;

    /**
     * 
     */
    private Integer secStat2;

    /**
     * 
     */
    private Integer secStat3;

    /**
     * 
     */
    private Integer secStat4;

    /**
     * 
     */
    private Double secValue1;

    /**
     * 
     */
    private Double secValue2;

    /**
     * 
     */
    private Double secValue3;

    /**
     * 
     */
    private Double secValue4;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Artifact other = (Artifact) that;
        return (this.getArtifactId() == null ? other.getArtifactId() == null : this.getArtifactId().equals(other.getArtifactId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getArtifactSet() == null ? other.getArtifactSet() == null : this.getArtifactSet().equals(other.getArtifactSet()))
            && (this.getSlot() == null ? other.getSlot() == null : this.getSlot().equals(other.getSlot()))
            && (this.getMainStat() == null ? other.getMainStat() == null : this.getMainStat().equals(other.getMainStat()))
            && (this.getMainValue() == null ? other.getMainValue() == null : this.getMainValue().equals(other.getMainValue()))
            && (this.getSecStat1() == null ? other.getSecStat1() == null : this.getSecStat1().equals(other.getSecStat1()))
            && (this.getSecStat2() == null ? other.getSecStat2() == null : this.getSecStat2().equals(other.getSecStat2()))
            && (this.getSecStat3() == null ? other.getSecStat3() == null : this.getSecStat3().equals(other.getSecStat3()))
            && (this.getSecStat4() == null ? other.getSecStat4() == null : this.getSecStat4().equals(other.getSecStat4()))
            && (this.getSecValue1() == null ? other.getSecValue1() == null : this.getSecValue1().equals(other.getSecValue1()))
            && (this.getSecValue2() == null ? other.getSecValue2() == null : this.getSecValue2().equals(other.getSecValue2()))
            && (this.getSecValue3() == null ? other.getSecValue3() == null : this.getSecValue3().equals(other.getSecValue3()))
            && (this.getSecValue4() == null ? other.getSecValue4() == null : this.getSecValue4().equals(other.getSecValue4()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getArtifactId() == null) ? 0 : getArtifactId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getArtifactSet() == null) ? 0 : getArtifactSet().hashCode());
        result = prime * result + ((getSlot() == null) ? 0 : getSlot().hashCode());
        result = prime * result + ((getMainStat() == null) ? 0 : getMainStat().hashCode());
        result = prime * result + ((getMainValue() == null) ? 0 : getMainValue().hashCode());
        result = prime * result + ((getSecStat1() == null) ? 0 : getSecStat1().hashCode());
        result = prime * result + ((getSecStat2() == null) ? 0 : getSecStat2().hashCode());
        result = prime * result + ((getSecStat3() == null) ? 0 : getSecStat3().hashCode());
        result = prime * result + ((getSecStat4() == null) ? 0 : getSecStat4().hashCode());
        result = prime * result + ((getSecValue1() == null) ? 0 : getSecValue1().hashCode());
        result = prime * result + ((getSecValue2() == null) ? 0 : getSecValue2().hashCode());
        result = prime * result + ((getSecValue3() == null) ? 0 : getSecValue3().hashCode());
        result = prime * result + ((getSecValue4() == null) ? 0 : getSecValue4().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", artifactId=").append(artifactId);
        sb.append(", userId=").append(userId);
        sb.append(", artifactSet=").append(artifactSet);
        sb.append(", slot=").append(slot);
        sb.append(", mainStat=").append(mainStat);
        sb.append(", mainValue=").append(mainValue);
        sb.append(", secStat1=").append(secStat1);
        sb.append(", secStat2=").append(secStat2);
        sb.append(", secStat3=").append(secStat3);
        sb.append(", secStat4=").append(secStat4);
        sb.append(", secValue1=").append(secValue1);
        sb.append(", secValue2=").append(secValue2);
        sb.append(", secValue3=").append(secValue3);
        sb.append(", secValue4=").append(secValue4);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}