<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>ユーザー削除確認</title>
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
	<div class="center">
		<h1>ユーザー削除確認</h1>
	</div>
	<br>
	<div class="haba alert alert-primary">
	<h5> ${A.name} </h5>
	<h5>を本当に削除してよろしいでしょうか。</h5>
	<br>
	<br>
	<br>
	<br>
	<div class="row">
	<div class="col-sm-3">
	</div>
	<div class="col-sm-3">
	<a href="UserListServlet2"><input type="button" value="キャンセル"  class="btn"></a>
	</div>

	<div class="col-sm-3">
	<form action="Delete" method="post">
		<input type="hidden" name="id" value="${A.id}">
		<input type="submit" value="OK"  class="btn">
	</form>
	</div>

	<div class="col-sm-3">
	</div>
	</div>
</div>

</body>
</html>