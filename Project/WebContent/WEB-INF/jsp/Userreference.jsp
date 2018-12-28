<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="login.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<title>ユーザー参照</title>
</head>
<body>
	<div class="container-fluid alert alert-dark">
		<div class="row">
			<div class="col-sm-5"></div>
			<div class="col-sm-5">${A.name} さん</div>

			<!-- aタグのログアウトボタンの作成↑ -->
			<div class="col-sm-2">
				<div class="right">
					<a class="text-danger border-bottom border-danger"
						href="LogOut">ログアウト</a>
				</div>
			</div>
		</div>
	</div>
	<br>

	<div class="center">
		<h1>　　　　　　　　　　　　　ユーザー情報詳細参照</h1>
	</div>
	<br>
	<br>
	<div class="haba alert alert-primary">
		<br> <br>
		<div class="row">
			<div class="col-sm-5">
				<div class="right">ログインID</div>
			</div>
			<div class="col-sm-6">
				<divb> ${A.loginId}</divb>
			</div>
		</div>
		<br>

		<div class="row">
			<div class="col-sm-5">
				<div class="right">ユーザー名</div>
			</div>
			<div class="col-sm-6">
				<divb> ${A.name}</divb>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-sm-5">
				<div class="right">生年月日</div>
			</div>
			<div class="col-sm-6">
				<divb> ${A.birthDate}</divb>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-sm-5">
				<div class="right">登録日時</div>
			</div>
			<div class="col-sm-6">
				<divb> ${A.createDate}</divb>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-sm-5">
				<div class="right">更新日時</div>
			</div>
			<div class="col-sm-6">
				<divb> ${A.updateDate}</divb>
			</div>
		</div>
		<br> <br> <a href="UserListServlet2">戻る</a>
	</div>



</body>
</html>

