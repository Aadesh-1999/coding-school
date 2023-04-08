package com.coding.school.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coding.school.model.Student;

@RestController
public class SchoolServiceController {

	List<Student> studentsList;

	public SchoolServiceController() {
		studentsList = new ArrayList<Student>();
		Student s1 = new Student(1, "Aadesh");
		Student s2 = new Student(2, "Aaditi");
		Student s3 = new Student(3, "Shri");
		Student s4 = new Student(4, "Omkar");
		studentsList.add(s1);
		studentsList.add(s2);
		studentsList.add(s3);
		studentsList.add(s4);
		studentsList.add(new Student(5, "Mayur"));
		studentsList.add(new Student(5, "Rushi"));
	}

	@RequestMapping(value = { "/student/all" }, method = { RequestMethod.GET }, produces = { "application/json" })
	public ResponseEntity<List<Student>> getStudents() {
		return new ResponseEntity<List<Student>>(studentsList, HttpStatus.OK);
	}

	@RequestMapping(value = { "/student/{id}" }, method = { RequestMethod.GET }, produces = { "application/json" })
	public ResponseEntity<?> getStudent(@PathVariable("id") int id) {
		if (id > studentsList.size() - 1 || studentsList.get(id) == null || studentsList.get(id).isEmpty()) {
			return new ResponseEntity<String>("student not found", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Student>>(
				studentsList.stream().filter(n -> n.getId() == id).collect(Collectors.toList()), HttpStatus.OK);
	}

	@RequestMapping(value = { "/student/add" }, method = { RequestMethod.POST }, produces = {
			"application/json" }, consumes = { "application/json" })
	public ResponseEntity<?> addStudent(@RequestBody Student requestBody) {
		if (requestBody != null && !requestBody.isEmpty()) {
			studentsList.add(requestBody);
		} else {
			return new ResponseEntity<String>("request body missing", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Student>>(studentsList, HttpStatus.OK);
	}

	@RequestMapping(value = { "/student/edit" }, method = { RequestMethod.PUT }, produces = {
			"application/json" }, consumes = { "application/json" })
	public ResponseEntity<?> editStudent(@RequestBody Student requestBody) {
		if (requestBody != null && !requestBody.isEmpty()) {
			if (requestBody.getId() > studentsList.size() - 1 || studentsList.get(requestBody.getId()) == null
					|| studentsList.get(requestBody.getId()).isEmpty()) {
				return new ResponseEntity<String>("student not found", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			studentsList.stream().map(n -> {
				if (n.getId() == requestBody.getId()) {
					n.setName(requestBody.getName());
					return n;
				} else
					return n;
			}).collect(Collectors.toList());
		} else {
			return new ResponseEntity<String>("request body missing", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Student>>(studentsList, HttpStatus.OK);
	}

	@RequestMapping(value = { "/student/delete/{id}" }, method = { RequestMethod.DELETE })
	public ResponseEntity<?> deleteStudent(@PathVariable("id") int id) {
		if (id > studentsList.size() - 1 || studentsList.get(id) == null || studentsList.get(id).isEmpty()) {
			return new ResponseEntity<String>("student not found", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		studentsList.removeIf(n -> n.getId() == id);
		return new ResponseEntity<List<Student>>(studentsList, HttpStatus.OK);
	}
}
