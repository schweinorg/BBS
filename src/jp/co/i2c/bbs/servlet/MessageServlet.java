package jp.co.i2c.bbs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.i2c.bbs.action.ActionFactory;
import jp.co.i2c.bbs.action.IAction;

public class MessageServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doExecute(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doExecute(request, response);
	}

	/**
	 *
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doExecute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String actionId = request.getParameter("ACTION_ID");
		System.out.println(actionId);
		IAction action = createAction(actionId, request, response);

		String path = "";
		try {

			path = action.performAction();

		} catch (Exception exception) {
			throw new ServletException(exception);
		}

		request.getRequestDispatcher(path).forward(request, response);
	}

	/**
	 * アクションクラスを生成します。
	 *
	 * @param actionId
	 * @param request
	 * @param response
	 * @return
	 */
	private IAction createAction(String actionId, HttpServletRequest request,
			HttpServletResponse response) {

		IAction action = ActionFactory.createAction(actionId);

		action.init(request, response);

		return action;
	}
}
