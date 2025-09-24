package com.onetag.twitter.dto;

public class TweetResponseDto {
    
    private Long id;
    private String author;
    private String message;
    private String date; // Formatted date string

    // Constructors
    public TweetResponseDto() {}

    public TweetResponseDto(Long id, String author, String message, String date) {
        this.id = id;
        this.author = author;
        this.message = message;
        this.date = date;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TweetResponseDto{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", message='" + message + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}