package com.kh.mvc.employee.util;

import com.kh.mvc.employee.controller.EmployeeController;

public class ValidationCheck {
	
	public boolean checkEmpId(String empId) {
		EmployeeController emplController = new EmployeeController();
		
		if(empId.length() != 3) return false;
		
		for(int i = 0; i < empId.length(); i++) {
			if(!Character.isDigit(empId.charAt(i))) return false;
		}
		
		if(!(emplController.checkEmpId(empId) == null)) return false;
		
		return true;
	}
	
	public boolean checkName(String name) {
		if(name.isEmpty() || name.length() > 20) return false;
		
		for(int i = 0; i < name.length(); i++) {
			if(!Character.isLetter(name.charAt(i))) return false;
		}
		
		return true;
	}
	
	public boolean checkResNo(String resNo) {
		EmployeeController emplController = new EmployeeController();
		
		if(resNo.length() != 14 || !(resNo.charAt(6) == '-')) return false;
		
		for(int i = 0; i < resNo.length(); i++) {
			if(i == 6) continue;
			
			if(!Character.isDigit(resNo.charAt(i))) return false;
		}
		
		if(!(emplController.checkEmpNo(resNo) == null)) return false;
			
		return true;
	}
	
	public boolean checkEmail(String email) {
		if(email.isEmpty() || email.length() > 25 || !email.contains("@")) return false;
		
		return true;
	}
	
	public boolean checkPhone(String phone) {
		if(phone.length() != 11) return false;
		
		for(int i = 0; i < phone.length(); i++) {
			if(!Character.isDigit(phone.charAt(i))) return false;
		}
		
		return true;
	}
	
	public boolean checkDeptCode(String deptCode) {
		if(deptCode.length() != 2 || !deptCode.startsWith("D") || !Character.isDigit(deptCode.charAt(1))) return false;
		
		return true;
	}
	
	public boolean checkJobCode(String jobCode) {
		if(jobCode.length() != 2 || !jobCode.startsWith("J") || !Character.isDigit(jobCode.charAt(1))) return false;
		
		return true;
	}
	
	public boolean checkSalary(int salary) {
		if(salary < 2000000 || salary > 10000000) return false;
		
		return true;
	}
	
	public boolean checkManagerId(String manegerId) {
		EmployeeController emplController = new EmployeeController();
		
		if(manegerId.length() != 3) return false;
		
		for(int i = 0; i < manegerId.length(); i++) {
			if(!Character.isDigit(manegerId.charAt(i))) return false;
		}
		
		if(emplController.checkEmpId(manegerId).isEmpty()) return false;
		
		return true;
	}
}
