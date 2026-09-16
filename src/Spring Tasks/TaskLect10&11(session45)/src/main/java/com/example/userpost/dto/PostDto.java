package com.example.userpost.dto;

import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostDto {
    private Long id;
    @NotBlank @Size(min = 20, message = "text must contain at least 20 characters")
    private String text;
    private String imagePath;
    private Long userId;
    private UserDto user;
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getText() { return text; } public void setText(String text) { this.text = text; }
    public String getImagePath() { return imagePath; } public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    public Long getUserId() { return userId; } public void setUserId(Long userId) { this.userId = userId; }
    public UserDto getUser() { return user; } public void setUser(UserDto user) { this.user = user; }
}
