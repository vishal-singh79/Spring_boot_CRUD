package com.springboot.tutorial.learningSpringBoot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity

@Table(name = "students")

@Getter
@Setter
public class Student {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    @Column(name = "dob")
    private LocalDate dob;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be exactly 10 digits")
    @Column(name = "phone")
    private String phone;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @PrePersist
    public void beforeSave() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }


    @PreUpdate
    public void beforeUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}












































//package com.springboot.tutorial.learningSpringBoot.entity;
//
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import lombok.Getter;
//import lombok.Setter;
//
//@Entity
//@Getter
//@Setter
//public class Student {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//    private String Email;
//}
