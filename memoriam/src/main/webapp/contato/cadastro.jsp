<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tt" tagdir="/WEB-INF/tags/templating"%>

<tt:template title="Cadastro">
	<jsp:body>
			<div class="jumbotron">
				<h1>Memori<i class="glyphicon glyphicon-phone"></i>m</h1>
					<h2>Dados do contato</h2>
					<form action="${pageContext.request.contextPath}/controller.do" method="POST" class="form-horizontal">
						<input type="hidden" name="op" value="contatoCadastro" />
						<input type="hidden" name="id" value="${contato.id}" />
						<input id="nome" type="text" value="${contato.nome}" name="nome" placeholder="Nome" class="form-control">
						<input id="fone" type="text" value="${contato.fone}" name="fone" placeholder="Telefone" class="form-control">
						<fmt:formatDate var="dataAniv" value="${contato.dataAniversario}" pattern="dd/MM/yyyy"/>
						<input id="dataaniv" value="${dataAniv}" name="dataaniv" class="form-control" type="date" placeholder="Data de cria��o (dd/mm/aaaa)"/>
						<select class="form-control" id="operadora" name="operadora">
							<option value="${null}" label="Selecione a operadora">Selecione a operadora</option>
							<c:forEach var="operadora" items="${utilBean.operadoras}">
								<c:if test="${operadora.id eq contato.operadora.id}">
									<option value="${operadora.id}" label="${operadora.nome}" selected>${operadora.nome}</option>
								</c:if>
								<c:if test="${operadora.id ne contato.operadora.id}">
									<option value="${operadora.id}" label="${operadora.nome}">${operadora.nome}</option>
								</c:if>
							</c:forEach>
						</select>
						
						<button class="form-control btn btn-primary">Salvar</button>
					</form>
					<c:set var="endofconversation" value="true" scope="request"/>
			</div>
		</div>
	</jsp:body>
</tt:template>