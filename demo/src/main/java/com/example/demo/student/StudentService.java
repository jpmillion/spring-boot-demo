package com.example.demo.student;


import java.util.List;
import java.util.Optional;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {
	
	private final StudentRepository studentRepository;
	
	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	public void addNewStudent(Student student) {
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
		
		if (studentOptional.isPresent()) {
			throw new IllegalStateException("Email taken");
		}
		
		studentRepository.save(student);
	}
	
	public void deleteStudent(Long studentId) {
		boolean exists = studentRepository.existsById(studentId);
		
		if (!exists) {
			throw new IllegalStateException("Student not found with id " + studentId);
		}
		
		studentRepository.deleteById(studentId);
		
	}

	@Transactional
	public void updateStudent(Long studentId, Student s) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new IllegalStateException("Student with id " + studentId + "does not exist"));
		
		if (s.getName() != null && s.getName().length() > 0 && !Objects.equals(student.getName(), s.getName())) {
			student.setName(s.getName());
		}
		
		if (s.getEmail() != null && s.getEmail().length() > 0 && !Objects.equals(student.getEmail(), s.getEmail())) {
			Optional<Student> studentEmail = studentRepository.findStudentByEmail(s.getEmail());
			
			if (studentEmail.isPresent()) {				throw new IllegalStateException("Email already taken");
			}
			student.setEmail(s.getEmail());
		}
	}

}
