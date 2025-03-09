package com.kh.mvc.employee.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.mvc.employee.model.dao.EmployeeDAO;
import com.kh.mvc.employee.model.dto.EmployeeDTO;
import com.kh.mvc.employee.util.EmployeeUtil;

public class EmployeeService {
	private EmployeeDAO emplDao = new EmployeeDAO();
	
	public String checkEmpId(String empId) {
		Connection conn = EmployeeUtil.getConnection();
		
		String result = emplDao.checkEmpId(conn, empId);
		
		EmployeeUtil.closeConnection(conn);
		
		return result;
	}
	
	public String checkEmpNo(String empNo) {
		Connection conn = EmployeeUtil.getConnection();
		
		String result = emplDao.checkEmpNo(conn, empNo);
		
		EmployeeUtil.closeConnection(conn);
		
		return result;
	}
	
	public List<EmployeeDTO> selectAll() {
		Connection conn = EmployeeUtil.getConnection();
		
		List<EmployeeDTO> resultList = emplDao.selectAll(conn);
		
		EmployeeUtil.closeConnection(conn);
		
		return resultList;
	}
	
	public boolean insertEmployee(EmployeeDTO newEmployee) {
		Connection conn = EmployeeUtil.getConnection();
		
		int salary = newEmployee.getSalary();
		
		if(salary < 3000000) {
			newEmployee.setSalLevel("S6");
			
		}	else if(salary < 4000000) {
			newEmployee.setSalLevel("S5");
			
		}	else if(salary < 5000000) {
			newEmployee.setSalLevel("S4");
			
		}	else if(salary < 6000000) {
			newEmployee.setSalLevel("S3");

		}	else if(salary < 7000000) {
			newEmployee.setSalLevel("S2");
			
		} else {
			newEmployee.setSalLevel("S1");
		}
		
		return emplDao.insertEmployee(conn, newEmployee);
	}
}
