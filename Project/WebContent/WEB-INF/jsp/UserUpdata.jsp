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

<style type="text/css">
.btn {
	width: 150px;
}
</style>
<title>ユーザー情報更新</title>
</head>
<body>
	<div class="container-fluid alert alert-dark">
		<div class="row">
			<div class="col-sm-5"></div>
			<div class="col-sm-5">${UserST.name}さん</div>
			<div class="col-sm-2">
				<div class="right">
					<a class="text-danger border-bottom border-danger"
						href="LogOut">ログアウト</a>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<c:if test="${errMsg != null}">
			<div class="alert alert-danger" role="alert">${errMsg}</div>
		</c:if>
		<div class="center">
			<h1>　　　　　　　　　ユーザー情報更新</h1>
		</div>
		<br>
		<div class="haba alert alert-primary">
			<br>
			<div class="row">
				<div class="col-sm-5">
					<div class="left">ログインID</div>
				</div>
				<div class="col-sm-6">
					<div>${UserST.loginId}</div>
				</div>
			</div>
			<br>
			<form action="UserUpdataSrevlet" method="post">

				<input type="hidden" name="id" value="${UserST.id}">
				<div class="row">
					<div class="col-sm-5">
						<div class="left">パスワード</div>
					</div>
					<div class="col-sm-6">
						<input type="password" name="Pass" maxlength="8">
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-sm-5">
						<div class="left">パスワード(確認)</div>
					</div>
					<div class="col-sm-6">
						<input type="password" name="Pass2" maxlength="8">
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-sm-5">
						<div class="left">ユーザー名</div>
					</div>
					<div class="col-sm-6">
						<input type="text" value=${UserST.name } name="UserName">
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-sm-5">
						<div class="left">生年月日</div>
					</div>
					<div class="col-sm-6">
						<input type="date" value=${UserST.birthDate } name="Birthday">
					</div>
				</div>
				<br>
				<div class="center">
					<input type="submit" value="更新" class="btn">
				</div>
			</form>
		</div>
		<br> <a href="UserListServlet2">戻る</a>
	</div>
</body>
</html>