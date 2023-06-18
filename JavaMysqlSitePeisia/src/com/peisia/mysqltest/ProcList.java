package com.peisia.mysqltest;

import java.sql.SQLException;

import com.peisia.c.board.display.Disp;
import com.peisia.util.Ci;
import com.peisia.util.Cw;
import com.peisia.util.Db;

public class ProcList {
	static public final int PER_PAGE = 3;
	static int startIndex = 0;		// 현재 페이지의 첫 글 인덱스
	static int currentPage = 1;		// 현재 페이지
	static boolean isSearchMode = false;	// 현재 리스트가 검색 모드인지 구분하는 값
	static int totalPage = 0;
	static int count = 0;
	static String cmd = "";
	
	static public void run() {
		int startIndex = 0;		// 현재 페이지의 첫 글 인덱스
		int currentPage = 1;	// 현재 페이지
		
		////	전체 페이지 수를 구하기	////
		int totalPage = 0;
		if(Db.getPostCount2() % PER_PAGE > 0) {
			totalPage = Db.getPostCount2() / PER_PAGE + 1; //전체 페이지 구하기 : 글수 나누기 per_page(페이지당 글수) 해서 나머지가 나오면(1or2) 토탈+1, 나머지가 0이면 몫=토탈
		}else {
			totalPage = Db.getPostCount2() / PER_PAGE;
		}
		
		Cw.wn("총 페이지 수:"+totalPage);
		
		String cmd;
		while(true) {
			cmd = Ci.r("페이지번호입력[이전 메뉴로:x]:");
			if(cmd.equals("x")) {
				break;
			}
			if (!cmd.matches("\\d+")) { //정수가 아닌 값을 입력시 오류 발생했는데, 이를 방지하기 위해 먼저 입력값이 숫자가 아니면(문자열이면) 재입력하게하기. cmd가 숫자가 아니라면 true
			    Cw.wn("유효한 페이지 번호를 입력해주세요.");
			    continue; 
			}
			currentPage = Integer.parseInt(cmd); //숫자가 아닌 문자가오면 오류발생. 위에 !cmd.matches("\\d+")로 문자열 걸러주기.
			if(currentPage > totalPage || currentPage < 1) { //초과나 미만인 페이지 입력시 다시 입력하게하기.
				Cw.wn("페이지 범위에 맞는 값을 넣어주세요");
				continue;
			}

			startIndex = (currentPage - 1) * PER_PAGE;	// 페이지의 첫 인덱스를 계산해서 저장하기
			Disp.titleList();
			String sql = "select * from board where b_comment is null limit "+startIndex+","+PER_PAGE; // b_comment is null 을 추가해서 댓글은 글리스트에 안보이게 설정(4번이 댓글인데 안나오게됨. 근데 글수는 10이라 애매
			//select*from board where b_comment is null limit 0,3;(보드에서 코맨트가 null인 놈들 3개씩 0[1페이지]페이지를 보여줘라
//			인기글 페이징한다고 하면 select * from board order by b_hit desc limit -,- 해주면 되겠다.
			try {
				Cw.wn("전송한sql문:"+sql);
				Db.result = Db.st.executeQuery(sql);
				while(Db.result.next()) {	// 결과를 하나씩 빼기. 더 이상 없으면(행 수가 끝나면) false 리턴됨.
					String no = Db.result.getString("b_no");
					String title = Db.result.getString("b_title");
					String id = Db.result.getString("b_writerid");
					String datetime = Db.result.getString("b_writetime");
					Cw.wn(no+" "+title+" "+id+" "+datetime);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}			
			
		}
	}
	/* 검색어를 입력받아 리스트-검색 처리 */
	static public void search() {
		cmd=Ci.rl("검색어[x:나가기]");
		if(cmd.equals("x")) {
			return;
		}else {
			searchList(cmd);
		}
	}
	/* 리스트-검색 처리 */
	static public void searchList(String searchWord) {
		////	전체 페이지 수를 구하기	////
		count = Db.getPostCountSearch(searchWord); 
		if(count % PER_PAGE > 0) {
			totalPage = count / PER_PAGE + 1;
		}else {
			totalPage = count / PER_PAGE;
		}
		Cw.wn("총 페이지 수<검색모드>:"+totalPage);
		while(true) {
			cmd = Ci.r("페이지번호입력<검색모드>[이전 메뉴로:x]:");
			if(cmd.equals("x")) {
				break;
			}
			currentPage = Integer.parseInt(cmd);
			if(currentPage > totalPage || currentPage < 1) {
				Cw.wn("페이지 범위에 맞는 값을 넣어주세요");
				continue;
			}
			startIndex = (currentPage - 1) * PER_PAGE;	// 페이지의 첫 인덱스를 계산해서 저장하기
			Disp.titleList();
			String sql = "select * from board where b_comment_number is null"
					+ " and b_title like '%" + searchWord + "%'"
					+ " limit "+startIndex+","+PER_PAGE;
			try {
				Cw.wn("전송한sql문:"+sql);
				Db.result = Db.st.executeQuery(sql);
				while(Db.result.next()) {	// 결과를 하나씩 빼기. 더 이상 없으면(행 수가 끝나면) false 리턴됨.
					String no = Db.result.getString("b_no");
					String title = Db.result.getString("b_title");
					String id = Db.result.getString("b_writerid");
					String datetime = Db.result.getString("b_writetime");
					Cw.wn(no+" "+title+" "+id+" "+datetime);
				}
			} catch (Exception e) {
			}			
		}		
	}
}
