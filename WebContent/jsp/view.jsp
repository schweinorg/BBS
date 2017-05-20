<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
    import="java.util.ArrayList"
    import="jp.co.i2c.bbs.data.ContributeData"
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>投稿一覧</title>
</head>
<body>
<form action="" name="frmView" >
<% ArrayList array = (ArrayList)request.getAttribute("contdata"); %>
<% String postid = "";
	String poster = "";
	String title = "";
	String message = "";
	String postdate = "";
	String delkey = "";
%>
<% if (array == null){ %>
	<p>投稿がありません。</p>
<% }else{ %>
	<% for (int i = 0 ; i < array.size() ; i++){ %>
		<br>
		<table border="1" cellpadding="0" cellspacing="0" align="center">
			<tr><td width="1200">
				<br>
						<!-- ここに投稿一覧表示 -->
							<% ContributeData cont = (ContributeData)array.get(i);
						       postid = cont.getPostid();
						       poster = cont.getPoster();
						       title = cont.getTitle();
						       message = cont.getMessage();
						       postdate = cont.getPostdate();
						       delkey = cont.getDelkey();
						    %>

						       <p>投稿ID：<%= postid %></p>
						       <p>投稿者：<%= poster %></p>
						       <p>タイトル：<%= title %></p>
						       本文：<p><%= message %></p>
						       <p>投稿日時：<%= postdate %></p>
				<br>
				<a href="MessageServlet?ACTION_ID=TRANDEL&DEL_ID=<%= postid %>">削除</a>
			</td></tr>
		</table>
	<% } %>
<% } %>
<br>
	<table border="0" cellpadding="0" cellspacing="0" align="center">
		<tr><td>
			<font class="Cp">Copyright (C) 2006 I2C Co.,Ltd. All Right Reserved.</font>
		</td></tr>
	</table>

</form>

</body>

</html>