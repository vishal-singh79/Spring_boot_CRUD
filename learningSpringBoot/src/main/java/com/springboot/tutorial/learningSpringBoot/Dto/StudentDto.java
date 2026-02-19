package com.springboot.tutorial.learningSpringBoot.Dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class StudentDto {

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dob;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be exactly 10 digits")
    private String phone;
}



























//package com.springboot.tutorial.learningSpringBoot.Dto;
//
//import lombok.Data;
//
//@Data
//public class StudentDto {
//    private Long id;
//    private String name;
//    private Integer age;
//
//    public StudentDto(Long id, String name, Integer age) {
//        this.id = id;
//        this.name = name;
//        this.age = age;
//    }
//}
