package com.springboot.hktss.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.hktss.repository.EmployeeRepository;
import com.springboot.hktss.entity.Employee;

@Service
public class EmployeeSerice {
	private static final Logger log = LoggerFactory.getLogger(Employee.class);

	@Autowired
	EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployees() {
		log.info("inside EmployeeService.getAllEmployees");
		return employeeRepository.findAll();
	}

	public Employee getEmployeeById(int id) {
		log.info("Getting employee details for ID " + id);
		return employeeRepository.getOne((long) id);
	}

	public void addEmployee(Employee employee) {
		log.info("Adding Employee " + employee);
		employeeRepository.save(employee);
	}

	public void updateEmployee(Employee employee) {
		log.info("Updating Employee " + employee);
		employeeRepository.saveAndFlush(employee);
	}

	public void deleteEmployee(int id) {
		log.info("Deleting employee for ID " + id);
		employeeRepository.deleteById(new Long(id));
	}

}
