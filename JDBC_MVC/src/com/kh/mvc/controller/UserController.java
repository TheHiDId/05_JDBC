package com.kh.mvc.controller;

import java.util.List;

import com.kh.mvc.model.dto.UserDTO;
import com.kh.mvc.model.service.UserService;

/**
 * View에서 온 요청을 처리해주는 클래스
 * 메서드로 전달된 데이터값을 가공처리한 후 DAO로 전달
 * DAO로부터 반환받은 결과를 View에 반환
 */
public class UserController {
	private UserService userService = new UserService();
	
	public List<UserDTO> selectAll() {
		return userService.selectAll();
	}
	
	public int insertUser(String userId, String userPW, String userName) {
		UserDTO user = new UserDTO();
		
		user.setUserId(userId);
		user.setUserPW(userPW);
		user.setUserName(userName);
		
		return userService.insertUser(user);
	}
	
	public int deleteUser(String userId, String userPw, String userName) {
		UserDTO user = new UserDTO();
		
		user.setUserId(userId);
		user.setUserPW(userPw);
		user.setUserName(userName);
		
		return userService.deleteUser(user);
	}
	
	public List<UserDTO> checkUser(String userId, String userPw) {
		UserDTO uncheckedUser = new UserDTO();
		
		uncheckedUser.setUserId(userId);
		uncheckedUser.setUserPW(userPw);
		
		return userService.checkUser(uncheckedUser);
	}
	
	public int updatePw(String userId, String userPW) {
		UserDTO newPwInfo = new UserDTO();
		
		newPwInfo.setUserId(userId);
		newPwInfo.setUserPW(userPW);
		
		return userService.updatePw(newPwInfo);
	}
	
	public boolean selectId(String userId) {
		return userService.selectId(userId);
	}
	
	public UserDTO selectUserNo(int userNo) {
		return userService.selectUserNo(userNo);
	}
	
	public UserDTO selectUserId(String userId) {
		return userService.selectUserId(userId);
	}
}