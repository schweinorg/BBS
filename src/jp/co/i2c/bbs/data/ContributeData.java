package jp.co.i2c.bbs.data;

import java.io.Serializable;

/**
 * データ格納用クラスです。
 *
 */
public class ContributeData implements Serializable {
	private static final long serialVersionUID = 1L;

	private String _postid = "";
	private String _poster = "";
	private String _title = "";
	private String _message = "";
	private String _postdate = "";
	private String _delkey = "";
	private String _delflg = "";


	/**
	 * <p>投稿IDを返します。</p>
	 *
	 * @return _postid
	 */
	public String getPostid() {
		return _postid;
	}

	public void setPostid(String postid) {
		_postid = postid;
	}

	/**
	 * <p>投稿者名を返します。</p>
	 *
	 * @return _poster
	 */
	public String getPoster() {
		return _poster;
	}

	public void setPoster(String poster) {
		_poster = poster;
	}

	/**
	 * <p>タイトルを返します。</p>
	 *
	 * @return _title
	 */
	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	/**
	 * <p>投稿本文を返します。</p>
	 *
	 * @return _message
	 */
	public String getMessage() {
		return _message;
	}

	public void setMessage(String message) {
		_message = message;
	}

	/**
	 * <p>投稿日時を返します。</p>
	 *
	 * @return _postdate
	 */
	public String getPostdate() {
		return _postdate;
	}

	public void setPostdate(String postdate) {
		_postdate = postdate;
	}

	/**
	 * <p>削除キーを返します。</p>
	 *
	 * @return _delkey
	 */
	public String getDelkey() {
		return _delkey;
	}

	public void setDelkey(String delkey) {
		_delkey = delkey;
	}

	/**
	 * <p>削除フラグを返します。</p>
	 *
	 * @return _delflg
	 */
	public String getDelflg() {
		return _delflg;
	}

	public void setDelflg(String delflg) {
		_delflg = delflg;
	}
}
