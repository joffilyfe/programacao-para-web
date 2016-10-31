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
				<h1>Memoriam</h1>
				<table class="table">
					<thead>
						<th>#</th>
						<th>Nome</th>
						<th>Telefone</th>
						<th>Operadora</th>
					</thead>
					
					<tbody>
						<c:forEach var="contato" items="${contatos}">
							<tr>
								<td></td>
								<td>${contato.nome}</td>
								<td>${contato.telefone}</td>
								<td>${contato.operadora}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
			</div>
		</div>
	</body>
</html>