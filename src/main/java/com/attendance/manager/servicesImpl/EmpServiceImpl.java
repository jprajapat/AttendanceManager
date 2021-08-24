package com.attendance.manager.servicesImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.attendance.manager.entities.Employee;
import com.attendance.manager.exception.EmptyInputException;
import com.attendance.manager.exception.RecordNotFoundException;
import com.attendance.manager.repositories.EmployeeRepo;
import com.attendance.manager.services.EmpService;

@Service
public class EmpServiceImpl implements EmpService {

	private static final Logger logger = LoggerFactory.getLogger(EmpServiceImpl.class);
	
	@Autowired
	private EmployeeRepo employeeRepo;

	@Override
	public Employee saveEmplyee(Employee employee) {
		if(employee.getEmail() == null) {
			logger.error("saveEmplyee Email id not Empty");
			throw new EmptyInputException();
		}
		return employeeRepo.save(employee);
		
	}

	@Override
	public List<Employee> getAllEmployee() {
		return employeeRepo.findAll();
	}

	@Override
	public void deleteEmployee(int id) {
		employeeRepo.deleteById(id);
	}

	@Override
	public Employee updateEmployee(int id, Employee employee) {
		
		Employee emp = employeeRepo.findById(id).orElse(employee);
		Employee save;

		if (emp.getId() == id) {
			emp.setEmployeeName(employee.getEmployeeName());
			emp.setEmail(employee.getEmail());
			emp.setContactNumber(employee.getContactNumber());
			save = employeeRepo.save(emp);
		}else {
			logger.error("updateEmployee record not found ",id);
			throw new RecordNotFoundException("id not present");
		}
		return save;
	}

	@Override
	public List<Employee> findByEmployeeName(String employeeName) {
		List<Employee> allEmp = employeeRepo.findByEmployeeName(employeeName);
		

		if(CollectionUtils.isEmpty(allEmp)) {
			logger.error("findByEmployeeName record not found ",employeeName);
			throw new RecordNotFoundException("Employee name is not match");
		}
		return employeeRepo.findByEmployeeName(employeeName);
	}

	@Override
	public List<Employee> findByEmployeeNameStartingWith(String startingName) {
		List<Employee> allEmp = employeeRepo.findByEmployeeNameStartingWith(startingName);

		if (allEmp.isEmpty()) {
			logger.error("findByEmployeeNameStartingWith record not found ",startingName);
			throw new RecordNotFoundException("Record not present");
		}
		return employeeRepo.findByEmployeeNameStartingWith(startingName);
	}

	@Override
	public Employee findByEmail(String email) {
		return employeeRepo.findByEmail(email);
	}
}
