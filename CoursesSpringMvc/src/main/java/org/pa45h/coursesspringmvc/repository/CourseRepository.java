package org.pa45h.coursesspringmvc.repository;

import org.pa45h.coursesspringmvc.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
