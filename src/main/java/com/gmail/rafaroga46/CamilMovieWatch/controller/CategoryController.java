package com.gmail.rafaroga46.CamilMovieWatch.controller;

import com.gmail.rafaroga46.CamilMovieWatch.controller.request.CategoryRequest;
import com.gmail.rafaroga46.CamilMovieWatch.controller.response.CategoryResponse;
import com.gmail.rafaroga46.CamilMovieWatch.entity.Category;
import com.gmail.rafaroga46.CamilMovieWatch.mapper.CategoryMapper;
import com.gmail.rafaroga46.CamilMovieWatch.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.util.stream.StreamSupport.stream;

@RestController()
@RequestMapping("/camilmoviewatch/category")
@Tag(name = "Category",
        description = "Recurso responsável pelo gerenciamento das categorias dos filmes.")
public class CategoryController {


    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "Listar todas categorias",
            description = "Método responsável por listar todas categorias.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "Todas Categorias em formato JSON.",
            content = @Content(schema = @Schema(implementation = CategoryResponse.class)))
    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> categories = categoryService.findAll()
                .stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();
        return ResponseEntity.ok(categories);
    }


    @Operation(summary = "Salvar Categoria",
            description = "Método responsável por salvar as categorias",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "Categoria salva com sucesso.",
            content = @Content(schema = @Schema(implementation = CategoryResponse.class)))
    @ApiResponse(responseCode = "400",description = "Bad request")
    @PostMapping()
    public ResponseEntity<CategoryResponse> saveCategory(@Valid @RequestBody CategoryRequest request) {
        Category savedCategory = categoryService.saveCategory(CategoryMapper.toCategory(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CategoryMapper.toCategoryResponse(savedCategory));

    }
    @Operation(summary = "Buscar categoria por id",
            description = "Método responsável por buscar categorias por id.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200",description = "Retorna a categoria por id",
            content = @Content(schema = @Schema(implementation = CategoryResponse.class)))
    @ApiResponse(responseCode = "404",description = "Categoria não encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getByCategoryId(@Valid @PathVariable Long id) {
        return categoryService.findById(id)
                .map(category -> ResponseEntity.ok(CategoryMapper.toCategoryResponse(category)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deletar categoria por id",
            description = "Método responsável por deletar categoria por id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "204",
            description = "Categoria deletada com sucesso.",content = @Content())
    @ApiResponse(responseCode = "404",description = "Categoria não encontrada",content = @Content())
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();


    }


}
