package com.example.teacherstudent.repository;

import com.example.teacherstudent.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select distinct s from Student s left join fetch s.teachers")
    List<Student> findAllWithTeachers();
    @Query("select s from Student s left join fetch s.teachers where s.id = :id")
    Optional<Student> findByIdWithTeachers(@Param("id") Long id);
}
