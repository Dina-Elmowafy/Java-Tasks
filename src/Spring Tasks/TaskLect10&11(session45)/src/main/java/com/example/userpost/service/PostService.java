package com.example.userpost.service;

import com.example.userpost.dto.*;
import java.util.List;

public interface PostService {
    PostDto create(PostDto dto);
    PostDto getById(Long id);
    List<PostDto> getAll();
    PostDto update(Long id, PostDto dto);
    void delete(Long id);
    List<PostDto> getAllWithUsers();
    PostDto getWithUser(Long id);
}
