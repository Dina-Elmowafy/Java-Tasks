package com.example.userpost.dto;

import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private Long id;
    @NotBlank @Size(min = 8, message = "name must contain at least 8 characters")
    private String name;
    @NotNull @Min(value = 18, message = "age must be 18 or older")
    private Integer age;
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).+$", message = "password must contain uppercase, lowercase, number, and special character")
    private String password;
    private List<PostDto> posts;
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getName() { return name; } public void setName(String name) { this.name = name; }
    public Integer getAge() { return age; } public void setAge(Integer age) { this.age = age; }
    public String getPassword() { return password; } public void setPassword(String password) { this.password = password; }
    public List<PostDto> getPosts() { return posts; } public void setPosts(List<PostDto> posts) { this.posts = posts; }
}
