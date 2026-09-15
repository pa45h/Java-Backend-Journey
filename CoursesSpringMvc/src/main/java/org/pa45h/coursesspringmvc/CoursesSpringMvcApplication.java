package org.pa45h.coursesspringmvc;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoursesSpringMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoursesSpringMvcApplication.class, args);
        System.out.println("CoursesSpringMvcApplication started");
    }
}
