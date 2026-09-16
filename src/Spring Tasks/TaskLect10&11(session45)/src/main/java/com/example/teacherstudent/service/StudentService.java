package com.example.teacherstudent.service;

import com.example.teacherstudent.dto.StudentDTO;
import java.util.List;

public interface StudentService {
    List<StudentDTO> getAll();
    StudentDTO getById(Long id);
}
