package com.peisia.mysqltest;

import java.sql.SQLException;

import com.peisia.util.Ci;
import com.peisia.util.Cw;
import com.peisia.util.Db;

public class ProcRead {
	static public void run() {
	while(true) {
		String readNo = Ci.r("읽을 글 번호를 입력해주세요:"+"0부터"+Db.getPostCount()+"까지 중에 선택/"+"뒤로가기[x]");
		if(readNo.equals("x")) {
			break;
		}
		else if(!readNo.matches("\\d+")) {
			Cw.wn("유효한 글 번호를 입력해주세요.");
		}
		else {
			try {
				int intCount = Db.getPostCount();
                int selectedNo = Integer.parseInt(readNo);
                if (selectedNo >= 0 && selectedNo <= intCount) {
					Db.result = Db.st.executeQuery("select * from board where b_no ="+readNo);
					Db.result.next();	// 결과를 하나씩 빼기. 더 이상 없으면(행 수가 끝나면) false 리턴됨.
					String title = Db.result.getString("b_title");	// p_name 필드(열) 의 데이터 꺼내기(1개 꺼낸거에서)
					String content = Db.result.getString("b_content");	// p_name 필드(열) 의 데이터 꺼내기(1개 꺼낸거에서)
					Cw.wn("글제목: "+title);
					Cw.wn("글내용: "+content);
                }
                else {
                	Cw.wn("유효한 글번호를 써주세요.");
                }
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	}

}
