<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>掲示板投稿</title>

<script type="text/javascript">
<!--
//必須入力・桁数チェック
function check(){
	var flag = 0;
	// 設定開始（必須にする項目を設定してください）
	if(document.contfrm.tokobun.value == ""){ // 「本文」の入力をチェック
		flag = 1;
        document.getElementById( 'notice-textarea-2' ).style.display = "none";
		document.getElementById( 'notice-textarea-1' ).style.display = "block";
    }else if(document.contfrm.tokobun.value.length > 2000){
    	flag = 1;
        document.getElementById( 'notice-textarea-1' ).style.display = "none";
		document.getElementById( 'notice-textarea-2' ).style.display = "block";
	}else{
        document.getElementById( 'notice-textarea-1' ).style.display = "none";
        document.getElementById( 'notice-textarea-2' ).style.display = "none";
	}

	if(document.contfrm.delkey.value == ""){ // 「削除キー」の入力をチェック
		flag = 1;
        document.getElementById( 'notice-input-text-2' ).style.display = "none";
		document.getElementById( 'notice-input-text-1' ).style.display = "block";
    }else if(document.contfrm.delkey.value.length > 6){
    	flag = 1;
        document.getElementById( 'notice-input-text-1' ).style.display = "none";
    	document.getElementById( 'notice-input-text-2' ).style.display = "block";
    }else{
        document.getElementById( 'notice-input-text-1' ).style.display = "none";
        document.getElementById( 'notice-input-text-2' ).style.display = "none";
	}

	// 設定終了
	if(flag){
		//alert('必須項目に未入力がありました'); // 入力漏れがあれば警告ダイアログを表示
		return false; // 送信を中止
	}else{
		return true; // 送信を実行
	}
}

// クリアボタン処理
function inpClear() {
	document.contfrm.name.value="";
	document.contfrm.title.value="";
	document.contfrm.tokobun.value="";
	document.contfrm.delkey.value="";
    document.getElementById( 'notice-textarea-1' ).style.display = "none";
    document.getElementById( 'notice-textarea-2' ).style.display = "none";
    document.getElementById( 'notice-input-text-1' ).style.display = "none";
    document.getElementById( 'notice-input-text-2' ).style.display = "none";
}

// 改行コードをbrタグに変換
function nl2br() {
    return document.contfrm.tokobun.value.replace(/[\n\r]/g, "<br />");
}



// -->
</script>

</head>
<body>

<h1>投稿</h1>
<li><a href="/BulletinBoard/MessageServlet?ACTION_ID=MENU">メニューへ</a></li>
<form action="MessageServlet" method="post" name="contfrm" onSubmit="return check()">
<input type="hidden" name="ACTION_ID" value="CONTRIBUTE">
<p>名前</p>
<input type="text" name="name" />
<p>タイトル</p>
<input type="text" name="title" />
<p>投稿文(必須)</p>
<p id="notice-textarea-1" style="display: none; color: red;"> 【投稿文を入力して下さい】</p>
<p id="notice-textarea-2" style="display: none; color: red;"> 【投稿文は2000字以内で入力して下さい】</p>
<textarea name="tokobun" cols=40 rows=4>
</textarea>
<br>
<p>削除キー(必須)</p>
<p id="notice-input-text-1" style="display: none; color: red;"> 【削除キーを入力して下さい】</p>
<p id="notice-input-text-2" style="display: none; color: red;"> 【削除キーは6桁以下で入力して下さい】</p>
<input type="text" name="delkey" />
<br>
<input type="submit" name="sub_ans" value="投稿する" />
<input type="button" name="clear" value="クリア" onClick="inpClear()"/>
</form>
</body>
</html>
