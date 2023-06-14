package com.peisia.mysqltest;

import com.peisia.util.Ci;
import com.peisia.util.Cw;
import com.peisia.util.Db;

public class ProcDel {
	public static void run() {
		
		String delNo = Ci.r("삭제할 글번호를 입력해주세요:");
		int intCount = Db.getPostCount();
		int selectNo = Integer.parseInt(delNo);
		if(!delNo.matches("\\d+")) {
			Cw.wn("글번호를 제대로 입력해 주세요.");
			return;
		}
		
		else if(intCount<selectNo || selectNo<1) {
			Cw.wn("글번호를 1부터"+intCount+"안으로 입력하세요.");
			return;
		}
//		return으로 만들어 봣지만 try catch문을 사용하지 않고서는 정수가 문자열로 입력됐을때 나오는 오류를 잡기 어렵다.
		Db.dbExecuteUpdate("delete from board where b_no="+delNo);
	}
}
