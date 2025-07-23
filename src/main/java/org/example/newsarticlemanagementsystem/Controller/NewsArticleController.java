package org.example.newsarticlemanagementsystem.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.newsarticlemanagementsystem.Api.ApiResponse;
import org.example.newsarticlemanagementsystem.Model.NewsArticle;
import org.example.newsarticlemanagementsystem.Service.NewsArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/newsArticle")
@AllArgsConstructor
public class NewsArticleController {

    private final NewsArticleService newsArticleService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllArticles(){
        if (newsArticleService.getAllNewsArticles().isEmpty()){
            return ResponseEntity.status(404).body(new ApiResponse("empty array"));
        }
        return ResponseEntity.status(200).body(newsArticleService.getAllNewsArticles());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getArticleById(@PathVariable String id){
        if (newsArticleService.getArticleById(id)==null){
            return ResponseEntity.status(404).body(new ApiResponse("Article Not found"));
        }
        return ResponseEntity.status(200).body(newsArticleService.getArticleById(id));
    }

    @PostMapping("/post")
    public ResponseEntity<?> addArticle(@Valid @RequestBody NewsArticle newsArticle, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        newsArticleService.addArticle(newsArticle);
        return ResponseEntity.status(200).body(new ApiResponse("Article added"));
    }


    @PutMapping("/put/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable String id, @Valid @RequestBody NewsArticle newsArticle, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        if (newsArticleService.updateArticle(id, newsArticle)){
            return ResponseEntity.status(200).body(new ApiResponse("Article updated"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Article not found"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable String id){
        if (newsArticleService.deleteArticle(id)){
            return ResponseEntity.status(200).body(new ApiResponse("Article deleted"));
        }

        return ResponseEntity.status(404).body(new ApiResponse("article not found"));
    }


    @PutMapping("/publish/{id}")
    public ResponseEntity<?> publishArticle(@PathVariable String id){
        if (newsArticleService.publishArticle(id)){
            return ResponseEntity.status(200).body(new ApiResponse("Article publish"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Article not found"));
    }

    @GetMapping("/get/published")
    public ResponseEntity<?> getPublishedArticles(){
        if (newsArticleService.getPublishedArticles() == null){
            return ResponseEntity.status(404).body(new ApiResponse("No published articles"));
        }

        return ResponseEntity.status(200).body(newsArticleService.getPublishedArticles());
    }

    @GetMapping("/get/category")
    public ResponseEntity<?> getArticleByCategory(@RequestParam String categoryType){
        if (newsArticleService.getArticleByCategory(categoryType) == null){
            return ResponseEntity.status(404).body(new ApiResponse("items not found"));
        }
        return ResponseEntity.status(200).body(newsArticleService.getArticleByCategory(categoryType));
    }


}
