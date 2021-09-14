package com.example.demo.student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class StudentService {
	public List<Student> getStudents() {
		return List.of(new Student(1L, "John Million", "jpmillion@comcast.net", LocalDate.of(2000, 1, 16)));
	}
}
