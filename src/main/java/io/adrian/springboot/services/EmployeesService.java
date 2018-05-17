package io.adrian.springboot.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.adrian.springboot.model.EmployeesModel;

@Service
public class EmployeesService {
	private List<EmployeesModel> employees = new ArrayList<EmployeesModel>();
	
	EmployeesService() {
		readResource("/json/employee.json");
	}
	
	public List<EmployeesModel> getAllEmployees() {
		Collections.shuffle(employees);
		return employees;
	}
	
	public EmployeesModel createNewEmployee(EmployeesModel newEmployee) {
		employees.add(newEmployee);
		return newEmployee;
	}
	
	public EmployeesModel getEmployeeByName(String name) {
		return employees.stream().filter(t -> t.getName().equals(name)).findFirst().get();
	}
	
	public EmployeesModel editEmployeeByName(String name, EmployeesModel newInfo) {
		for(int i = 0; i < employees.size(); i++) {
			EmployeesModel e = employees.get(i);
			if(e.getName().equals(name)) {
				employees.set(i, newInfo);
				return e;
			}
		}
		throw new IllegalStateException();
	}
	
	public void deleteEmployeeByName(String name) {
		for(int i = 0; i < employees.size(); i++) {
			EmployeesModel e = employees.get(i);
			if(e.getName().equals(name)) {
				employees.remove(e);
				return;
			}
		}
		throw new IllegalStateException();
	}

	public List<EmployeesModel> sortEmployeesBySalaryAsc() {
		List<EmployeesModel> sortedBySalary = employees;
		sortedBySalary.sort(Comparator.comparing(EmployeesModel::getSalary));
		return sortedBySalary;
	}

	public List<EmployeesModel> sortEmployeesByInputAge(int age) {
		List<EmployeesModel> sortedByInputAge = employees;
		sortedByInputAge = employees.stream().filter(e -> e.getAge() < age).collect(Collectors.toList());
		Collections.shuffle(sortedByInputAge);
		return sortedByInputAge;
	}
	
	public void readResource(String resource) {
		//Read Json and convert to object
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<EmployeesModel>> typeReference = new TypeReference<List<EmployeesModel>>() {};
		InputStream inputStream = typeReference.getClass().getResourceAsStream(resource);
		try {
			List<EmployeesModel> employeesResource = mapper.readValue(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1 ), typeReference);
			employees = employeesResource;
			Collections.shuffle(employees);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
