package com.category.dto.service;

import lombok.Data;

@Data
public class CategoryInfoDto {
    private int categoryId;
    private String categoryName;
    private int parentCategoryId;
    private int isDisplay;
    private int categorySeq;
}
