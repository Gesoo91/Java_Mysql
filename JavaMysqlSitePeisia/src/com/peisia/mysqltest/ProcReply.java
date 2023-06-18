package com.peisia.mysqltest;

import java.sql.SQLException;

import com.peisia.site.SiteMain;
import com.peisia.util.Ci;
import com.peisia.util.Cw;
import com.peisia.util.Db;

public class ProcReply {
	public static void list(int b_comment_number) {
		String commentNo ="select * from board where b_comment_number="+b_comment_number; 
		try {
			Cw.wn("전송한sql문:"+commentNo);
			Db.result = Db.st.executeQuery(commentNo);
			while(Db.result.next()) { //ProcRead 처럼 그냥 while 아래쪽에쓰고 while(true) 로 했을때는 오류남.
//				원인은 Db.result.next()를 와일문 안에서 호출했는데 더이상 결과행이 없는데도 계속 next()를 호출해서 행을 가져오려고 했어서 그럼. [while문이 있냐 없냐의 차이]
//				따라서 결과행을 모두 순회한 이후에는 next()를 호출하지 않도록 while문이 true일때만 next를 가져오라고 while(Db.result.next())에 집어넣음
//				기존의 while문에 break; 하는 형태도 가능하다. 각각의 장단점이 있다.
//				이 방법은 코드가 간결해지고 결과집합에서 다음행으로 이동하며 루프가 실행되며 / 다음행으로 이동하는 작업이 루프 내부에 있어 추가성능손실이 있을수 있고,
//				후자의 방법은 코드가 더 직관적이고 명확하여 루프 실행 전에 결과가 있는지 확인할 수 있으나 / 약간의 추가코드를 써야만 한다.(break문)
				
				
				String b_id = Db.result.getString("b_writerid");
				String comment = Db.result.getString("b_comment");
//				String commentId = Db.result.getString("b_commnet_number");
				Cw.wn("댓글내용:"+comment+"-"+b_id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void write(int b_comment_number) {
//		String cmd = "select * from board where b_comment_number="+b_comment_number; //insert into board (,,) values(,,)해줘야할듯?
		try {
//			댓글에 들어가야할 것들 = b_comment_hit,b_comment_number,b_comment,b_writerid
//			Db.result = Db.st.executeQuery(cmd);
			
			String b_comment_writer = Ci.rl("댓글 id:");
			String b_comment_text = Ci.rl("댓글 쓰기:");
			Db.st.executeUpdate("insert into board (b_writerid,b_writetime,b_comment_number,b_comment)"
			+ "values ('"
					+SiteMain.loginedId
					+"',now(),"+b_comment_number+",'"+b_comment_text+"')");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
