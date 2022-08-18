package com.category.dto.response;

import lombok.Data;

@Data
public class CommonResponseDto {
    private String code;

    private String message;

    private ResponseInterface data;

    public CommonResponseDto(String code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public CommonResponseDto() {
        this.code = "success";
        this.message = "성공";
        this.data = null;
    }

    public <T extends ResponseInterface> CommonResponseDto(T data) {
        this.code = "success";
        this.message = "성공";
        this.data = data;
    }

    public <T extends ResponseInterface> CommonResponseDto(String message, T data) {
        this.code = "success";
        this.message = message;
        this.data = data;
    }

    public <T extends ResponseInterface> CommonResponseDto(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
