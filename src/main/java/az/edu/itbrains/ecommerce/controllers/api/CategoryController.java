package az.edu.itbrains.ecommerce.controllers.api;


import az.edu.itbrains.ecommerce.dtos.category.CategoryCreateDto;
import az.edu.itbrains.ecommerce.dtos.category.CategoryDto;
import az.edu.itbrains.ecommerce.dtos.category.CategoryUpdateDto;
import az.edu.itbrains.ecommerce.payloads.results.Result;
import az.edu.itbrains.ecommerce.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "apiCategoryController")
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping
    public ResponseEntity<List<CategoryDto>> index(){
        List<CategoryDto> categoryDtoList = categoryService.getAllCategories();
        return new ResponseEntity<>(categoryDtoList, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<Result> create(@RequestBody CategoryCreateDto categoryCreateDto){
        Result result = categoryService.saveCategory(categoryCreateDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


    @GetMapping("/update/{id}")
    public ResponseEntity<CategoryUpdateDto> edit(@PathVariable Long id){
        CategoryUpdateDto categoryUpdateDto = categoryService.findUpdatedCategory(id);
        return new ResponseEntity<>(categoryUpdateDto, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Result> edit(@PathVariable Long id, @RequestBody CategoryUpdateDto categoryUpdateDto){
        Result result = categoryService.updateCategory(id, categoryUpdateDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }




    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
