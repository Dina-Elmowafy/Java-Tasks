package com.spring.boot.resturantbackend.controllers;

import com.spring.boot.resturantbackend.dto.ContactInfoDto;
import com.spring.boot.resturantbackend.services.ContactInfoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact-info")
public class ContactInfoController {
    private final ContactInfoService service;

    public ContactInfoController(ContactInfoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ContactInfoDto> create(@Valid @RequestBody ContactInfoDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createContactInfo(dto));
    }
}
