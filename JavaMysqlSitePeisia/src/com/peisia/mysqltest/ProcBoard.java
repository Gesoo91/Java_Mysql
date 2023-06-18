package com.peisia.mysqltest;

import java.sql.SQLException;

import com.peisia.c.board.display.Disp;
import com.peisia.util.Ci;
import com.peisia.util.Cw;
import com.peisia.util.Db;
//todo : case1,2에 넣은 숫자범위 및 정수아닐때 오류나는거 4,5에도 추가해주기 -> 유효성검사 추가 완료.
@SuppressWarnings("unused")
public class ProcBoard {
	public static void run() {
		
		Disp.showTitle();
		Db.dbInit();
		Cw.wn("글수+댓글수:"+Db.getPostCount());
		loop:while(true) {
			Disp.showMainMenu();
			String cmd=Ci.r("명령입력: ");
			switch(cmd) {
			case "1":	//글리스트
				ProcList.run();
				break;
			case "2":	//글읽기
				ProcRead.run();
				break;
			case "3":	//글쓰기
				ProcWrite.run();
				break;
			case "4":	//글삭제
				ProcDel.run();
				break;
			case "5":	//글수정
				ProcEdit.run();
				break;
			case "6":	//글리스트-검색
				ProcList.search();
			case "0":	//관리자
				break;
			case "e":	//프로그램 종료
				Cw.wn("프로그램종료");
				break loop;
			}
		}
	}
}