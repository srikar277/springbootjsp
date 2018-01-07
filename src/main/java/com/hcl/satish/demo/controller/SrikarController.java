package com.hcl.satish.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.satish.demo.entity.Student;
import com.hcl.satish.demo.repository.StudentRepo;

@Controller

@RestController
@RequestMapping("srikar")
public class SrikarController {

	@Autowired
	private StudentRepo studentRepo;

	@GetMapping("/hello")
	public List<Student> hellojj() {
		return studentRepo.findAll();

	}
	
	@PostMapping("/hello")
	public void postStudent(@RequestBody Student student) {
		 studentRepo.save(student);

	}
	
	@DeleteMapping("/hello/{studentId}")
	public void deleteStudent(@PathVariable Integer studentId) {
		System.out.println("student id: "+studentId);
		 studentRepo.delete(studentId);

	}
	
	
	@PutMapping("/hello/{studentId}")
	public void editStudent(@RequestBody Student student,@PathVariable Integer studentId) {
		System.out.println("student id: "+studentId);
		
		System.out.println("student  "+student);
		
		Student stu =studentRepo.findOne(studentId);
		
		if(stu !=null) {
		stu.setStudentId(student.getStudentId());
		stu.setName(student.getName());
		stu.setAddress(student.getAddress());
		
		
		 studentRepo.save(stu);
		}

	}
	
	@PutMapping("/test/put")
	public void editStudentdd(@RequestBody Student student,@RequestParam("id") Integer id  ) {
		
		System.out.println("student  "+student);
		
		Student stu =studentRepo.findOne(id);
		
		if(stu !=null) {
		stu.setStudentId(student.getStudentId());
		stu.setName(student.getName());
		stu.setAddress(student.getAddress());
		
		
		 studentRepo.save(stu);
		}

	}
	
	

	}
