<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/login.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<title>ユーザー 一覧</title>
</head>
<body>
	<div class="container-fluid alert alert-dark">
		<div class="row">
			<div class="col-sm-5">ユーザー名</div>
			<div class="col-sm-5">${userInfo.name}さん</div>
			<div class="col-sm-2">
				<div class="right">
					<a class="text-danger border-bottom border-danger" href="LogOut">ログアウト</a>
				</div>
			</div>
		</div>
	</div>

	<div class="right">
		<a href="NewUser">新規登録</a>
		<!-- NewUserのサーブレットに飛ぶ -->
	</div>
	<br>
	<br>

	<div class="center">
		<h1>ユーザー 一覧</h1>
	</div>
	<br>
	<div class="haba alert alert-dark">
		<br>
		<form action="UserListServlet2" method="post">
			<div class="row">
				<div class="col-sm-5">
					<div class="left">ログインID</div>
				</div>
				<div class="col-sm-6">
					<input type="text" name="id" class="example2">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-sm-5">
					<div class="left">ユーザー名</div>
				</div>
				<div class="col-sm-6">
					<input type="text" name="name" maxlength="8" class="example2">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-sm-5">
					<div class="left">生年月日</div>
				</div>
				<div class="col-sm-3">
					<input class="example2" type="date" name="birthday">
				</div>
				<div class="col-sm-3">
					<input class="example2" type="date" name="birthday2">
				</div>
				<br> <br>
				<div class="mx-auto">
					<input type="submit" value="検索" class="example2">
				</div>
			</div>
		</form>
		<hr>
	</div>

	<table class="table table-bordered">
		<thead class="thead-dark">
			<tr>
				<th scope="col">ログインid</th>
				<th scope="col">ユーザー名</th>
				<th scope="col">生年月日</th>
				<th scope="col"></th>
			</tr>
		</thead>
		<tbody>

			<tr>
				<c:forEach var="li" items="${list}">
					<tr>
						<td>${li.loginId}</td>
						<td>${li.name}</td>
						<td>${li.birthDate}</td>
						<!-- TODO 未実装；ログインボタンの表示制御を行う -->
						<td><a class="btn btn-primary" href="More?id=${li.id}">詳細</a>
							<c:if
								test="${li.loginId == userInfo.loginId or userInfo.loginId == 'admin'}">
								<a class="btn btn-success" href="UserUpdataSrevlet?id=${li.id}">更新</a>
								<a class="btn btn-danger" href="Delete?id=${li.id}">削除</a>
							</c:if></td>
					</tr>
				</c:forEach>
		</tbody>
	</table>
</body>
</html>