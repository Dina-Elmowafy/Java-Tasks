package com.example.teacherstudent.service;

import com.example.teacherstudent.dto.TeacherDTO;
import java.util.List;

public interface TeacherService {
    List<TeacherDTO> getAll();
    TeacherDTO getById(Long id);
}
