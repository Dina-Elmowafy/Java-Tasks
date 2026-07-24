package com.dina.employeeemail.controller;

import com.dina.employeeemail.dto.EmailRequestDto;
import com.dina.employeeemail.dto.EmailResponseDto;
import com.dina.employeeemail.service.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/emails")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<EmailResponseDto> create(@Valid @RequestBody EmailRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(emailService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmailResponseDto> update(@PathVariable Long id,
                                                   @Valid @RequestBody EmailRequestDto dto) {
        return ResponseEntity.ok(emailService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        emailService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<EmailResponseDto>> getAll() {
        return ResponseEntity.ok(emailService.getAll());
    }

    @GetMapping("/by-name")
    public ResponseEntity<List<EmailResponseDto>> getByName(@RequestParam String name) {
        return ResponseEntity.ok(emailService.getByName(name));
    }

    @GetMapping("/by-names")
    public ResponseEntity<List<EmailResponseDto>> getByNames(@RequestParam List<String> names) {
        return ResponseEntity.ok(emailService.getByNames(names));
    }

    @GetMapping("/by-content")
    public ResponseEntity<List<EmailResponseDto>> getByContent(@RequestParam String content) {
        return ResponseEntity.ok(emailService.getByContent(content));
    }
}
