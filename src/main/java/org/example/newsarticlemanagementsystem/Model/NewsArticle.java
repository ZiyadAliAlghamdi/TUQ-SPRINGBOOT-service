package org.example.newsarticlemanagementsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class NewsArticle {
    @NotEmpty(message= "id cannot be empty")
    @Pattern(regexp = "^\\d+$", message = "id must be an integer")
    private String id;

    @NotEmpty(message = "title cannot be empty")
    @Size(max = 100, message = "Maximum length of title is 100 chars")
    private String title;

    @NotEmpty(message = "author cannot be null")
    @Size(min = 4, max = 20, message = "author name length should be between 4 and 20 chars")
    private String author;

    @NotEmpty(message = "content cannot be empty")
    @Size(max = 200, message = "content length must be 200 chars at least")
    private String content;

    @NotEmpty(message = "category cannot be empty")
    @Pattern(regexp = "\\b(politics|sports|technology)\\b")
    private String category;


    @NotEmpty(message = "message not empty")
    private String imageUrl;

    @NotNull(message = "isPublished value cannot be null")
    private boolean isPublished;    //false by default



    private LocalDate publishDate;
}
