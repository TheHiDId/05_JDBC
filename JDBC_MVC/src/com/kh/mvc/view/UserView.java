package com.kh.mvc.view;

import java.util.Scanner;

import com.kh.mvc.controller.UserController;

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
			
			System.out.print("이용할 메뉴 번호 입력>> ");
			int menuNo = sc.nextInt();
			sc.nextLine();
			
			switch(menuNo) {
			case 1: selectAll(); break;
			case 0: System.out.println("프로그램 종료"); return;
			default: System.out.println("잘못된 입력입니다.");
			}
		}
	}
	
	// 회원 전체 정보를 조회해주는 기능
	private void selectAll() {
		System.out.println("--- 회원 전체 목록 ---");
		
		userController.selectAll();
	}
}