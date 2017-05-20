package jp.co.i2c.bbs.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.i2c.bbs.util.DBUtil;

/**
 * 投稿処理クラスです。
 *
 */
public class ContributeAction extends AbstractAction {

	@Override
	protected String execute(Connection connection) throws SQLException {

		//HttpServletRequest request = super.getRequest();

		final String NONAME = "名無しさん";
		final String PATH = "jsp/index.html";

        PreparedStatement pstmt = null;

        final String SQL = "insert into posting_table " +
        		"(POSTING_ID,POSTER,TITLE,MESSAGE,DELETE_KEY)" +
        		" values" +
        		"(POSTING_TABLE_SEQ.NEXTVAL,?,?,?,?)";

        //フォームに入力された値を取得
        String poster = getParameter("name");
        String title = getParameter("title");
        String message = getParameter("tokobun");
        String delkey = getParameter("delkey");

//        String poster = request.getParameter("name");
//        String title = request.getParameter("title");
//        String message = request.getParameter("tokobun");
//        String delkey = request.getParameter("delkey");
        //名前未入力なら名無しさんにする（DBのデフォルト値でうまくいかなかった）
        if (poster == null || poster.isEmpty()){
        	poster = NONAME;
        }

        try{
        	pstmt = connection.prepareStatement(SQL);

        	pstmt.setString(1, poster);
        	pstmt.setString(2, title);
        	pstmt.setString(3, message);
        	pstmt.setString(4, delkey);

            int ins = 0;
        	ins = pstmt.executeUpdate();
        	System.out.println(ins);
        	connection.commit();

        } catch(SQLException sqle){
        	DBUtil.rollback(connection);
        	sqle.printStackTrace();
        } finally {
        	try{
        		DBUtil.close(pstmt);
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        }

		return PATH;
	}
}
