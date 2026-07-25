package com.student.university.dto;

import java.util.List;

public class UniversityDtos {

    public record StudentInfo(Long id, String name, String email) {
    }

    public record InstructorInfo(Long id, String name, String email) {
    }

    public record CourseInfo(Long id, String title, String description, InstructorInfo instructor) {
    }

    public record CourseWithStudents(Long id, String title, String description,
                                     List<StudentInfo> students) {
    }

    public record StudentDetails(Long id, String name, String email, List<CourseInfo> courses) {
    }

    public record CourseDetails(Long id, String title, String description,
                                InstructorInfo instructor, List<StudentInfo> students) {
    }

    public record InstructorDetails(Long id, String name, String email,
                                    List<CourseWithStudents> courses) {
    }
}
