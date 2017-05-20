package jp.co.i2c.bbs.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.i2c.bbs.util.DBUtil;

/**
 * 投稿削除画面遷移クラスです。
 *
 */
public class TranDelAction extends AbstractAction {

	private static final String PATH = "jsp/sakujo.jsp";
    private static final String SQL = "select delete_key from POSTING_TABLE where posting_id = ?";

	@Override
	protected String execute(Connection connection) throws SQLException {

        ResultSet rst = null;
        PreparedStatement pstmt = null;

        String delid = getParameter("DEL_ID");

        try{

        	pstmt = connection.prepareStatement(SQL);
        	pstmt.setString(1, delid);
        	rst = pstmt.executeQuery();
        	rst.next();
        	String delkey = rst.getString(1);

        	System.out.println("select:" + delkey);

        	setAttribute("DEL_KEY",delkey);

        } catch(SQLException sqle){
        	sqle.printStackTrace();
        } finally {
        	try{
        		if(rst != null){
        			rst.close();
        			System.out.println("ResultSetClose");
        		} else {
        			System.out.println(rst);
        		}

    			DBUtil.close(pstmt);
    			System.out.println("StatementClose");

        	}catch(Exception e){
        		e.printStackTrace();
        	}
        }
		return PATH;
	}

}
