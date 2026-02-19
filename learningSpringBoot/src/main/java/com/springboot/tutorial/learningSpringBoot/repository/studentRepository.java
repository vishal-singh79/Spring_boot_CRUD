package com.springboot.tutorial.learningSpringBoot.repository;

import com.springboot.tutorial.learningSpringBoot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface studentRepository extends JpaRepository<Student, Long> {


    Optional<Student> findByEmail(String email);


    boolean existsByEmail(String email);

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
