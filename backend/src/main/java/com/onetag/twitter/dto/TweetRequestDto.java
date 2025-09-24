package com.onetag.twitter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TweetRequestDto {

    @NotBlank(message = "Author cannot be blank")
    @Size(max = 50, message = "Author name cannot exceed 50 characters")
    private String author;

    @NotBlank(message = "Message cannot be blank")
    @Size(max = 280, message = "Message cannot exceed 280 characters")
    private String message;

    // Constructors
    public TweetRequestDto() {}

    public TweetRequestDto(String author, String message) {
        this.author = author;
        this.message = message;
    }

    // Getters and Setters
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "TweetRequestDto{" +
                "author='" + author + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}