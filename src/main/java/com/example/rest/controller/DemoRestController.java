package com.example.rest.controller;

import com.example.rest.model.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return students.get(studentId);
    }
}
