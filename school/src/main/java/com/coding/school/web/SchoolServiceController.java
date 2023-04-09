package com.coding.school.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coding.school.crud.entity.Student;
import com.coding.school.crud.repository.StudentRepository;

@RestController
public class SchoolServiceController {

	@Autowired
	private StudentRepository studentRepository;

	@RequestMapping(value = { "/student/all" }, method = { RequestMethod.GET }, produces = { "application/json" })
	public ResponseEntity<List<Student>> getStudents() {
		return new ResponseEntity<List<Student>>(studentRepository.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = { "/student/{id}" }, method = { RequestMethod.GET }, produces = { "application/json" })
	public ResponseEntity<?> getStudent(@PathVariable("id") int id) {
		if (studentRepository.findById(id).isPresent()) {
			return new ResponseEntity<Student>(studentRepository.findById(id).get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Student not found", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = { "/student/add" }, method = { RequestMethod.POST }, produces = {
			"application/json" }, consumes = { "application/json" })
	public ResponseEntity<?> addStudent(@RequestBody Student requestBody) {
		if (requestBody != null && !requestBody.isEmpty()) {
			if (studentRepository.findById(requestBody.getId()).isPresent()) {
				return new ResponseEntity<String>("Student already exist with same id", HttpStatus.BAD_REQUEST);
			}
			studentRepository.save(requestBody);
			return new ResponseEntity<List<Student>>(studentRepository.findAll(), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Request body missing", HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = { "/student/edit" }, method = { RequestMethod.PUT }, produces = {
			"application/json" }, consumes = { "application/json" })
	public ResponseEntity<?> editStudent(@RequestBody Student requestBody) {
		if (requestBody != null && !requestBody.isEmpty()) {
			if (studentRepository.findById(requestBody.getId()).isPresent()) {
				Student student = studentRepository.findById(requestBody.getId()).get();
				student.setName(requestBody.getName());
				studentRepository.save(student);
				return new ResponseEntity<List<Student>>(studentRepository.findAll(), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Student not found", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<String>("Request body missing", HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = { "/student/delete/{id}" }, method = { RequestMethod.DELETE }, produces = {
			"application/json" })
	public ResponseEntity<?> deleteStudent(@PathVariable("id") int id) {
		if (studentRepository.findById(id).isPresent()) {
			studentRepository.deleteById(id);
			return new ResponseEntity<List<Student>>(studentRepository.findAll(), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Student not found", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
