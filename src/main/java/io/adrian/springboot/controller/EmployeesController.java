package io.adrian.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.adrian.springboot.model.EmployeesModel;
import io.adrian.springboot.services.EmployeesService;

@RestController
public class EmployeesController {
	@Autowired
	private EmployeesService employeesService;
	
	EmployeesController() {}
	
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello World from Spring Boot!";
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/get/employees/sorted/salary")
	public List<EmployeesModel> sortEmployeesBySalaryAsc() {
		return employeesService.sortEmployeesBySalaryAsc();
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/get/employees/younger/than/{age}")
	public List<EmployeesModel> sortEmployeesByInputAge(@PathVariable int age) {
		return employeesService.sortEmployeesByInputAge(age);
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/get/employees/all")
	public List<EmployeesModel> getAllEmployees() {
		return employeesService.getAllEmployees();
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/create/employees/new")
	@ResponseStatus(HttpStatus.CREATED)
	public EmployeesModel createNewEmployee(@RequestBody EmployeesModel newEmployee) {
		return employeesService.createNewEmployee(newEmployee);
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/get/employees/{name}")
	public EmployeesModel getEmployeeByName(@PathVariable String name) {
		return employeesService.getEmployeeByName(name);
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping("/edit/employees/{name}")
	public EmployeesModel editEmployeeByName(
			@PathVariable("name") String name,
			@RequestBody EmployeesModel newInfo
		) {
		return employeesService.editEmployeeByName(name, newInfo);
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@DeleteMapping("/delete/employees/{name}")
	public void deleteEmployeeByName(@PathVariable String name) {
		employeesService.deleteEmployeeByName(name);
	}
	
	//Handle Exception Name not found
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalStateException.class)
	public void HandleNameNotFound() {}
	
}
