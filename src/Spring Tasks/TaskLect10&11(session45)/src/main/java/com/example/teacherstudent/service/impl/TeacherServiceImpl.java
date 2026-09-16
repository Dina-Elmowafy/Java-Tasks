package com.example.teacherstudent.service.impl;

import com.example.teacherstudent.dto.StudentDTO;
import com.example.teacherstudent.dto.TeacherDTO;
import com.example.teacherstudent.exception.ResourceNotFoundException;
import com.example.teacherstudent.model.Teacher;
import com.example.teacherstudent.repository.TeacherRepository;
import com.example.teacherstudent.service.TeacherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    public TeacherServiceImpl(TeacherRepository teacherRepository) { this.teacherRepository = teacherRepository; }
    @Override public List<TeacherDTO> getAll() { return teacherRepository.findAllWithStudents().stream().map(this::toDTO).toList(); }
    @Override public TeacherDTO getById(Long id) {
        Teacher teacher = teacherRepository.findByIdWithStudents(id).orElseThrow(() -> new ResourceNotFoundException("Teacher with id " + id + " was not found"));
        return toDTO(teacher);
    }
    private TeacherDTO toDTO(Teacher teacher) {
        List<StudentDTO> students = teacher.getStudents().stream().map(student -> new StudentDTO(student.getId(), student.getName())).toList();
        return new TeacherDTO(teacher.getId(), teacher.getName(), students);
    }
}
