package com.onetag.twitter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "tweets")
public class Tweet {
    
    private static final DateTimeFormatter DATE_FORMAT = 
        DateTimeFormatter.ofPattern("HH:mm - dd/MM/yyyy z")
                        .withZone(ZoneId.of("GMT"));

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Author cannot be blank")
    @Size(max = 50, message = "Author name cannot exceed 50 characters")
    @Column(nullable = false, length = 50)
    private String author;

    @NotBlank(message = "Message cannot be blank")
    @Size(max = 280, message = "Message cannot exceed 280 characters")
    @Column(nullable = false, length = 280)
    private String message;

    @NotNull(message = "Date cannot be null")
    @Column(nullable = false)
    private Long date; // Stored in epoch format

    // Constructors
    public Tweet() {}

    public Tweet(String author, String message, Long date) {
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

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    // Utility method to get formatted date
    public String getFormattedDate() {
        if (date == null) {
            return null;
        }
        return DATE_FORMAT.format(Instant.ofEpochMilli(date));
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", message='" + message + '\'' +
                ", date=" + date +
                ", formattedDate='" + getFormattedDate() + '\'' +
                '}';
    }
}