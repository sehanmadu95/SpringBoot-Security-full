package com.technotic.Spring_security.controller;

import com.technotic.Spring_security.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CsrfTocken {
     List<Student> list=new ArrayList<>(List.of(
            new Student(1,"Shehan",55),
            new Student(2,"Shehan2",36),
            new Student(3,"Shehan3",78),
            new Student(4,"Shehan4",50)
    ));

    @GetMapping("/getAll")
    private List<Student> getStudent(){
        return list;
    }

    @PostMapping("/save")
    public Boolean saveStudent(@RequestBody Student student){
       return list.add(student);
    }

    @GetMapping("/getCSRF")
    public CsrfToken getTocken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
