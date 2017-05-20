package jp.co.i2c.bbs.util;

import java.sql.Connection;

import jp.co.i2c.bbs.action.AbstractAction;

public class DBTest extends AbstractAction {



	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

	@Override
	protected String execute(Connection connection) throws Exception {
		String path = "C:\\Users\\wdev_otsuka\\workspace\\BulletinBoard\\WebContent\\WEB-INF\\Constants.properties";
		connection = DBUtil.getConnection(path);
		DBUtil.close(connection);
		return null;
	}

}
