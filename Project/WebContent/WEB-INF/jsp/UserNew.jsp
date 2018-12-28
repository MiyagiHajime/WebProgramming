<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ja">
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="login.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<title>ユーザー新規登録画面</title>
</head>
<body>
	<div class="container-fluid alert alert-dark">
		<div class="row">
			<div class="col-sm-5"></div>
			<div class="col-sm-5">ユーザー名</div>
			<div class="col-sm-2">
				<div class="right">
					<a class="text-danger border-bottom border-danger"
						href="LogOut">ログアウト</a>
				</div>
			</div>
		</div>
	</div>
	<c:if test="${errMsg != null}" >
	    <div class="alert alert-danger" role="alert">
		  ${errMsg}
		</div>
	</c:if>
	<div class="center">
	<form action="NewUser" method="post">
		<h1>　　　　　　　　　　　　　ユーザー新規登録</h1>
	</div>
	<br>
	<div class="haba alert alert-success">
	<br>
		<div class="row">
			<div class="col-sm-5">
				<div class="right">ログインID</div>
			</div>
			<div class="col-sm-6">
				<input type="text" name="id">
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-sm-5">
				<div class="right">パスワード</div>
			</div>
			<div class="col-sm-6">
				<input type="password" name="pass" maxlength="8">
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-sm-5">
				<div class="right">パスワード(確認)</div>
			</div>
			<div class="col-sm-6">
				<input type="password" name="pass2" maxlength="8">
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-sm-5">
				<div class="right">名前</div>
			</div>
			<div class="col-sm-6">
				<input type="text" name="name">
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-sm-5">
				<div class="right">生年月日</div>
			</div>
			<div class="col-sm-6">
				<input class="col-sm-6" type="date" name="birthday">
			</div>
		</div>
		<br>
		<div class="center">

			<input type="submit" value="登録">
		</from>
		</div>
		<br> <a href="UserListServlet2">戻る</a>
	</div>



</body>
</html>
