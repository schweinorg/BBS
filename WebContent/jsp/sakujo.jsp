<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>投稿削除</title>
<script type="text/javascript">
<!--
//削除キー一致チェック
function alert(delkey){
	var flag = 0;
	var indelkey = document.delfrm.delkey.value;
	if(indelkey != delkey){
		flag = 1;
		document.getElementById( 'notice-input-text-1' ).style.display = "block";
	}

	// 設定終了
	if(flag){
		//alert('必須項目に未入力がありました'); // 入力漏れがあれば警告ダイアログを表示
		return false; // 送信を中止
	}else{
		return true; // 送信を実行
	}
}


// -->
</script>
</head>
<body>
<% String deleteid = (String)request.getAttribute("DEL_ID"); %>
<h1>投稿削除</h1>
<!--
<form action="MessageServlet?ACTION_ID=DELETE" method="post" name="delfrm" onSubmit="return alert(<%= "" %>)">
 -->
<form action="MessageServlet?ACTION_ID=DELETE" method="post" name="delfrm">
<p>削除したい投稿の削除キーを入力して、削除するボタンを押してください。</p>
<% String status = (String)request.getAttribute("STATUS");
   String ng = "NG";%>
<% if(ng.equals(status)){ %>
<p>投稿ID：<%= deleteid %></p>
<!--
<p id="notice-input-text-1" style="display: none; color: red;"> 該当する投稿は存在しないか、削除キーが異なります </p>
 -->
<p style="color: red;"> 該当する投稿は存在しないか、削除キーが異なります </p>
<div>
<input type="text" name="delkey" value="<%= request.getParameter("delkey") %>" />
</div>
<% }else{ %>
<p>投稿ID：<%= request.getParameter("DEL_ID") %></p>
<div>
<input type="text" name="delkey" />
</div>
<% } %>
<input type="hidden" name="delid" value="<%= deleteid %>"></input>
<div>
<p><input type="submit" name="sub_ans" value="削除する" /> </p>
<p><input type="button" name="back" value="戻る" onClick="location.href='/BulletinBoard/MessageServlet?ACTION_ID=VIEW'"/></p>
</div>
</form>
</body>
</html>
