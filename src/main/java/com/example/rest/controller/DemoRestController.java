package com.example.rest.controller;

import com.example.rest.exceptions.StudentErrorResponse;
import com.example.rest.exceptions.StudentNotFoundException;
import com.example.rest.model.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test") // endpoint will start as /test/*
public class DemoRestController {

    List<Student> students = new ArrayList<>();

    @GetMapping("/hello")
    public String Greetings(){
        return "Hello";
    }

    // @PostConstruct marks a method that Spring will call once after the bean is instantiated and dependency injection is complete, during the bean initialization phase
    @PostConstruct
    public void loadData(){
        students.add(new Student("Mahesh", "Sontakke"));
        students.add(new Student("Yash", "Sinha"));
        students.add(new Student("Riya ", "Agarwal"));
    }

    @GetMapping("/students")
    public List<Student> getStudents(){
        return students;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        if(studentId >= students.size() || studentId < 0){
            throw new StudentNotFoundException("Student Id " + studentId + " not found");
        }
        return students.get(studentId);
    }

    // Add an exception handler
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exp){

//        create a student error response
        StudentErrorResponse response = new StudentErrorResponse();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(exp.getMessage());
        response.setTimeStamp(System.currentTimeMillis());

//        return responseEntity

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
