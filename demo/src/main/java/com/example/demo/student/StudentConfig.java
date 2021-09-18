package com.example.demo.student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository repository) {
		return args -> {
			Student jp = new Student("John Million", "jpmillion@comcast.net", LocalDate.of(1978, 10, 24));
			Student mary = new Student("Mary Million", "mary@comcast.net", LocalDate.of(1979, 6, 20));
			
			repository.saveAll(List.of(jp, mary));
		};
	}
}
