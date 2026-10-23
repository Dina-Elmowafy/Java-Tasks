package com.example.userpost.controller;

import com.example.userpost.dto.*;
import com.example.userpost.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService service;
    public PostController(PostService service) { this.service = service; }
    @PostMapping public ResponseEntity<PostDto> create(@Valid @RequestBody PostDto dto) { return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto)); }
    @GetMapping("/{id}") public PostDto getById(@PathVariable Long id) { return service.getById(id); }
    @GetMapping public List<PostDto> getAll() { return service.getAll(); }
    @PutMapping("/{id}") public PostDto update(@PathVariable Long id, @Valid @RequestBody PostDto dto) { return service.update(id, dto); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id) { service.delete(id); }
    @GetMapping("/postsWithUsers") public List<PostDto> getAllWithUsers() { return service.getAllWithUsers(); }
    @GetMapping("/postWithUsers/{id}") public PostDto getWithUser(@PathVariable Long id) { return service.getWithUser(id); }
}
