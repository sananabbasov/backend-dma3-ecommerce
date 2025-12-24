package az.edu.itbrains.ecommerce.services;

import az.edu.itbrains.ecommerce.dtos.category.CategoryCreateDto;
import az.edu.itbrains.ecommerce.dtos.category.CategoryDto;
import az.edu.itbrains.ecommerce.dtos.category.CategoryUpdateDto;
import az.edu.itbrains.ecommerce.models.Category;
import az.edu.itbrains.ecommerce.payloads.results.Result;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAllCategories();

    Result saveCategory(CategoryCreateDto categoryCreateDto);

    CategoryUpdateDto findUpdatedCategory(Long id);

    Result updateCategory(Long id, CategoryUpdateDto categoryUpdateDto);

    Category getCategoryById(Long categoryId);
}
