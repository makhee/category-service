package com.category.service;

import com.category.dto.service.CategoryInfoDto;
import com.category.dto.service.CategoryInfoListDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface CategoryService {
    // 카테고리 생성
    CategoryInfoDto createCategory(String categoryName, int categorySeq, int isDisplay, int parentCategoryId);

    // 카테고리 수정
    CategoryInfoDto updateCategory(int categoryId, String categoryName, int categorySeq, int isDisplay, int parentCategoryId);

    // 카테고리 순서 수정
    void updateCategorySeq(int parentCategoryId, int categorySeq);

    // 카테고리 삭제
    int deleteCategory(int categoryId);

    // 카테고리 그룹 조회 By parentCategoryId
    ArrayList<CategoryInfoListDto> getCategoryList(int categoryId);

    // 카테고리 단일 조회 By categoryId
    CategoryInfoDto getCategoryByCategoryId(int categoryId);
}
