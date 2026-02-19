package com.springboot.tutorial.learningSpringBoot.service;

import com.springboot.tutorial.learningSpringBoot.Dto.StudentDto;
import com.springboot.tutorial.learningSpringBoot.entity.Student;
import com.springboot.tutorial.learningSpringBoot.repository.studentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentService {


    @Autowired
    private studentRepository studentRepo;


    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }


    public Student getStudentById(Long id) {

        return studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    public Student createStudent(StudentDto dto) {

        if (studentRepo.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already in use: " + dto.getEmail());
        }

        Student student = new Student();
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setDob(dto.getDob());
        student.setPhone(dto.getPhone());

        return studentRepo.save(student);
    }

    public Student updateStudent(Long id, StudentDto dto) {

        Student existing = getStudentById(id);

        if (studentRepo.existsByEmailAndIdNot(dto.getEmail(), id)) {
            throw new RuntimeException("Email already in use by another student: " + dto.getEmail());
        }

        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setEmail(dto.getEmail());
        existing.setDob(dto.getDob());
        existing.setPhone(dto.getPhone());

        return studentRepo.save(existing);
    }

    public void deleteStudent(Long id) {

        if (!studentRepo.existsById(id)) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        studentRepo.deleteById(id);
    }
}