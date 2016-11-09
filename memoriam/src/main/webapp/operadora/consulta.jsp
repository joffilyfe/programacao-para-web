<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/layout/header.html" %>
	</head>
	
	<body>
		<div class="container">
			<div class="jumbotron">
				<h1>Memori<i class="glyphicon glyphicon-phone"></i>m</h1>
				<table class="table">
					<thead>
						<th>#</th>
						<th>Nome</th>
						<th>Prefixo</th>
					</thead>
					
					<tbody style="text-align: left;">
						<c:forEach var="operadora" items="${operadoras}">
							<tr>
								<td></td>
								<td><a href="controller.do?op=operadoraEditar&id=${operadora.id}">${operadora.nome}</td>
								<td>${operadora.prefixo}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<a href="operadora/cadastro.jsp" class="form-control btn btn-primary">Novo</a>
			</div>
		</div>
	</body>
</html>