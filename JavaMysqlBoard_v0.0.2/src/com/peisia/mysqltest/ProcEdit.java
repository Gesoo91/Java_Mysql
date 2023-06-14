package com.peisia.mysqltest;

import com.peisia.util.Ci;
import com.peisia.util.Db;

public class ProcEdit {
	public static void run() {
		String editNo = Ci.r("수정할 글번호를 입력해주세요:");
		String edTitle = Ci.rl("제목을 입력해주세요:");
		String edId = Ci.rl("작성자id를 입력해주세요:");				
		String edContent = Ci.rl("글내용을 입력해주세요:");
		Db.dbExecuteUpdate("update board set b_title='"+edTitle+"',b_writerid='"+edId+"',b_writetime=now(),b_contnet='"+edContent+"' where b_no="+editNo);
		/*String.format형태로 써주기
		 * String sq13 = String.format(
				"update board set b_title='%s', b_writerid='%s', b_writetime=now(), b_contnet='%s' where b_no=%d",
                edTitle,
                edId,
                edContent,
                editNo
				);
				*/
//		이러한 입력방식은 sql삽입공격에 취약하다고 한다.  예를들어 입력값에 drop table ~~같은것을 기입하면 서버에 테이블이 지워지니까
//		이를 방지하기 위해 Prepared Statement를 사용하여 미리 컴파일하고 실행시에는 파라미터를 전달하는 방식으로 하는게 좋다.
		/*
	case "5": // 글수정
	    String editNo = Ci.r("수정할 글번호를 입력해주세요:");
	    String edTitle = Ci.rl("제목을 입력해주세요:");
	    String edId = Ci.rl("작성자id를 입력해주세요:");
	    String edContent = Ci.rl("글내용을 입력해주세요:");

	    try {
	        String query = "UPDATE board SET b_title=?, b_writerid=?, b_writetime=now(), b_content=? WHERE b_no=?"; //쿼리준비. 변경값에 대한 placeholder로 ?사용
	        PreparedStatement pstmt = Db.conn.prepareStatement(query); //객체생성, Db.conn은 데이터 베이스 연결을 나타내는 Connection 객체.
	        pstmt.setString(1, edTitle); //첫번째 ?에 들어갈 값. 숫자입력시에는 setInt메서드 사용
	        pstmt.setString(2, edId);
	        pstmt.setString(3, edContent);
	        pstmt.setInt(4, Integer.parseInt(editNo));
	        pstmt.executeUpdate(); //쿼리 실행. 데이터베이스에 해당 내용이 수정됨.
	        pstmt.close(); //객체를 닫아서 리소스 누수 방지. 여기에 더해 입력값 유효성검증, 입력값 이스케이프처리, 최소권한부여, 보안패치 및 업데이트, 웹방화벽 및 보안솔루션 등을 사용하여 추가 보안을 챙긴다고 한다.

			Cw.wn("글 수정 완료");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		break;
		 */
	}
}
