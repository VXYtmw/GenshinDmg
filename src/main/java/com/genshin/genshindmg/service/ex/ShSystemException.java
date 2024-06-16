package com.genshin.genshindmg.service.ex;

import com.genshin.genshindmg.utils.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ShSystemException extends RuntimeException {
    private Integer state;
    private String message;

    public ShSystemException(ResultEnum resultEnum) {
        this.state = resultEnum.getState();
        this.message = resultEnum.getMessage();
    }
}
