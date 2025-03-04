package com.kh.mvc.run;

import com.kh.mvc.view.UserView;

public class Run {
	public static void main(String[] args) {
		/* Model: 데이터와 관련된 모든 작업
		 * View: 화면 상 입/출력
		 * Controller: 뷰에서의 요청을 받아서 처리해주는 역할
		 */
		
		new UserView().mainMenu();
	}
}