package com.category.dto.category;

import com.category.dto.response.ResponseInterface;
import com.category.dto.service.CategoryInfoDto;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UpdateCategoryDto {
    @Getter
    public static class RequestDto {
        @NotBlank(message = "카테고리명은 필수값입니다.")
        String categoryName;

        @NotBlank(message = "카테고리순서는 필수값입니다.")
        @Pattern(regexp = "[0-9]+", message = "카테고리 순서는 정수만 입력가능합니다.")
        private String categorySeq;

        @NotBlank(message = "상위 카테고리 정보는 필수값입니다.")
        @Pattern(regexp = "[0-9]+", message = "상위 카테고리 정보는 정수만 입력가능합니다.")
        private String parentCategoryId;

        private int isDisplay;

        public int getCategorySeq() {
            return Integer.parseInt(this.categorySeq);
        }

        public int getParentCategoryId() {
            return Integer.parseInt(this.parentCategoryId);
        }
    }

    @Data
    public static class ResponseDto implements ResponseInterface {
        private CategoryInfoDto categoryInfo;
    }
}
