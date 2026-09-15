package org.pa45h.jobspringbootmongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JobSpringbootMongodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobSpringbootMongodbApplication.class, args);
        System.out.println("Server Started..");
    }
}
