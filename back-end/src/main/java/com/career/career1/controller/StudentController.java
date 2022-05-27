package com.career.career1.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.career.career1.model.Student;
import com.career.career1.repo.StudentRepo;



@RestController
@CrossOrigin
public class StudentController {
    @Autowired
    private StudentRepo studentRepo;


    @PostMapping(value="register")
    public int createStudent(Student stu) {
        String username = stu.getUsername();
        int length = (int) studentRepo.count();
        int b = 1;
        String u = "";
        for (int i = 1; i <= length; i++) {
            u = studentRepo.findById(i).get().getUsername();
            if (username.equals(u)) return b = 0;
        }
        if (b == 1) {
            studentRepo.save(stu);
        }
        return b;
    }

    @PostMapping(value="login")
    public int login(@RequestParam(name = "username") String username,
                     @RequestParam(name = "password") String password) {
        int length = (int) studentRepo.count();
        int b = 0;
        String u = "";
        String p = "";
        for (int i = 1; i <= length; i++) {
            u = studentRepo.findById(i).get().getUsername();
            if (username.equals(u)){
                p = studentRepo.findById(i).get().getPassword();
                if (password.equals(p)) {
                    b = 1;
                }
            }
        }
        return b;
    }

    @PostMapping(value = "basicInfo")
    public String basicInfo(@RequestParam(name = "username") String username) {
        int length = (int) studentRepo.count();
        String s = "";
        String u = "";
        for (int i = 1; i <= length; i++) {
            Student stu = studentRepo.findById(i).get();
            u = stu.getUsername();
            if (username.equals(u)){
                s = stu.getEmail() + "?" + stu.getSchool() + "%" + stu.getSex();
                return s;
            }
        }
        return s;
    }

    @PostMapping(value = "beforeEdit")
    public String beforeEdit(@RequestParam(name = "username") String username) {
        int length = (int) studentRepo.count();
        String s = "";
        String u = "";
        for (int i = 1; i <= length; i++) {
            Student stu = studentRepo.findById(i).get();
            u = stu.getUsername();
            if (username.equals(u)){
                s = stu.getAge() + "#" + stu.getPhone() + "$" + stu.getEmail() + "%" + stu.getSchool();
                return s;
            }
        }
        return s;
    }

    @PostMapping(value = "edit")
    public int edit(@RequestParam(name = "username") String username,
                    @RequestParam(name = "email") String email,
                    @RequestParam(name = "school") String school,
                    @RequestParam(name = "phone") Long phone,
                    @RequestParam(name = "age") int age) {
        int length = (int) studentRepo.count();
        int e = 0;
        String u = "";
        for (int i = 1; i <= length; i++) {
            Student stu = studentRepo.findById(i).get();
            u = stu.getUsername();
            if (username.equals(u)){
                stu.setEmail(email);
                stu.setSchool(school);
                stu.setPhone(phone);
                stu.setAge(age);
                studentRepo.save(stu);
                e = 1;
                break;
            }
        }
        return e;
    }
}
