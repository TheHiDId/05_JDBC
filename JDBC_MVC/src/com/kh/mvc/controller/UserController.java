package com.kh.mvc.controller;

import java.util.List;

import com.kh.mvc.model.dao.UserDAO;
import com.kh.mvc.model.dto.UserDTO;
import com.kh.mvc.model.service.UserService;

/**
 * View에서 온 요청을 처리해주는 클래스
 * 메서드로 전달된 데이터값을 가공처리한 후 DAO로 전달
 * DAO로부터 반환받은 결과를 View에 반환
 */
public class UserController {
	private UserDAO userDao = new UserDAO();
	private UserService userService = new UserService();
	
	public List<UserDTO> selectAll() {
		return userService.selectAll();
	}
	
	public int insertUser(String userId, String userPW, String userName) {
		UserDTO user = new UserDTO();
		
		user.setUserId(userId);
		user.setUserPW(userPW);
		user.setUserName(userName);
		
		return userDao.insertUser(user);
	}
}