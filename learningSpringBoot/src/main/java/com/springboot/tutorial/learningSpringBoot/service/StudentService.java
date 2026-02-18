package com.springboot.tutorial.learningSpringBoot.service;

import com.springboot.tutorial.learningSpringBoot.Dto.StudentDto;
import com.springboot.tutorial.learningSpringBoot.entity.Student;
import com.springboot.tutorial.learningSpringBoot.repository.studentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service marks this class as the business logic layer
// The Controller calls this, and this calls the Repository
@Service
public class StudentService {

    // @Autowired: Spring automatically creates and injects the repository here
    @Autowired
    private studentRepository studentRepo;

    // ===== GET ALL STUDENTS =====
    // Returns a simple List — no paging, just all students at once
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    // ===== GET STUDENT BY ID =====
    public Student getStudentById(Long id) {
        // findById returns an Optional — if student not found, throw an error
        return studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    // ===== CREATE NEW STUDENT =====
    public Student createStudent(StudentDto dto) {
        // Check if the email is already registered
        if (studentRepo.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already in use: " + dto.getEmail());
        }

        // Create a new Student object and copy data from the DTO into it
        Student student = new Student();
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setDob(dto.getDob());
        student.setPhone(dto.getPhone());

        // Save to database and return the saved object (now has an ID)
        return studentRepo.save(student);
    }

    // ===== UPDATE EXISTING STUDENT =====
    public Student updateStudent(Long id, StudentDto dto) {
        // First fetch the existing student (throws error if not found)
        Student existing = getStudentById(id);

        // Check if someone ELSE already has the new email
        if (studentRepo.existsByEmailAndIdNot(dto.getEmail(), id)) {
            throw new RuntimeException("Email already in use by another student: " + dto.getEmail());
        }

        // Update all fields with the new values
        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setEmail(dto.getEmail());
        existing.setDob(dto.getDob());
        existing.setPhone(dto.getPhone());

        // Save the updated record
        return studentRepo.save(existing);
    }

    // ===== DELETE STUDENT =====
    public void deleteStudent(Long id) {
        // Make sure the student exists before deleting
        if (!studentRepo.existsById(id)) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        studentRepo.deleteById(id);
    }
}