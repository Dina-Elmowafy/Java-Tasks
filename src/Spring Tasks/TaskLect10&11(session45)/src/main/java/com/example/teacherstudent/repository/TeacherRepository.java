package com.example.teacherstudent.repository;

import com.example.teacherstudent.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Query("select distinct t from Teacher t left join fetch t.students")
    List<Teacher> findAllWithStudents();
    @Query("select t from Teacher t left join fetch t.students where t.id = :id")
    Optional<Teacher> findByIdWithStudents(@Param("id") Long id);
}
