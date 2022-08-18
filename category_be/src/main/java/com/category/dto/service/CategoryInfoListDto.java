package com.category.dto.service;

import lombok.Data;

import java.util.ArrayList;

@Data
public class CategoryInfoListDto {
    private int categoryId;
    private String categoryName;
    private int parentCategoryId;
    private int isDisplay;
    private int categorySeq;
    private ArrayList<CategoryInfoListDto> childCategoryList;
}
