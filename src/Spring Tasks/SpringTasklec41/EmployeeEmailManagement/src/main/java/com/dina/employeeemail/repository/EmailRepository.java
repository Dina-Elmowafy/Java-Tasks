package com.dina.employeeemail.repository;

import com.dina.employeeemail.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailRepository extends JpaRepository<Email, Long> {
    List<Email> findByName(String name);
    List<Email> findByNameIn(List<String> names);
    List<Email> findByContent(String content);
}
