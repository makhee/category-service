package com.category.dto.enums;

import com.category.enums.EnumCode;
import lombok.Data;

@Data
public class EnumDto {
    private String code;

    private String name;

    public EnumDto(EnumCode enumCode) {
        this.code = enumCode.getCode();
        this.name = enumCode.getName();
    }
}