<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
<link rel="stylesheet" href="css/login.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<form action="LoginServlet2" method="post">
			<div class="center">
				<h1>ログイン画面</h1>
				<br> <br> <br>
				<div class="container">
		<c:if test="${errMsg != null}">
			<div class="alert alert-danger" role="alert">${errMsg}</div>
		</c:if>
				<div class="row">
					<div class="col-sm-4"></div>
					<div class="col-sm-2">
						<h2>ログインid</h2>
					</div>
					<div class="col-sm-2">
						<input type="text" name="id">
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-sm-4"></div>
					<div class="col-sm-2">
						<h3>パスワード</h3>
					</div>
					<div class="col-sm-2">
						<input type="password" name="pass" size="20" maxlength="8">
					</div>
				</div>
				<br>
				<div class="row">
					<div class="container">
						<button
							input type="submit">ログイン</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>