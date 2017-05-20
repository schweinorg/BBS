package jp.co.i2c.bbs.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * データベースに関連するユーティリティクラスです。
 *
 *
 */
public final class DBUtil {

	/**
	 * コネクションを閉じます。
	 *
	 * @param connection
	 */
	public static void close(Connection connection) {
		try {
			if (connection != null){
				connection.close();
				System.out.println("正常に切断されました。");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ステートメントを閉じます。
	 *
	 * @param pstate
	 */
	public static void close(PreparedStatement pstate) {
		try {
			if (pstate != null) {
				pstate.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ロールバックを行います。
	 *
	 * @param connection
	 */
	public static void rollback(Connection connection) {
		try{
			if ( connection != null) {
				connection.rollback();
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}


	/**
	 * コネクションを取得します。
	 *
	 * @param realpath
	 * @return conn
	 */
    public static synchronized Connection getConnection(String realpath) {

        Properties config = new Properties();
        InputStream is = null;
        //String realpath = (String)request.getAttribute("REAL_PATH");

        System.out.println("path :" + realpath);

        try {
            // プロパティファイルのパスを取得する
			is = new FileInputStream(new File(realpath));
			config.load(is);
        } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

        String url = config.getProperty("URL");
        String usr = config.getProperty("USR");
        String pass = config.getProperty("PASSWORD");
        String driver = config.getProperty("DRIVER");
        boolean acommit = Boolean.valueOf(config.getProperty("COMM"));

        Connection conn = null;

        try {
            Class.forName(driver);
            System.out.println("ドライバーのロードに成功しました。");
            conn = DriverManager.getConnection(url, usr, pass);
            System.out.println("データベースに接続しました。");
            conn.setAutoCommit(acommit);
        } catch (ClassNotFoundException ce) {
        	ce.printStackTrace();
        } catch (Exception e){
            System.out.println("Exception:" + e.getMessage());
        }
    	return conn;
      }
}
