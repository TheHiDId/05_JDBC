package com.kh.mvc.employee.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.kh.mvc.employee.controller.EmployeeController;
import com.kh.mvc.employee.model.dto.EmployeeDTO;
import com.kh.mvc.employee.util.ValidationCheck;

public class EmployeeView {
	// Controller 객체
	private EmployeeController emplController = null;
	// 스캐너 대신 사용할 BufferedReader
	private BufferedReader br = null;
	
	/**
	 * EmployeeView가 생성될 때, 
	 * EmployeeController와 BufferedReader 객체 생성, 
	 * 예외 발생 시 프로그램 종료
	 */
	public EmployeeView() {
		try {
			emplController = new EmployeeController();
			
			br = new BufferedReader(new InputStreamReader(System.in));
			
		} catch (Exception e) {
			System.err.println("프로그램 실행 중 오류가 발생해 프로그램을 종료합니다.\n");
			
			e.printStackTrace();
			
			System.exit(0);
		}
	}
	
	/**
	 * 프로그램 시작 메서드
	 */
	public void startView() {
		// 사용자가 메뉴에서 입력할 정수를 담을 변수
		int inputNum = -1;
		
		do {
			try {
				// mainMenu 메서드 출력하고 반환받은 정수로 과정 진행
				inputNum = mainMenu();
				
				switch(inputNum) {
				case 1: selectAll(); break;
				case 2: insertEmployee(); break;
				
				case 0: System.out.println("프로그램을 종료합니다."); break;
				
				default: System.err.println("\n메뉴에 작성된 번호만 입력해주세요.");
				}
				
			} catch (NumberFormatException e) {
				System.err.println("\n숫자만 입력해주세요.\n");
				
			} catch (IOException e) {
				System.err.println("\n입출력 관련 예외가 발생했습니다.\n");
				
				e.printStackTrace();
				
			} catch (Exception e) {
				System.err.println("\n알 수 없는 예외가 발생했습니다.\n");
				
				e.printStackTrace();
			}
		} while (inputNum != 0);
		
		try {
			if(br != null) br.close();
			
		} catch (IOException e) {
			System.err.println("\n입출력 관련 예외가 발생했습니다.\n");
			
			e.printStackTrace();
		}
	}
	
	/**
	 * 사용자에게 프로그램의 화면을 출력하고 정수를 입력받는 메서드
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public int mainMenu() throws NumberFormatException, IOException {
		System.out.println("[DB Employee 테이블 관리 프로그램]\n");
		
		System.out.println("1. 직원 조회");
		System.out.println("2. 직원 추가");
		System.out.println("0. 프로그램 종료\n");
		
		System.out.print("사용할 메뉴 번호를 입력 >> ");
		int inputNum = Integer.parseInt(br.readLine());
		
		System.out.println();
		
		return inputNum;
	}
	
	private void selectAll() {
		System.out.println("[직원 조회]\n");
		
		List<EmployeeDTO> resultList = emplController.selectAll();
		
		for(int i = 0; i < resultList.size(); i++) {
			EmployeeDTO employee = resultList.get(i);
			
			System.out.print("직원번호: " + employee.getEmpId());
			System.out.print(" / 이름: " + employee.getEmpName());
			System.out.print(" / 주민등록번호: " + employee.getEmpNo());
			System.out.print(" / 이메일: " + employee.getEmail());
			System.out.print(" / 전화번호: " + employee.getPhone());
			System.out.print(" / 부서번호: " + employee.getDeptCode());
			System.out.print(" / 직급번호: " + employee.getJobCode());
			System.out.print(" / 급여등급: " + employee.getSalLevel());
			System.out.print(" / 급여: " + employee.getSalary());
			System.out.print(" / 보너스비율: " + employee.getBonus());
			System.out.print(" / 사수번호: " + employee.getManagerId());
			System.out.print(" / 입사일: " + employee.getHireDate());
			System.out.print(" / 퇴사일: " + employee.getEntDate());
			System.out.print(" / 퇴사여부: " + employee.getEntYN());
			System.out.println();
		}
		
		System.out.println();
	}
	
	private void insertEmployee() throws IOException {
		System.out.println("[직원 추가]\n");
		
		ValidationCheck valCheck = new ValidationCheck();
		
		System.out.print("직원번호 입력 >> ");
		String empId = br.readLine();
		
		if(!valCheck.checkEmpId(empId)) {
			System.err.println("\n잘못된 직원번호 형식이거나 이미 존재하는 직원번호 입니다. 다시 시도하세요.\n");
			
			return;
		}
		
		System.out.print("이름 입력 >> ");
		String empName = br.readLine();
		
		if(!valCheck.checkName(empName)) {
			System.err.println("\n잘못된 이름 형식입니다. 다시 시도하세요.\n");
			
			return;
		}
		
		System.out.print("주민등록번호 입력 >> ");
		String empNo = br.readLine();
		
		if(!valCheck.checkResNo(empNo)) {
			System.err.println("\n잘못된 주민등록번호 형식입니다. 다시 시도하세요.\n");
			
			return;
		}
		
		System.out.print("이메일 입력 >> ");
		String email = br.readLine();
		
		if(!valCheck.checkEmail(email)) {
			System.err.println("\n잘못된 이메일 형식입니다. 다시 시도하세요.\n");
			
			return;
		}
		
		System.out.print("전화번호 입력 >> ");
		String phone = br.readLine();
		
		if(!valCheck.checkPhone(phone)) {
			System.err.println("\n잘못된 전화번호 형식입니다. 다시 시도하세요.\n");
			
			return;
		}
		
		System.out.print("부서코드 입력 >> ");
		String deptCode = br.readLine();
		
		if(!valCheck.checkDeptCode(deptCode)) {
			System.err.println("\n잘못된 부서코드 형식입니다. 다시 시도하세요.\n");
			
			return;
		}
		
		System.out.print("직급코드 입력 >> ");
		String jobCode = br.readLine();
		
		if(!valCheck.checkJobCode(jobCode)) {
			System.err.println("\n잘못된 직급코드 형식입니다. 다시 시도하세요.\n");
			
			return;
		}
		
		System.out.print("급여 입력 >> ");
		int salary = Integer.parseInt(br.readLine());
		
		if(!valCheck.checkSalary(salary)) {
			System.err.println("\n잘못된 급여 형식입니다. 다시 시도하세요.\n");
			
			return;
		}
		
		System.out.print("사수번호 입력 >> ");
		String managerId = br.readLine();
		
		if(!valCheck.checkManagerId(managerId)) {
			System.err.println("\n잘못된 사수번호 형식이거나 존재하지 않는 직원번호 입니다. 다시 시도하세요.\n");
			
			return;
		}
		
		boolean result = emplController.insertEmployee(empId, empName, empNo, email, phone, deptCode, jobCode, salary, managerId);
		
		if(!result) {
			System.err.println("\n" + empName + " 님 추가에 실패했습니다. 다시 시도하세요.\n");
			
			return;
		}
		
		System.out.println("\n" + empName + " 님 추가에 성공했습니다.\n");
	}
}