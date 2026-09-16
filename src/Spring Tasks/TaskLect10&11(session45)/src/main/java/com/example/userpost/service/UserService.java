package com.example.userpost.service;

import com.example.userpost.dto.*;
import java.util.List;

public interface UserService {
    UserDto create(UserDto dto);
    UserDto getById(Long id);
    List<UserDto> getAll();
    UserDto update(Long id, UserDto dto);
    void delete(Long id);
    List<PostDto> getPosts(Long id);
    List<UserDto> getAllWithPosts();
    UserDto getWithPosts(Long id);
}
