package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;

@Controller
@RequestMapping("/projects") // route to this URL for project related
public class ProjectController {
	
	@Autowired // spring auto create instance in repository
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping
	public String displayProject(Model model) {
		List<Project> projects = proRepo.findAll();
		model.addAttribute("employees", projects);
		return "projects/list-projects";
	}
	
	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		Project aProject = new Project(); // required empty constructor of object
		List<Employee> employees = empRepo.findAll();
		// add to model
		model.addAttribute("project", aProject);
		model.addAttribute("allEmployees", employees);
		return "projects/new-project"; // map to template/new-project.html
	}
	
	@PostMapping("/save")
	public String createProject(Project project, Model model) {
		// this should save the project to database
		proRepo.save(project);
		// save employees assigned to project
		
		// use redirect to prevent duplicate submission
		return "redirect:/projects/new";
	}
}
