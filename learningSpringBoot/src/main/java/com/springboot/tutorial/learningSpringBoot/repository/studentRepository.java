package com.springboot.tutorial.learningSpringBoot.repository;

import com.springboot.tutorial.learningSpringBoot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// @Repository marks this as a data access layer component
// JpaRepository gives us free CRUD methods like save(), findById(), findAll(), deleteById()
// We just need to add any custom methods we need
@Repository
public interface studentRepository extends JpaRepository<Student, Long> {

    // Spring Data JPA automatically writes the SQL for methods named like this!
    // This finds a student by their email address
    Optional<Student> findByEmail(String email);

    // This checks if any student already has a given email (useful for validation)
    boolean existsByEmail(String email);

    // This checks if a DIFFERENT student (not the one being updated) has the same email
    boolean existsByEmailAndIdNot(String email, Long id);
}
















//package com.springboot.tutorial.learningSpringBoot.repository;
//
//import com.springboot.tutorial.learningSpringBoot.entity.Student;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface studentRepository extends JpaRepository<Student, Long> {
//}
