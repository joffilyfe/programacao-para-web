<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tt" tagdir="/WEB-INF/tags/templating"%>

<tt:template title="Cadastro">
	<jsp:body>
		<div class="container">
			<div class="jumbotron">
				<h1>Memori<i class="glyphicon glyphicon-phone"></i>m</h1>
				<form method="POST" action="${pageContext.request.contextPath}/controller.do?op=operadoraDeletar">
					<table class="table">
						<thead>
							<th>#</th>
							<th>Nome</th>
							<th>Telefone</th>
							<th>Operadora</th>
						</thead>
						
						<tbody style="text-align: left;">
							<c:forEach var="contato" items="${contatos}">
								<tr>
									<td>
										<input type="checkbox" name="id" value="${contato.id}" class="operadora-select"/>
									</td>
									<td><a href="controller.do?op=contatoEditar&id=${contato.id}">${contato.nome}</td>
									<td>${contato.fone}</td>
									<td>${contato.operadora.nome}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<button id="btnDeletar" class="btn btn-danger form-control hidden">Deletar</button>
				</form>
				<a href="contato/cadastro.jsp" class="form-control btn btn-primary">Novo</a>
			</div>
		</div>
		<script>
			$(document).ready(function() {
				var enabled = false;
				var checkboxes = $(".operadora-select");

				$(".operadora-select").on("click", function(e) {
					
					if ($(this).is(":checked")) {
						enabled = true;
					} else {
						enabled = false;
					}
					
					if (enabled) {
						$("#btnDeletar").removeClass("hidden");
					} else {
						

						for (var i = 0; i < checkboxes.length; i++) {
							if (checkboxes[i].checked) {
								return;
							}
						}

						$("#btnDeletar").addClass("hidden");
					}
				})
			});
		</script>
	</jsp:body>
</tt:template>