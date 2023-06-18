package com.peisia.mysqltest;

import com.peisia.site.SiteMain;
import com.peisia.util.Ci;
import com.peisia.util.Cw;
import com.peisia.util.Db;

public class ProcDel {
	public static void run() {
		
		String delNo = Ci.r("삭제할 글번호를 입력해주세요:");
//		int intCount = Db.getPostCount();
//		int selectNo = Integer.parseInt(delNo);
//		if(!delNo.matches("\\d+")) {
//			Cw.wn("글번호를 제대로 입력해 주세요.");
//			return;
//		}
		
//		else if(intCount<selectNo || selectNo<1) {
//			Cw.wn("글번호를 1부터"+intCount+"안으로 입력하세요.");
//			return;
//		}
//		return으로 만들어 봣지만 try catch문을 사용하지 않고서는 정수가 문자열로 입력됐을때 나오는 오류를 잡기 어렵다.
		Cw.wn("전송한sql문:"+"delete from board where b_no="
		+delNo+ " and b_writerid='"+SiteMain.loginedId+"'");
		Db.dbExecuteUpdate("delete from board where b_no= "
		+ delNo + " and b_writerid='"+SiteMain.loginedId+"'");
//		delete from board where b_no="1"and b_writerid = "login id); 지울 글 번호와 로그인아이디가 같은 녀석을 삭제해라. = 아이디가 같아야 삭제가능
//		sql문에서 띄어쓰기 주의. 스프링에서는 따로 설정파일에 기입하므로 실수가 조금 줄어들 수 있다. " and b_writerid= 을 "and b_writerid= 으로 작성해서 오류났엇음
		
	}
}
