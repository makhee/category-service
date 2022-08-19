package com.category.service.impl;

import com.category.dto.entity.CategoryEntityDto;
import com.category.dto.service.CategoryInfoDto;
import com.category.dto.service.CategoryInfoListDto;
import com.category.mapper.CategoryMapper;
import com.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryInfoDto createCategory(String categoryName, int categorySeq, int isDisplay, int parentCategoryId) {
        CategoryEntityDto categoryEntityDto = new CategoryEntityDto();
        categoryEntityDto.setCategoryName(categoryName);
        categoryEntityDto.setIsDisplay(isDisplay);
        categoryEntityDto.setParentCategoryId(parentCategoryId);
        categoryEntityDto.setCategorySeq(categorySeq);

        int insertCount = categoryMapper.createCategory(categoryEntityDto);
        if (insertCount == 0) return null;

        CategoryInfoDto categoryInfoDto = new CategoryInfoDto();
        BeanUtils.copyProperties(categoryEntityDto, categoryInfoDto);

        return categoryInfoDto;
    }

    @Override
    public CategoryInfoDto updateCategory(int categoryId, String categoryName, int categorySeq, int isDisplay, int parentCategoryId) {
        CategoryEntityDto categoryEntityDto = new CategoryEntityDto();
        categoryEntityDto.setCategoryId(categoryId);
        categoryEntityDto.setCategoryName(categoryName);
        categoryEntityDto.setCategorySeq(categorySeq);
        categoryEntityDto.setIsDisplay(isDisplay);
        categoryEntityDto.setParentCategoryId(parentCategoryId);

        int updateCount = categoryMapper.updateCategory(categoryEntityDto);
        if (updateCount == 0) return null;

        CategoryInfoDto categoryInfoDto = new CategoryInfoDto();
        BeanUtils.copyProperties(categoryEntityDto, categoryInfoDto);

        return categoryInfoDto;
    }

    @Override
    public void updateCategorySeq(int parentCategoryId, int categorySeq) {
        categoryMapper.updateCategorySeq(parentCategoryId, categorySeq);
    }

    @Override
    public int deleteCategory(int categoryId) {
        return categoryMapper.deleteCategory(categoryId);
    }

    @Override
    public ArrayList<CategoryInfoListDto> getCategoryList(int categoryId) {
        ArrayList<CategoryInfoDto> categoryInfoList = categoryMapper.getCategoryListByParentCategoryId(categoryId);
        ArrayList<CategoryInfoListDto> categoryInfoListDto = new ArrayList<>();
        for (CategoryInfoDto categoryInfo : categoryInfoList) {
            CategoryInfoListDto childCategoryInfo = new CategoryInfoListDto();
            childCategoryInfo.setCategoryId(categoryInfo.getCategoryId());
            childCategoryInfo.setCategoryName(categoryInfo.getCategoryName());
            childCategoryInfo.setParentCategoryId(categoryInfo.getParentCategoryId());
            childCategoryInfo.setIsDisplay(categoryInfo.getIsDisplay());
            childCategoryInfo.setCategorySeq(categoryInfo.getCategorySeq());
            childCategoryInfo.setChildCategoryList(this.getCategoryList(categoryInfo.getCategoryId()));

            categoryInfoListDto.add(childCategoryInfo);
        }

        if (categoryInfoListDto.size() == 0) categoryInfoListDto = null;

        return categoryInfoListDto;
    }

    @Override
    public CategoryInfoDto getCategoryByCategoryId(int categoryId) {
        return categoryMapper.getCategoryByCategoryId(categoryId);
    }
}
