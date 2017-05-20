package jp.co.i2c.bbs.action;

import java.sql.Connection;


public class DummyAction extends AbstractAction {

	@Override
	protected String execute(Connection connection) {
		// TODO 自動生成されたメソッド・スタブ
		return "/index.jsp";
	}

}
