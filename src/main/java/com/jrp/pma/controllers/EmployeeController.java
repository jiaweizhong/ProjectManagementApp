package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.entities.Employee;

@Controller
@RequestMapping("/employees") // route to this URL for employee related
public class EmployeeController {
	
	@Autowired // spring auto create instance in repository
	EmployeeRepository empRepo;
	
	@GetMapping
	public String displayEmployee(Model model) {
		List<Employee> employees = empRepo.findAll();
		model.addAttribute("employees", employees);
		return "employees/list-employees";
	}
	
	@GetMapping("/new")
	public String displayEmployeeForm(Model model) {
		Employee aEmployee = new Employee(); // required empty constructor of object
		model.addAttribute("employee", aEmployee);
		return "employees/new-employee"; // map to template/new-employee.html
	}
	
	@PostMapping("/save")
	public String createEmployee(Employee employee, Model model) {
		// this should save the project to database
		empRepo.save(employee);
		// use redirect to prevent duplicate submission
		return "redirect:/employees/new";
	}
}
