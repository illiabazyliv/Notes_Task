package com.app.notes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.apache.catalina.User;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name ="notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title us required")
    @Column(nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public Note() {
    }
    public Note(String title, String content, Author author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Title us required") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Title us required") String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
