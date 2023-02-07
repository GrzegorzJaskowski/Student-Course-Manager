package com.example.demo.course;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CourseConfig {

    @Bean
    CommandLineRunner commandLineRunnerC(CourseRepository repository){
        return args -> {
            Course english = new Course(
                    "english",
                    10
            );

            Course science = new Course(
                    "science",
                    10
            );

            repository.saveAll(List.of(english, science));
        };
    }
}
