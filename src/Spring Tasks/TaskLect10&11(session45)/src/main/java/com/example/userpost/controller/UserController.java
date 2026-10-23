package com.example.userpost.controller;

import com.example.userpost.dto.*;
import com.example.userpost.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;
    public UserController(UserService service) { this.service = service; }
    @PostMapping public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto dto) { return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto)); }
    @GetMapping("/{id}") public UserDto getById(@PathVariable Long id) { return service.getById(id); }
    @GetMapping public List<UserDto> getAll() { return service.getAll(); }
    @PutMapping("/{id}") public UserDto update(@PathVariable Long id, @Valid @RequestBody UserDto dto) { return service.update(id, dto); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id) { service.delete(id); }
    @GetMapping("/{id}/posts") public List<PostDto> getPosts(@PathVariable Long id) { return service.getPosts(id); }
    @GetMapping("/usersWithPost") public List<UserDto> getAllWithPosts() { return service.getAllWithPosts(); }
    @GetMapping("/userWithPost/{id}") public UserDto getWithPosts(@PathVariable Long id) { return service.getWithPosts(id); }
}
