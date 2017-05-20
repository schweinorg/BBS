package jp.co.i2c.bbs.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import jp.co.i2c.bbs.data.ContributeData;
import jp.co.i2c.bbs.util.DBUtil;

/**
 * 投稿一覧表示クラスです。
 *
 */
public class ViewAction extends AbstractAction {

	private static final String PATH = "jsp/view.jsp";
    private static final String SQL = "select posting_id, " +
    		"poster, " +
    		"title, " +
    		"message, " +
    		"posting_date, " +
    		"delete_key," +
    		" delete_flg " +
    		"from POSTING_TABLE " +
    		"where delete_flg = ? " +
    		"order by to_number(POSTING_ID)";

	@Override
	protected String execute(Connection connection) throws SQLException {

        ResultSet rst = null;
        PreparedStatement pstmt = null;
        ArrayList<ContributeData> array = new ArrayList<ContributeData>();

        try{

        	pstmt = connection.prepareStatement(SQL);
        	pstmt.setString(1, "0");
        	rst = pstmt.executeQuery();
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy'年'MM'月'dd'日'HH'時'mm'分'ss'秒'");

        	while(rst.next()){

                ContributeData contribute = new ContributeData();

        		contribute.setPostid(rst.getString(1));
        		contribute.setPoster(sanitizing(rst.getString(2)));
        		contribute.setTitle(sanitizing(rst.getString(3)));
        		contribute.setMessage(sanitizing(rst.getString(4)));
        		contribute.setPostdate(sdf.format(rst.getTimestamp(5)));
        		contribute.setDelkey(sanitizing(rst.getString(6)));
        		contribute.setDelflg(rst.getString(7));
        		array.add(contribute);
        	}

        	setAttribute("contdata",array);

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

	/**
	 * タグを無害化します。
	 * @param str
	 * @return
	 */
    public String sanitizing(String str) {

    if (null == str || "".equals(str)) {
    return str;
    }
    str = str.replaceAll("&", "&amp;");
    str = str.replaceAll("<", "&lt;");
    str = str.replaceAll(">", "&gt;");
    str = str.replaceAll("\"", "&quot;");
    str = str.replaceAll("'", "&#39;");
    str = str.replaceAll("\n", "<br>");
    return str;
    }
}
