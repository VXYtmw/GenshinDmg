package com.genshin.genshindmg.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName config
 */
@TableName(value ="config")
@Data
public class Config implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer id;

    /**
     * 
     */
    private Integer suitMax;

    /**
     * 
     */
    private Integer attributeMax;

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
        Config other = (Config) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSuitMax() == null ? other.getSuitMax() == null : this.getSuitMax().equals(other.getSuitMax()))
            && (this.getAttributeMax() == null ? other.getAttributeMax() == null : this.getAttributeMax().equals(other.getAttributeMax()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSuitMax() == null) ? 0 : getSuitMax().hashCode());
        result = prime * result + ((getAttributeMax() == null) ? 0 : getAttributeMax().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", suitMax=").append(suitMax);
        sb.append(", attributeMax=").append(attributeMax);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}