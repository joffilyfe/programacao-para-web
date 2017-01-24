<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/layout/header.html" %>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/stylesheets/login.css">
		<title>Cadastrar operadora</title>
	</head>
	
	<body>
		<div class="container">
			<%@ include file="/layout/menu.jsp" %>
		</div>
		<div class="container">
			<form class="form-signin" method="POST" action="${pageContext.request.contextPath}/controller.do?op=login">
				<label for="login">Usuário (e-mail)</label>
				<input type="email" name="login" id="login" class="form-control">

				<label for="senha">Senha</label>
				<input type="password" name="senha" id="senha" class="form-control">
				
				<button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
			</form>
		</div>
	</body>
</html>