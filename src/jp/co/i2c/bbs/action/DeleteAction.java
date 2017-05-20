package jp.co.i2c.bbs.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.i2c.bbs.util.DBUtil;


/**
 * 投稿削除クラスです。
 *
 */
public class DeleteAction extends AbstractAction {

	private static final String PATH = "jsp/index.html";
	private static final String ERRPATH = "jsp/sakujo.jsp";
    private static final String DELSQL = "update POSTING_TABLE " +
    		"set delete_flg = 1 " +
    		"where posting_id = ? and delete_key = ?";

    private static final String SELSQL = "select posting_id, delete_key from POSTING_TABLE where posting_id = ?";

	@Override
	protected String execute(Connection connection) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rst = null;

        String postid = getParameter("delid");
        String indelkey = getParameter("delkey");
        String delkey = (String)getAttribute("DEL_KEY");

        System.out.println(postid);
        System.out.println(indelkey);
        System.out.println(delkey);

        if(indelkey.equals(delkey)){
	        try{
	            int upd = 0;
	        	pstmt = connection.prepareStatement(DELSQL);
	        	pstmt.setString(1, postid);
	        	pstmt.setString(2, indelkey);
	        	upd = pstmt.executeUpdate();
	        	System.out.println(upd);
	        	connection.commit();

	        } catch(SQLException sqle){
				DBUtil.rollback(connection);
	        	sqle.printStackTrace();
	        } finally {
	        	DBUtil.close(pstmt);
	        }
	        return PATH;
        }else{
        	pstmt = connection.prepareStatement(SELSQL);
        	pstmt.setString(1, postid);
        	rst = pstmt.executeQuery();
        	rst.next();

        	System.out.println(rst.getString(1));

        	setAttribute("DEL_ID",rst.getString(1));
        	setAttribute("DEL_KEY",rst.getString(2));
        	setAttribute("STATUS","NG");
        	return ERRPATH;
        }
	}

}
