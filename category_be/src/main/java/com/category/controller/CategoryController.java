package com.category.controller;

import com.category.dto.response.CommonResponseDto;
import com.category.dto.category.*;
import com.category.dto.service.CategoryInfoDto;
import com.category.dto.service.CategoryInfoListDto;
import com.category.exception.DatabaseException;
import com.category.exception.NoSearchResultException;
import com.category.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    /*
        카테고리 리스트 조회
     */
    @Operation(
            summary = "카테고리 목록 조회",
            description = "categoryId 값으로 해당 카테고리의 하위 목록을 조회 합니다. 없는경우 전체 목록을 조회합니다."
    )
    @GetMapping()
    public CommonResponseDto getCategoryList(GetCategoryListDto.RequestDto requestDto) {
        ArrayList<CategoryInfoListDto> categoryList = categoryService.getCategoryList(requestDto.getCategoryId());

        GetCategoryListDto.ResponseDto response = new GetCategoryListDto.ResponseDto();
        response.setList(categoryList);

        return new CommonResponseDto(response);
    }

    /*
        카테고리 상세 조회
     */
    @Operation(
            summary = "카테고리 상세 조회",
            description = "categoryId 값으로 해당 카테고리의 상세 정보를 조회 합니다."
    )
    @GetMapping("/{categoryId}")
    public CommonResponseDto getCategory(@Parameter(description = "CATEGORY_ID")
                                         @PathVariable("categoryId") int categoryId) {
        CategoryInfoDto categoryInfoDto = categoryService.getCategoryByCategoryId(categoryId);
        if (categoryInfoDto == null) throw new NoSearchResultException("카테고리 정보를 조회할 수 없습니다.");

        GetCategoryDetailDto.ResponseDto response = new GetCategoryDetailDto.ResponseDto();
        response.setCategoryInfo(categoryInfoDto);

        return new CommonResponseDto(response);
    }

    /*
        카테고리 생성
     */
    @Operation(
            summary = "카테고리 생성",
            description = "parentCategoryId 값의 하위 카테고리로 생성합니다. 0인 경우 최상위 카테고리로 생성합니다."
    )
    @Transactional
    @PostMapping()
    public CommonResponseDto createCategory(@Valid @RequestBody CreateCategoryDto.RequestDto requestDto) {
        if (requestDto.getParentCategoryId() != 0) {
            CategoryInfoDto categoryInfoDto = categoryService.getCategoryByCategoryId(requestDto.getParentCategoryId());
            if (categoryInfoDto == null) throw new NoSearchResultException("상위 카테고리 정보를 조회할 수 없습니다.");
        }
        categoryService.updateCategorySeq(requestDto.getParentCategoryId(), requestDto.getCategorySeq());

        CategoryInfoDto categoryInfoDto = categoryService.createCategory(
                requestDto.getCategoryName(),
                requestDto.getCategorySeq(),
                requestDto.getIsDisplay(),
                requestDto.getParentCategoryId()
        );
        if (categoryInfoDto == null) throw new DatabaseException("카테고리 생성에 실패하였습니다.");

        CreateCategoryDto.ResponseDto response = new CreateCategoryDto.ResponseDto();
        response.setCategoryInfo(categoryInfoDto);

        return new CommonResponseDto(response);
    }

    /*
        카테고리 수정
     */
    @Operation(
            summary = "카테고리 수정",
            description = "categoryId 카테고리를 수정합니다."
    )
    @Transactional
    @PutMapping("/{categoryId}")
    public CommonResponseDto updateCategory(@Parameter(description = "CATEGORY_ID")
                                            @PathVariable("categoryId") int categoryId,
                                            @Valid @RequestBody UpdateCategoryDto.RequestDto requestDto) {
        if (requestDto.getParentCategoryId() != 0) {
            CategoryInfoDto categoryInfoDto = categoryService.getCategoryByCategoryId(requestDto.getParentCategoryId());
            if (categoryInfoDto == null) throw new NoSearchResultException("상위 카테고리 정보를 조회할 수 없습니다.");
        }

        categoryService.updateCategorySeq(requestDto.getParentCategoryId(), requestDto.getCategorySeq());

        CategoryInfoDto categoryInfoDto = categoryService.updateCategory(
                categoryId,
                requestDto.getCategoryName(),
                requestDto.getCategorySeq(),
                requestDto.getIsDisplay(),
                requestDto.getParentCategoryId()
        );
        if (categoryInfoDto == null) throw new DatabaseException("카테고리 업데이트에 실패하였습니다.");

        UpdateCategoryDto.ResponseDto response = new UpdateCategoryDto.ResponseDto();
        response.setCategoryInfo(categoryInfoDto);

        return new CommonResponseDto(response);
    }

    /*
        카테고리 삭제
     */
    @Operation(
            summary = "카테고리 삭제",
            description = "categoryId 값으로 카테고리를 삭제합니다."
    )
    @DeleteMapping("/{categoryId}")
    public CommonResponseDto deleteCategory(@Parameter(description = "CATEGORY_ID")
                                            @PathVariable("categoryId") int categoryId) {
        CategoryInfoDto categoryInfoDto = categoryService.getCategoryByCategoryId(categoryId);
        if (categoryInfoDto == null) throw new NoSearchResultException("카테고리 정보를 조회할 수 없습니다.");

        int deleteCount = categoryService.deleteCategory(categoryId);

        DeleteCategoryDto.ResponseDto response = new DeleteCategoryDto.ResponseDto();
        response.setDeleteCount(deleteCount);

        return new CommonResponseDto(response);
    }
}
