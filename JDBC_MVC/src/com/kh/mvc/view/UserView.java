package com.kh.mvc.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.kh.mvc.controller.UserController;
import com.kh.mvc.model.dto.UserDTO;

/**
 * 해당 UserView 클래스는 JDBC 실습을 위해 생성하였으며
 * 사용자에게 입력 및 출력을 수행하는 메서드를 제공
 * 
 * @author: 종로 C강의장
 * @version: 0.1
 * @date: 2025-03-04
 */
public class UserView {
	private Scanner sc = new Scanner(System.in);
	private UserController userController = new UserController();
	
	/**
	 * 프로그램 시작 시 사용자에게 보여줄 메인화면을 출력하는 메서드
	 */
	public void mainMenu() {
		while(true) {
			System.out.println("--- USER테이블 관리 프로그램 ---");
			
			System.out.println();
			
			System.out.println("1. 회원 전체 조회");
			System.out.println("2. 회원 추가");
			System.out.println("0. 프로그램 종료");
			
			System.out.println();
			
			System.out.print("이용할 메뉴 번호 입력 >> ");
			int menuNo = -1;
			try {
				menuNo = sc.nextInt();
				
			} catch (InputMismatchException e) {
					sc.nextLine();
					continue;
			}
			sc.nextLine();
			
			switch(menuNo) {
			case 1: selectAll(); break;
			case 2: insertUser(); break;
			case 0: System.out.println("\n프로그램 종료"); return;
			default: System.out.println("\n잘못된 입력입니다.");
			}
		}
	}
	
	// 회원 전체 정보를 조회해주는 기능
	private void selectAll() {
		System.out.println("\n--- 회원 전체 목록 ---\n");
		
		List<UserDTO> list = userController.selectAll();
		
		System.out.println("조회된 총 회원의 수는 " + list.size() + "명 입니다.\n");
		
		if(!list.isEmpty()) {
			for(UserDTO user : list) {
				System.out.print(user.getUserName() + "님의 정보 !");
				System.out.print("\n아이디: " + user.getUserId());
				System.out.print("\t가입일: " + user.getEnrollDate());
				System.out.println();
				System.out.println();
			}
		} else {
				System.out.println("회원이 존재하지 않습니다.");
		}
	}
	
	// TB_USER에 INSERT할 값을 사용자에게 입력받도록 유도하는 화면
	private void insertUser() {
		System.out.println("\n--- 회원 추가 ---\n");
		
		System.out.print("아이디 입력 >> ");
		String userId = sc.nextLine();
		// 중복검사 필요
		
		System.out.print("비밀번호 입력 >> ");
		String userPW = sc.nextLine();
		
		System.out.print("이름 입력 >> ");
		String userName = sc.nextLine();
		
		int result = userController.insertUser(userId, userPW, userName);
		
		if(result > 0) {
			System.out.println(userName + "님 추가 성공\n");
			
		} else {
				System.out.println("추가 실패! 다시 시도하세요.\n");
		}
	}
}