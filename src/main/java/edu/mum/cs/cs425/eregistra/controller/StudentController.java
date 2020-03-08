package edu.mum.cs.cs425.eregistra.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.mum.cs.cs425.eregistra.model.Search;
import edu.mum.cs.cs425.eregistra.model.Student;
import edu.mum.cs.cs425.eregistra.repository.StudentRepository;

@Controller
public class StudentController {
	@Autowired
	private StudentRepository studentRepository;
	@RequestMapping(value = {"/studentlist"}, method = RequestMethod.GET)
	public String getStudentList(Model model) {
		model.addAttribute("student", new Student());
		model.addAttribute("search", new Search());
		model.addAttribute("students", studentRepository.findAll());
		return "student/studentlist";
	}
	@GetMapping("/addstudent")
	public ModelAndView addStudent(@RequestParam(value = "studentId", required = false) Long studentId) {
		ModelAndView modelAndView = new ModelAndView();
		Student student = new Student();
		if(studentId != null)
			student = studentRepository.findById(studentId.longValue()).orElse(new Student());
		modelAndView.setViewName("/student/addstudent");
		modelAndView.addObject("student", student);
		return modelAndView;
	}
	@PostMapping("/addstudent")
	public String postStudent(@ModelAttribute Student student) {
		System.out.println(student.toString());
		studentRepository.save(student);
		return "redirect:/studentlist";
	}
	@GetMapping("/deletestudent")
	public String deleteStudent(@RequestParam("studentId") Long studentId) {
		if(studentId != null)
			studentRepository.deleteById(studentId);
		return "redirect:/studentlist";
	}
	@PostMapping("/search")
	public String search(@ModelAttribute Search search, Model model) {
		double cgpa = 0; 
		List<Student> students = new ArrayList<>();
		try {
			cgpa = Double.parseDouble(search.getSearch());
			students = studentRepository.findStudentsByCgpa(cgpa);
		}catch(Exception ex) {
			try {
				LocalDate date = LocalDate.parse(search.getSearch());
				students = studentRepository.findStudentsByEnrollmentDate(date);
			}catch(Exception ex2) {}
		}
		if(students.size() == 0)
			students = studentRepository.findStudentsByStringFields(search.getSearch());	
		model.addAttribute("students", students);
		return "/student/studentlist";
	}
}
