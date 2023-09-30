package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;
    
    @NotBlank(message = "Tag must not be blank")
    private String tag;
    
    @NotBlank(message = "Title must not be blank")
    private String title;
    
    private String image;
    
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    
    @NotNull(message = "publication Year must not be null")
    private Integer publicationYear;
    

    private String ISBN;
    
    @NotBlank(message = "Description must not be blank")
    private String description;
    

}