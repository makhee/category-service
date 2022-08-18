package com.category.dto.entity;

import lombok.Data;

@Data
public class CategoryEntityDto {
    private int categoryId;

    private String categoryName;

    private int categorySeq;

    private int parentCategoryId;

    private int isDisplay;

    private String updatedAt;

    private String createdAt;
}
