package com.kh.mvc.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.mvc.model.dto.UserDTO;

/**
 * DAO(Data Access Object)
 * 
 * 데이터베이스 관련된 작업(CRUD)을 전문적으로 담당하는 객체
 * DAO안에 모든 메서드들은 데이터베이스와 관련된 기능으로 만들 것
 * 
 * Controller를 통해 호출된 기능을 수행
 * DB에 직접 접근한 후 SQL문을 수행하고 결과 반환(JDBC)
 */
public class UserDAO {
	/* JDBC용 객체
	 * 
	 * Connection: DB와의 연결정보를 담고 있는 객체(IP, Port, UserName, PW, SID)
	 * Statement: Connection이 가지고 있는 연결정보 DB에 SQL문을 전달하고 실행하고 결과도 받아오는 객체
	 * ResultSet: 실행한 SQL문이 + SELECT문일 경우 조회된 결과가 처음 담기는 객체
	 * 
	 * Prepared Statement: SQL을 미리 준비하는 개념 ?(플레이스 홀더)로 확보해놓은 공간을 사용자가 입력한 값들과 바인딩해서 SQL문 수행
	 * 
	 * 처리 절차
	 * 1) JDBC Driver 등록: 해당 DBMA에서 제공하는 클래스 정보를 동적으로 등록
	 * 2) Connection 객체 생성: 접속하고자 하는 DB의 정보를 입력해서 생성 (url, username, pw)
	 * 3-1) PreparedStatement 객체 생성: Connection 객체를 이용해서 생성(미완성된 SQL문을 미리 전달)
	 * 3-2) SQL문이 미완성일 경우, 미완성된 SQL문을 완성 형태로 만들어주어야 함. 완성되어있는 경우 생략
	 * 4) SQL문을 실행: executeXXX() => SQL을 인자로 전달하지 않음!
	 * 			SELECT(DQL): executeQuery()
	 * 			DML: executeUpdate()
	 * 5) 결과받기:
	 * 			SELECT: ResultSet타입 객체(조회 데이터 담김)
	 * 			DML: int(처리된 행의 개수)
	 * 6-1) ResultSet에 담겨있는 데이터들을 하나하나씩 뽑아서 DTO객체 필드에 옮겨담은 후, 조회 결과가 여러 행일 경우 List로 관리
	 * 6-2) 트랜잭션 처리
	 * 7) (생략가능) 자원반납(close) => 생성의 역순
	 * 8) Controller로 결과 반환
	 * 			SELECT: 6-1에서 만든 거
	 * 			DML: 처리된 행의 개수
	 */
	private final String URL = "jdbc:oracle:thin:@112.221.156.34:12345:XE";
	private final String USER_NAME = "KH13_BJY";
	private final String PASSWORD = "KH1234";
	
	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
		} catch (ClassNotFoundException e) {
				System.out.println("ojdbc가 없거나 오타있는 듯");
		}
	}
	
	public List<UserDTO> selectAll(Connection conn) {
		/* VO / DTO / Entity
		 * 1명의 회원의 정보는 1개의 UserDTO 객체의 필드의 값을 담아야 함
		 */
		List<UserDTO> list = new ArrayList<UserDTO>();
		
		String sql = 
					"SELECT "
				+ 	"USER_NO"
				+ ", USER_ID"
				+ ", USER_PW"
				+ ", USER_NAME"
				+ ", ENROLL_DATE "
				+ "FROM "
				+ 	"TB_USER "
				+ "ORDER BY "
				+ 	"ENROLL_DATE DESC";
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				UserDTO user = new UserDTO();
				
				user.setUserNo(rset.getInt("USER_NO"));
				user.setUserId(rset.getString("USER_ID"));
				user.setUserPW(rset.getString("USER_PW"));
				user.setUserName(rset.getString("USER_NAME"));
				user.setEnrollDate(rset.getDate("ENROLL_DATE"));
				
				list.add(user);
			}
			
		} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("오타 확인해");
				
		} finally {
			try {
				if(rset != null) rset.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch (SQLException e) {
					System.out.println("암튼 이상함");
			}
		}
		
		return list;
	}
	
	public int insertUser(UserDTO user) {
		Connection conn = null;
		
		PreparedStatement pstmt = null;
		
		String sql = """
				INSERT INTO TB_USER
				VALUES (SEQ_USER_NO.NEXTVAL, ?, ?, ?, SYSDATE)
				""";
		
		int result = 0;
		
		try {
			conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPW());
			pstmt.setString(3, user.getUserName());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
				e.printStackTrace();
			
		} finally {
			try {
				if(pstmt != null && !pstmt.isClosed()) pstmt.close();
				
			} catch (SQLException e) {
					e.printStackTrace();
			}
			
			try {
				if(conn != null && !conn.isClosed()) conn.close();
				
			} catch (SQLException e) {
					e.printStackTrace();
			}
		}
		
		return result;
	}
}