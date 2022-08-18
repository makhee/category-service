package com.category.dto.category;

import com.category.dto.response.ResponseInterface;
import com.category.dto.service.CategoryInfoDto;
import lombok.Data;

public class GetCategoryDetailDto {
    @Data
    public static class ResponseDto implements ResponseInterface {
        private CategoryInfoDto categoryInfo;
    }
}
