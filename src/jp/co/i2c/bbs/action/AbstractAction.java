package jp.co.i2c.bbs.action;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.i2c.bbs.util.DBUtil;

/**
 * 業務ロジッククラスの基底です。
 *
 *
 */
public abstract class AbstractAction implements IAction {

	/** リクエスト */
	private HttpServletRequest _request = null;
	/** レスポンス */
	private HttpServletResponse _response = null;

	String realPath = "";

	@Override
	public void init(HttpServletRequest request, HttpServletResponse response) {
		realPath = request.getServletContext().getRealPath("WEB-INF/Constants.properties");
		System.out.println("realpath :" + realPath);
		//request.setAttribute("REAL_PATH", realPath);
		setRequest(request);
		setResponse(response);
	}

	@Override
	public String performAction() throws Exception {

		preProcess();

		Connection connection = DBUtil.getConnection(realPath);

		String path = "";

		try {
			path = execute(connection);
		} finally {
			DBUtil.close(connection);
		}

		postProcess();

		return path;
	}

	protected void preProcess(){
	}
	protected void postProcess(){
	}

	/**
	 * 業務ロジック処理を行います。
	 *
	 * @param connection
	 * @param context
	 * @return 遷移先画面のパス
	 */
	protected abstract String execute(Connection connection) throws Exception ;

	/**
	 * <p>リクエストを返します。</p>
	 *
	 * @return _request
	 */
	public HttpServletRequest getRequest() {
		return _request;
	}

	/**
	 * <p></p>
	 *
	 * @param request
	 */
	public void setRequest(HttpServletRequest request) {
		_request = request;
	}

	/**
	 * <p>レスポンスを返します。</p>
	 *
	 * @return _response
	 */
	public HttpServletResponse getResponse() {
		return _response;
	}

	/**
	 *
	 * @param response
	 */
	public void setResponse(HttpServletResponse response) {
		_response = response;
	}

	/**
	 *
	 * @param key
	 * @param obj
	 */
	public void setAttribute(String key, Object obj) {
		_request.setAttribute(key, obj);
	}

	/**
	 *
	 * @param key
	 * @return
	 *
	 */
	public Object getAttribute(String key) {
		return _request.getAttribute(key);
	}

	/**
	 *
	 * @param key
	 * @return
	 */
	public String getParameter(String key) {
		return _request.getParameter(key);
	}
}
