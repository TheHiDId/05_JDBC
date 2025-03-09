package com.kh.mvc.employee.controller;

import java.util.List;

import com.kh.mvc.employee.model.dto.EmployeeDTO;
import com.kh.mvc.employee.model.service.EmployeeService;

public class EmployeeController {
	EmployeeService emplService = new EmployeeService();
	
	public String checkEmpId(String empId) {
		return emplService.checkEmpId(empId);
	}
	
	public String checkEmpNo(String empNo) {
		return emplService.checkEmpNo(empNo);
	}
	
	public List<EmployeeDTO> selectAll() {
		return emplService.selectAll();
	}
	
	public boolean insertEmployee(String empId, String empName, String empNo, String email, String phone, String deptCode, String jobCode, int salary, String managerId) {
		EmployeeDTO newEmployee = new EmployeeDTO();
		
		newEmployee.setEmpId(empId);
		newEmployee.setEmpName(empName);
		newEmployee.setEmpNo(empNo);
		newEmployee.setEmail(email);
		newEmployee.setPhone(phone);
		newEmployee.setDeptCode(deptCode);
		newEmployee.setJobCode(jobCode);
		newEmployee.setSalary(salary);
		newEmployee.setManagerId(managerId);
		
		return emplService.insertEmployee(newEmployee);
	}
}
