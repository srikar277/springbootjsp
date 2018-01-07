package com.hcl.satish.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hcl.satish.demo.entity.Student;
import com.hcl.satish.demo.repository.StudentRepo;

@Controller

@RestController
public class StudentController {

	@Autowired
	private StudentRepo studentRepo;

	@GetMapping("test/student")
	public List<Student> hellojj() {
		return studentRepo.findAll();

	}

	@GetMapping("/hello")
	public ModelAndView getDetails() {
		
		System.out.println("Gett deatils..");
		List<Student> slist = studentRepo.findAll();
		
		System.out.println("slist size :: " +slist.size());
		
		ModelAndView m = new ModelAndView();
		m.addObject("lists", slist);
		m.setViewName("index");
		
		return m;

	}

	@RequestMapping("/hai")
	public String hello(Model model,
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		model.addAttribute("name", name);
		return "hello";
	}
	
	@RequestMapping("test/hai")
	public String hellodd() {
		return "hello";
	}
}
