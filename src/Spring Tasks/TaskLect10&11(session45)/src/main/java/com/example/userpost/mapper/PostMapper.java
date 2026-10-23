package com.example.userpost.mapper;

import com.example.userpost.dto.*;
import com.example.userpost.model.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    private final UserMapper userMapper;
    public PostMapper(UserMapper userMapper) { this.userMapper = userMapper; }
    public Post toEntity(PostDto dto) {
        Post post = new Post();
        updateEntity(dto, post);
        return post;
    }
    public void updateEntity(PostDto dto, Post post) {
        post.setText(dto.getText());
        post.setImagePath(dto.getImagePath());
    }
    public PostDto toDto(Post post) {
        PostDto dto = new PostDto();
        dto.setId(post.getId()); dto.setText(post.getText()); dto.setImagePath(post.getImagePath());
        if (post.getUser() != null) dto.setUserId(post.getUser().getId());
        return dto;
    }
    public PostDto toWithUser(Post post) { PostDto dto = toDto(post); if (post.getUser() != null) dto.setUser(userMapper.toDto(post.getUser())); return dto; }
}
