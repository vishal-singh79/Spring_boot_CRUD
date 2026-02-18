package com.springboot.tutorial.learningSpringBoot.controller;

import com.springboot.tutorial.learningSpringBoot.Dto.StudentDto;
import com.springboot.tutorial.learningSpringBoot.entity.Student;
import com.springboot.tutorial.learningSpringBoot.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController: This class handles HTTP requests and returns JSON automatically
// @RequestMapping: All endpoints in this class start with /api/students
@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // ===== GET ALL STUDENTS =====
    // URL: GET http://localhost:8080/api/students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students); // Returns 200 OK with the list
    }

    // ===== GET STUDENT BY ID =====
    // URL: GET http://localhost:8080/api/students/1
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        // @PathVariable picks up the {id} value from the URL
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student); // 200 OK
    }

    // ===== CREATE NEW STUDENT =====
    // URL: POST http://localhost:8080/api/students
    @PostMapping
    public ResponseEntity<Student> createStudent(@Valid @RequestBody StudentDto studentDto) {
        // @RequestBody converts the JSON from the request into a StudentDto object
        // @Valid triggers all the validation annotations (like @NotBlank, @Email)
        Student created = studentService.createStudent(studentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created); // 201 Created
    }

    // ===== UPDATE STUDENT =====
    // URL: PUT http://localhost:8080/api/students/1
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentDto studentDto) {

        Student updated = studentService.updateStudent(id, studentDto);
        return ResponseEntity.ok(updated); // 200 OK
    }

    // ===== DELETE STUDENT =====
    // URL: DELETE http://localhost:8080/api/students/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student with id " + id + " deleted successfully.");
    }
}
















//package com.springboot.tutorial.learningSpringBoot.controller;
//
//
//import com.springboot.tutorial.learningSpringBoot.Dto.StudentDto;
//import com.springboot.tutorial.learningSpringBoot.entity.Student;
//import com.springboot.tutorial.learningSpringBoot.repository.studentRepository;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//public class StudentController {
//
//    private final studentRepository studentRepository;
//
//    public StudentController(studentRepository studentRepository) {
//        this.studentRepository = studentRepository;
//    }
//
//    @GetMapping("/getstudents")
//    public List<Student> getStudents() {
//        return studentRepository.findAll();
//    }
//
//    @GetMapping("/student")
//    public StudentDto getStudent(){
//        return new StudentDto(1L,"Vishal",21);
//    }
//}
