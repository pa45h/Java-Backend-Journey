package org.pa45h.coursesspringmvc.controller;

import org.pa45h.coursesspringmvc.model.Course;
import org.pa45h.coursesspringmvc.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/")
    public String home(){
        return "Home";
    }

    @PostMapping("/create")
    public String createCourse(@RequestBody Course course){
        courseService.createCourse(course);
        return "Course Created..!";
    }

    @PutMapping("/update")
    public String updateCourse(@RequestBody Course course){
        courseService.updateCourse(course);
        return "Course Updated..!";
    }

    @DeleteMapping("delete/{id}")
    public String deleteCourse(@PathVariable Long id){
        courseService.deleteCourse(id);
        return "Course Deleted..!";
    }

    @GetMapping("getAll")
    public List<Course> getAllCourse(){
        return courseService.getAllCourses();
    }

    @GetMapping("get/{id}")
    public Course getCourse(@PathVariable Long id){
        return courseService.getCourseById(id);
    }
}
