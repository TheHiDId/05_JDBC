package com.kh.mvc.model.service;

import java.util.List;

import com.kh.mvc.model.dao.UserDAO;
import com.kh.mvc.model.dto.UserDTO;
import com.kh.mvc.util.JdbcUtil;

/**
 * Service: 비즈니스로직 / 의사결정코드를 작성하는 부분
 * Controller에서는 Service단의 메서드를 호출
 * Service에서 실질적인 동작을 시켜야하는 코드를 작성
 * => Service단을 추가함으로 DAO는 순수하게 SQL문을 처리하는 부분만 남겨놓을 것
 */
public class UserService {
	private UserDAO userDao = new UserDAO();
	
	public List<UserDTO> selectAll() {
		return userDao.selectAll(JdbcUtil.getConnection());
	}
	
	public int insertUser(UserDTO user) {
		return userDao.insertUser(JdbcUtil.getConnection(), user);
	}
	
	public List<UserDTO> checkUser(UserDTO uncheckedUser) {
		return userDao.checkUser(JdbcUtil.getConnection(), uncheckedUser);
	}
	
	public int updatePw(UserDTO newPwInfo) {
		return userDao.updatePw(JdbcUtil.getConnection(), newPwInfo);
	}
	
	public boolean selectId(String userId) {
		return userDao.selectId(JdbcUtil.getConnection(), userId);
	}
	
	public int deleteUser(UserDTO user) {
		return userDao.deleteUser(JdbcUtil.getConnection(), user);
	}
	
	public UserDTO selectUserNo(int userNo) {
		return userDao.selectUserNo(JdbcUtil.getConnection(), userNo);
	}
	
	public UserDTO selectUserId(String userId) {
		return userDao.selectUserId(JdbcUtil.getConnection(), userId);
	}
}
