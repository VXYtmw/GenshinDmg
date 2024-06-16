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
    private Integer suit;

    /**
     * 
     */
    private Integer part;

    /**
     * 
     */
    private Integer primaryAttribute;

    /**
     * 
     */
    private Double primaryValue;

    /**
     * 
     */
    private Integer secondaryAttribute1;

    /**
     * 
     */
    private Integer secondaryAttribute2;

    /**
     * 
     */
    private Integer secondaryAttribute3;

    /**
     * 
     */
    private Integer secondaryAttribute4;

    /**
     * 
     */
    private Double secondaryValue1;

    /**
     * 
     */
    private Double secondaryValue2;

    /**
     * 
     */
    private Double secondaryValue3;

    /**
     * 
     */
    private Double secondaryValue4;

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
            && (this.getSuit() == null ? other.getSuit() == null : this.getSuit().equals(other.getSuit()))
            && (this.getPart() == null ? other.getPart() == null : this.getPart().equals(other.getPart()))
            && (this.getPrimaryAttribute() == null ? other.getPrimaryAttribute() == null : this.getPrimaryAttribute().equals(other.getPrimaryAttribute()))
            && (this.getPrimaryValue() == null ? other.getPrimaryValue() == null : this.getPrimaryValue().equals(other.getPrimaryValue()))
            && (this.getSecondaryAttribute1() == null ? other.getSecondaryAttribute1() == null : this.getSecondaryAttribute1().equals(other.getSecondaryAttribute1()))
            && (this.getSecondaryAttribute2() == null ? other.getSecondaryAttribute2() == null : this.getSecondaryAttribute2().equals(other.getSecondaryAttribute2()))
            && (this.getSecondaryAttribute3() == null ? other.getSecondaryAttribute3() == null : this.getSecondaryAttribute3().equals(other.getSecondaryAttribute3()))
            && (this.getSecondaryAttribute4() == null ? other.getSecondaryAttribute4() == null : this.getSecondaryAttribute4().equals(other.getSecondaryAttribute4()))
            && (this.getSecondaryValue1() == null ? other.getSecondaryValue1() == null : this.getSecondaryValue1().equals(other.getSecondaryValue1()))
            && (this.getSecondaryValue2() == null ? other.getSecondaryValue2() == null : this.getSecondaryValue2().equals(other.getSecondaryValue2()))
            && (this.getSecondaryValue3() == null ? other.getSecondaryValue3() == null : this.getSecondaryValue3().equals(other.getSecondaryValue3()))
            && (this.getSecondaryValue4() == null ? other.getSecondaryValue4() == null : this.getSecondaryValue4().equals(other.getSecondaryValue4()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getArtifactId() == null) ? 0 : getArtifactId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getSuit() == null) ? 0 : getSuit().hashCode());
        result = prime * result + ((getPart() == null) ? 0 : getPart().hashCode());
        result = prime * result + ((getPrimaryAttribute() == null) ? 0 : getPrimaryAttribute().hashCode());
        result = prime * result + ((getPrimaryValue() == null) ? 0 : getPrimaryValue().hashCode());
        result = prime * result + ((getSecondaryAttribute1() == null) ? 0 : getSecondaryAttribute1().hashCode());
        result = prime * result + ((getSecondaryAttribute2() == null) ? 0 : getSecondaryAttribute2().hashCode());
        result = prime * result + ((getSecondaryAttribute3() == null) ? 0 : getSecondaryAttribute3().hashCode());
        result = prime * result + ((getSecondaryAttribute4() == null) ? 0 : getSecondaryAttribute4().hashCode());
        result = prime * result + ((getSecondaryValue1() == null) ? 0 : getSecondaryValue1().hashCode());
        result = prime * result + ((getSecondaryValue2() == null) ? 0 : getSecondaryValue2().hashCode());
        result = prime * result + ((getSecondaryValue3() == null) ? 0 : getSecondaryValue3().hashCode());
        result = prime * result + ((getSecondaryValue4() == null) ? 0 : getSecondaryValue4().hashCode());
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
        sb.append(", suit=").append(suit);
        sb.append(", part=").append(part);
        sb.append(", primaryAttribute=").append(primaryAttribute);
        sb.append(", primaryValue=").append(primaryValue);
        sb.append(", secondaryAttribute1=").append(secondaryAttribute1);
        sb.append(", secondaryAttribute2=").append(secondaryAttribute2);
        sb.append(", secondaryAttribute3=").append(secondaryAttribute3);
        sb.append(", secondaryAttribute4=").append(secondaryAttribute4);
        sb.append(", secondaryValue1=").append(secondaryValue1);
        sb.append(", secondaryValue2=").append(secondaryValue2);
        sb.append(", secondaryValue3=").append(secondaryValue3);
        sb.append(", secondaryValue4=").append(secondaryValue4);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}