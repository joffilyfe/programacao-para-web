<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/layout/header.html" %>
		<title>Cadastrar operadora</title>
	</head>
	
	<body>
		<div class="container">
			<div class="jumbotron">
				<%@ include file="/layout/mensagens.jsp" %>
				<h1>Memori<i class="glyphicon glyphicon-phone"></i>m</h1>
					<h2>Dados da Operadora</h2>
					<form action="${pageContext.request.contextPath}/controller.do" method="POST" class="form-horizontal">
						<input type="hidden" name="op" value="operadoraCadastro" />
						<input type="hidden" name="id" value="${operadora.id}" />
						<input id="nome" type="text" value="${operadora.nome}" name="nome" placeholder="Nome" class="form-control">
						<input id="fone" type="text" value="${operadora.prefixo}" name="prefixo" placeholder="Prefixo" class="form-control">
						<button class="form-control btn btn-primary">Salvar</button>
					</form>
					<c:set var="endofconversation" value="true" scope="request"/>
			</div>
		</div>
	</body>
</html>