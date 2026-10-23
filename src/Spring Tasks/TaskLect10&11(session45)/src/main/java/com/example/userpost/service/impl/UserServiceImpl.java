package com.example.userpost.service.impl;

import com.example.userpost.dto.*;
import com.example.userpost.exception.ResourceNotFoundException;
import com.example.userpost.mapper.*;
import com.example.userpost.model.User;
import com.example.userpost.repository.UserRepository;
import com.example.userpost.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service @Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository users; private final UserMapper userMapper; private final PostMapper postMapper;
    public UserServiceImpl(UserRepository users, UserMapper userMapper, PostMapper postMapper) { this.users = users; this.userMapper = userMapper; this.postMapper = postMapper; }
    @Transactional public UserDto create(UserDto dto) { return userMapper.toDto(users.save(userMapper.toEntity(dto))); }
    public UserDto getById(Long id) { return userMapper.toDto(find(id)); }
    public List<UserDto> getAll() { return users.findAll().stream().map(userMapper::toDto).toList(); }
    @Transactional public UserDto update(Long id, UserDto dto) { User user = find(id); userMapper.updateEntity(dto, user); return userMapper.toDto(user); }
    @Transactional public void delete(Long id) { users.delete(find(id)); }
    public List<PostDto> getPosts(Long id) { return findWithPosts(id).getPosts().stream().map(postMapper::toDto).toList(); }
    public List<UserDto> getAllWithPosts() { return users.findAllWithPosts().stream().map(this::withPosts).toList(); }
    public UserDto getWithPosts(Long id) { return withPosts(findWithPosts(id)); }
    private User find(Long id) { return users.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " was not found")); }
    private User findWithPosts(Long id) { return users.findByIdWithPosts(id).orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " was not found")); }
    private UserDto withPosts(User user) { return userMapper.toWithPosts(user, user.getPosts().stream().map(postMapper::toDto).toList()); }
}
