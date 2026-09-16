package com.example.userpost.service.impl;

import com.example.userpost.dto.*;
import com.example.userpost.exception.ResourceNotFoundException;
import com.example.userpost.mapper.PostMapper;
import com.example.userpost.model.*;
import com.example.userpost.repository.*;
import com.example.userpost.service.PostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service @Transactional(readOnly = true)
public class PostServiceImpl implements PostService {
    private final PostRepository posts; private final UserRepository users; private final PostMapper mapper;
    public PostServiceImpl(PostRepository posts, UserRepository users, PostMapper mapper) { this.posts = posts; this.users = users; this.mapper = mapper; }
    @Transactional public PostDto create(PostDto dto) { Post post = mapper.toEntity(dto); setUser(dto.getUserId(), post); return mapper.toDto(posts.save(post)); }
    public PostDto getById(Long id) { return mapper.toDto(find(id)); }
    public List<PostDto> getAll() { return posts.findAll().stream().map(mapper::toDto).toList(); }
    @Transactional public PostDto update(Long id, PostDto dto) { Post post = find(id); mapper.updateEntity(dto, post); setUser(dto.getUserId(), post); return mapper.toDto(post); }
    @Transactional public void delete(Long id) { posts.delete(find(id)); }
    public List<PostDto> getAllWithUsers() { return posts.findAllWithUser().stream().map(mapper::toWithUser).toList(); }
    public PostDto getWithUser(Long id) { return mapper.toWithUser(posts.findByIdWithUser(id).orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " was not found"))); }
    private Post find(Long id) { return posts.findByIdWithUser(id).orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " was not found")); }
    private void setUser(Long userId, Post post) {
        if (userId == null) { post.setUser(null); return; }
        User user = users.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " was not found"));
        post.setUser(user);
    }
}
