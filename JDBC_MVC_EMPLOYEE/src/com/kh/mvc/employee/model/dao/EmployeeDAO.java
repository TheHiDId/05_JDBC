package com.kh.mvc.employee.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.mvc.employee.model.dto.EmployeeDTO;
import com.kh.mvc.employee.util.EmployeeUtil;

public class EmployeeDAO {

	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
		} catch (ClassNotFoundException e) {
			System.out.println("ojdbc 라이브러리가 존재하지 않습니다.");
		}
	}
	
	public String checkEmpId(Connection conn, String empId) {
		
		String sql = """
				SELECT
					EMP_ID
				FROM
					EMPLOYEE_COPY
				WHERE 
					EMP_ID = ?
				""";
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String result = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, empId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				result = rset.getString("EMP_ID");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			System.err.println("코드에 오타가 있습니다.");
		} 
		
		EmployeeUtil.closeResultSet(rset);
		EmployeeUtil.closeStatement(pstmt);
		
		return result;
	}

	public String checkEmpNo(Connection conn, String empNo) {
		
		String sql = """
				SELECT
					EMP_NO
				FROM
					EMPLOYEE_COPY
				WHERE 
					EMP_NO = ?
				""";
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String result = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, empNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				result = rset.getString("EMP_NO");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			System.err.println("코드에 오타가 있습니다.");
		} 
		
		EmployeeUtil.closeResultSet(rset);
		EmployeeUtil.closeStatement(pstmt);
		
		return result;
	}
	
	public List<EmployeeDTO> selectAll(Connection conn) {
		
		String sql = """
				SELECT 
					EMP_ID, 
					EMP_NAME, 
					EMP_NO, 
					EMAIL, 
					PHONE, 
					DEPT_CODE, 
					JOB_CODE, 
					SAL_LEVEL, 
					SALARY, BONUS, 
					MANAGER_ID, 
					HIRE_DATE, 
					ENT_DATE, 
					ENT_YN
				FROM 
					EMPLOYEE_COPY
				""";
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		List<EmployeeDTO> resultList = new ArrayList<EmployeeDTO>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				EmployeeDTO emplDto = new EmployeeDTO();
				
				emplDto.setEmpId(rset.getString("EMP_ID"));
				emplDto.setEmpName(rset.getString("EMP_NAME"));
				emplDto.setEmpNo(rset.getString("EMP_NO"));
				emplDto.setEmail(rset.getString("EMAIL"));
				emplDto.setPhone(rset.getString("PHONE"));
				emplDto.setDeptCode(rset.getString("DEPT_CODE"));
				emplDto.setJobCode(rset.getString("JOB_CODE"));
				emplDto.setSalLevel(rset.getString("SAL_LEVEL"));
				emplDto.setSalary(rset.getInt("SALARY"));
				emplDto.setBonus(rset.getDouble("BONUS"));
				emplDto.setManagerId(rset.getString("MANAGER_ID"));
				emplDto.setHireDate(rset.getDate("HIRE_DATE"));
				emplDto.setEntDate(rset.getDate("ENT_DATE"));
				emplDto.setEntYN(rset.getString("ENT_YN").charAt(0));
				
				resultList.add(emplDto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			System.err.println("코드에 오타가 있습니다.");
		}
		
		EmployeeUtil.closeResultSet(rset);
		EmployeeUtil.closeStatement(pstmt);
		
		return resultList;
	}
	
	public boolean insertEmployee(Connection conn, EmployeeDTO newEmployee) {
		
		String sql = """
				INSERT INTO
					EMPLOYEE_COPY 
					(EMP_ID, EMP_NAME, EMP_NO, EMAIL, PHONE, DEPT_CODE, JOB_CODE, SAL_LEVEL, SALARY, MANAGER_ID, HIRE_DATE, ENT_YN)
				VALUES (
					?,
					?,
					?,
					?,
					?,
					?,
					?,
					?,
					?,
					?,
					SYSDATE,
					'N'
				)
				""";
		
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, newEmployee.getEmpId());
			pstmt.setString(2, newEmployee.getEmpName());
			pstmt.setString(3, newEmployee.getEmpNo());
			pstmt.setString(4, newEmployee.getEmail());
			pstmt.setString(5, newEmployee.getPhone());
			pstmt.setString(6, newEmployee.getDeptCode());
			pstmt.setString(7, newEmployee.getJobCode());
			pstmt.setString(8, newEmployee.getSalLevel());
			pstmt.setInt(9, newEmployee.getSalary());
			pstmt.setString(10, newEmployee.getManagerId());
			
			result = pstmt.executeUpdate();
			
			if(result == 0) {
				conn.rollback();
				
				return false;
			}
			
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			System.out.println("코드에 오타가 있습니다.");
			
		} finally {
			EmployeeUtil.closeStatement(pstmt);
		}
		
		return true;
	}
}
