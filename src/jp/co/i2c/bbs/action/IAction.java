package jp.co.i2c.bbs.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface IAction {

	void init(HttpServletRequest request, HttpServletResponse response);
	String performAction() throws Exception;

}
