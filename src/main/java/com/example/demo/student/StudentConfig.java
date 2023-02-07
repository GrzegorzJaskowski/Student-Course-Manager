package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student jan = new Student(
                    "Jan",
                    "Kowalski",
                    LocalDate.of(2000, Month.JANUARY,5),
                    20.
            );

            Student piotr = new Student(
                    "Piotr",
                    "Nowak",
                    LocalDate.of(2004, Month.JANUARY,5),
                    11.5
            );

            repository.saveAll(List.of(jan, piotr));
        };
    }
}
