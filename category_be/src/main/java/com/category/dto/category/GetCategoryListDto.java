package com.category.dto.category;

import com.category.dto.response.ResponseInterface;
import com.category.dto.service.CategoryInfoListDto;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;

public class GetCategoryListDto {
    @Getter
    public static class RequestDto {
        private Integer categoryId;
    }

    @Data
    public static class ResponseDto implements ResponseInterface {
        private ArrayList<CategoryInfoListDto> list;
    }
}
