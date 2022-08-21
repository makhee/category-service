package com.category.dto.category;

import com.category.dto.response.ResponseInterface;
import com.category.dto.service.CategoryInfoListDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;

public class GetCategoryListDto {
    @Data
    public static class RequestDto {
        @Schema(description = "CATEGORY_ID", example = "1")
        private int categoryId;
    }

    @Data
    public static class ResponseDto implements ResponseInterface {
        private ArrayList<CategoryInfoListDto> list;
    }
}
