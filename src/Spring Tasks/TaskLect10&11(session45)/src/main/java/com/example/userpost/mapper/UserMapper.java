package com.example.userpost.mapper;

import com.example.userpost.dto.*;
import com.example.userpost.model.User;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserMapper {
    public User toEntity(UserDto dto) { User user = new User(); updateEntity(dto, user); return user; }
    public void updateEntity(UserDto dto, User user) { user.setName(dto.getName()); user.setAge(dto.getAge()); user.setPassword(dto.getPassword()); }
    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId()); dto.setName(user.getName()); dto.setAge(user.getAge());
        return dto;
    }
    public UserDto toWithPosts(User user, List<PostDto> posts) { UserDto dto = toDto(user); dto.setPosts(posts); return dto; }
}
