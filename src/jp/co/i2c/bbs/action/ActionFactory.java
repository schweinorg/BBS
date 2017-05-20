package jp.co.i2c.bbs.action;

public final class ActionFactory {


	/**
	 * 指定されたアクションクラスを生成します。
	 *
	 * @param actionId
	 * @return 指定されたアクションクラス
	 */
	public static IAction createAction(String actionId) {

		IAction action = null;

//		if ("MENU".equals(actionId)) {
//			action = new MenuAction();
//		}

		if ("DUMMY".equals(actionId)) {
			action = new DummyAction();
		}

		if ("TRANCONT".equals(actionId)) {
			action = new TranContAction();
		}

		if ("CONTRIBUTE".equals(actionId)) {
			action = new ContributeAction();
		}

		if ("VIEW".equals(actionId)) {
			action = new ViewAction();
		}

		if ("DELETE".equals(actionId)) {
			action = new DeleteAction();
		}

		if ("TRANDEL".equals(actionId)) {
			action = new TranDelAction();
		}

		return action;
	}

}
