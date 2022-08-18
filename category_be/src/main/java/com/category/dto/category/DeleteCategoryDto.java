package com.category.dto.category;

import com.category.dto.response.ResponseInterface;
import lombok.Data;

public class DeleteCategoryDto {
    @Data
    public static class ResponseDto implements ResponseInterface {
        private int deleteCount;
    }
}
