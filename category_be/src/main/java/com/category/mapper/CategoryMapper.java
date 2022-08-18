package com.category.mapper;

import com.category.dto.entity.CategoryEntityDto;
import com.category.dto.service.CategoryInfoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface CategoryMapper {
    ArrayList<CategoryInfoDto> getCategoryListByParentCategoryId(Integer categoryId);

    CategoryInfoDto getCategoryByCategoryId(int categoryId);

    int createCategory(CategoryEntityDto entityDto);

    int updateCategory(CategoryEntityDto entityDto);

    void updateCategorySeq(int parentCategoryId, Integer categorySeq);

    int deleteCategory(int categoryId);
}
