package org.pa45h.coursesspringmvc.service;

import org.pa45h.coursesspringmvc.model.Course;
import org.pa45h.coursesspringmvc.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public void createCourse(Course course){
        this.courseRepository.save(course);
    }

    public void updateCourse(Course course){
        Course existing = getCourseById(course.getId());
        existing.setCourseName(course.getCourseName());
        existing.setInstructor(course.getInstructor());
        existing.setEmail(course.getEmail());
        this.courseRepository.save(existing);
    }

    public List<Course> getAllCourses(){
        return this.courseRepository.findAll();
    }

    public Course getCourseById(Long id){
        return this.courseRepository.findById(id).get();
    }

    public void deleteCourse(Long id){
        this.courseRepository.deleteById(id);
    }
}
