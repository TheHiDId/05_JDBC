package com.kh.mvc.employee.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.kh.mvc.employee.controller.EmployeeController;

public class EmployeeView {
	// Controller 객체
	private EmployeeController controller = null;
	// 스캐너 대신 사용할 BufferedReader
	private BufferedReader br = null;
	
	/**
	 * EmployeeView가 생성될 때, 
	 * EmployeeController와 BufferedReader 객체 생성, 
	 * 예외 발생 시 프로그램 종료
	 */
	public EmployeeView() {
		try {
			controller = new EmployeeController();
			
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
				case 1: break;
				
				case 0: System.out.println("프로그램을 종료합니다."); break;
				
				default: System.err.println("메뉴에 작성된 번호만 입력해주세요.\n");
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
		
		System.out.println("1. ");
		System.out.println("0. 프로그램 종료\n");
		
		System.out.print("사용할 메뉴 번호를 입력 >> ");
		int inputNum = Integer.parseInt(br.readLine());
		
		System.out.println();
		
		return inputNum;
	}
}