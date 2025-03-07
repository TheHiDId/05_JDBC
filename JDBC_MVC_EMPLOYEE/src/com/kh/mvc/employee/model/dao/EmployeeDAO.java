package com.kh.mvc.employee.model.dao;

public class EmployeeDAO {

	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
		} catch (ClassNotFoundException e) {
			System.out.println("ojdbc 라이브러리가 존재하지 않습니다.");
		}
	}
	
	
}
