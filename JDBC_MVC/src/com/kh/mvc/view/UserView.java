package com.kh.mvc.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.kh.mvc.controller.UserController;
import com.kh.mvc.model.dto.UserDTO;
import com.kh.mvc.util.JdbcUtil;

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
			System.out.println("--- USER 테이블 관리 프로그램 ---");
			
			System.out.println();
			
			System.out.println("1. 회원 전체 조회");
			System.out.println("2. 회원 추가");
			System.out.println("3. 비밀번호 수정");
			System.out.println("4. 회원 삭제");
			System.out.println("5. 회원 번호로 회원 조회");
			System.out.println("6. 회원 아이디로 회원 조회");
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
			case 3: updatePw(); break;
			case 4: deleteUser(); break;
			case 5: selectUserNo(); break;
			case 6: selectUserId(); break;
			case 0: System.out.println("\n프로그램 종료"); return;
			default: System.out.println("\n잘못된 입력입니다.");
			}
		}
	}
	
	/**
	 * 회원 전체 정보를 조회하는 과정을 출력하는 메서드
	 */
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
	
	/**
	 * TB_USER에 INSERT할 값을 사용자에게 입력받도록 유도하는 메서드
	 */
	private void insertUser() {
		System.out.println("\n--- 회원 추가 ---\n");
		
		System.out.print("아이디 입력 >> ");
		String userId = sc.nextLine();
		
		// Id 중복 검사
		if(userController.selectId(userId)) {
			System.out.println("이미 사용중인 아이디 입니다. 다른 아이디로 다시 시도해주세요.");
			
			return;
		}
		
		// Id 길이 초과 검사
		if(!JdbcUtil.checkIdPwForm(userId)) {
			System.out.println("아이디는 30자 이내여야 합니다. 다른 아이디로 다시 시도해주세요.");
			
			return;
		}
		
		System.out.print("비밀번호 입력 >> ");
		String userPW = sc.nextLine();
		
		// Pw 길이 초과 검사
		if(!JdbcUtil.checkIdPwForm(userPW)) {
			System.out.println("비밀번호는 30자 이내여야 합니다. 다른 비밀번호로 다시 시도해주세요.");
			
			return;
		}
		
		System.out.print("이름 입력 >> ");
		String userName = sc.nextLine();
		
		if(userController.insertUser(userId, userPW, userName) > 0) {
			System.out.println("\n" + userName + " 님 추가에 성공했습니다.\n");
			
		} else {
				System.out.println("\n추가에 실패했습니다. 다시 시도하세요.\n");
		}
	}
	
	/**
	 * 비밀번호 수정 과정을 출력하는 메서드
	 */
	private void updatePw() {
		System.out.println("\n--- 비밀번호 수정 ---\n");
		
		System.out.print("아이디 입력 >> ");
		String userId = sc.nextLine();
		
		System.out.print("현재 비밀번호 입력 >> ");
		String userBeforePW = sc.nextLine();
		
		// 회원 정보가 일치하는지 확인하고 일치하면 진행 아니면 리턴
		if(userController.checkUser(userId, userBeforePW).isEmpty()) {
			System.out.println("\n일치하는 회원 정보가 존재하지 않습니다.\n");
			
			return;
		}
		
		System.out.print("\n수정할 비밀번호 입력 >> ");
		String userAfterPW = sc.nextLine();
		
		// 입력한 새로운 비밀번호를 DB로 넘겨서 업데이트하고 실패하면 리턴
		if(userController.updatePw(userId, userAfterPW) == 0) {
			System.out.println("\n비밀번호 수정에 실패했습니다.\n");
			
			return;
		}
		
		System.out.println("\n비밀번호가 수정되었습니다.\n");
	}
	
	/**
	 * 회원 삭제 과정을 출력하는 메서드
	 */
	private void deleteUser() {
		System.out.println("\n--- 회원 삭제 ---\n");
		
		System.out.print("아이디 입력 >> ");
		String userId = sc.nextLine();
		
		System.out.print("비밀번호 입력 >> ");
		String userPw = sc.nextLine();
		
		List<UserDTO> list = userController.checkUser(userId, userPw);
		
		// 회원 정보가 일치하는지 확인하고 일치하면 회원의 이름을 확인 아니면 리턴
		if(list.isEmpty()) {
			System.out.println("\n일치하는 회원 정보가 존재하지 않습니다.\n");
				
			return;
		}
			
		UserDTO user = list.get(0);
		
		String userName = user.getUserName();
			
		System.out.print("\n" + userName + " 님의 정보를 삭제합니까? (Y/N)\n");
		char response = sc.next().toUpperCase().charAt(0);
		sc.nextLine();
		
		if(response == 'N') {
			System.out.println("회원 삭제를 취소하고 초기화면으로 돌아갑니다.");
			
			return;
			
		} else if(response == 'Y') {
			// 리턴 받은 값이 0일 경우 삭제 실패
			if(userController.deleteUser(userId, userPw, userName) == 0) {
				System.out.println("회원 삭제를 실패했습니다.");
				
				return;
			}
			
			System.out.println("회원 삭제에 성공했습니다.");
			
			return;
		}
		
		System.out.println("Y 또는 N 만 입력하세요.");
	}
	
	/**
	 * 회원 번호로 회원을 검색하는 과정을 출력하는 메서드
	 */
	public void selectUserNo() {
		System.out.println("\n--- 회원 번호로 회원 조회 ---\n");
		
		System.out.print("회원 번호 입력 >> ");
		int userNo = sc.nextInt();
		
		UserDTO user = userController.selectUserNo(userNo);
		
		System.out.println();
		
		if(user.getUserId() == null) {
			System.out.println("입력한 회원 번호에 해당하는 회원이 존재하지 않습니다.\n");
			
			return;
		}
		
		System.out.print(user.getUserName() + "님의 정보 !");
		System.out.print("\n회원 번호: " + user.getUserNo());
		System.out.print("\t아이디: " + user.getUserId());
		System.out.print("\t가입일: " + user.getEnrollDate());
		System.out.println();
		System.out.println();
	}
	
	/**
	 * 아이디로 회원을 검색하는 과정을 출력하는 메서드
	 */
	public void selectUserId() {
		System.out.println("\n--- 아이디로 회원 조회 ---\n");
		
		System.out.print("아이디 입력 >> ");
		String userId = sc.nextLine();
		
		UserDTO user = userController.selectUserId(userId);
		
		System.out.println();
		
		if(user.getUserId() == null) {
			System.out.println("입력한 회원 번호에 해당하는 회원이 존재하지 않습니다.\n");
			
			return;
		}
		
		System.out.print(user.getUserName() + "님의 정보 !");
		System.out.print("\n회원 번호: " + user.getUserNo());
		System.out.print("\t아이디: " + user.getUserId());
		System.out.print("\t가입일: " + user.getEnrollDate());
		System.out.println();
		System.out.println();
	}
}