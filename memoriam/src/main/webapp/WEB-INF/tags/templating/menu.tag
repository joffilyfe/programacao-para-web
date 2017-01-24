<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty usuario}">
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}">Memoriam</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/controller.do?op=conctt">Página inicial</a></li>
                <c:if test="${usuario.perfil eq 'ADMIN' }">
                    <li><a href="${pageContext.request.contextPath}/controller.do?op=operadoras">Operadoras</a></li>
                </c:if>
            </ul>
            <!--  dropdown -->
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${sessionScope.usuario.nome} <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a id="link-submit" href="#">Sair</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">${sessionScope.usuario.perfil}</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<form id="logout-form" action="${pageContext.request.contextPath}/controller.do" method="POST">
	<input type="hidden" name="op" value="logout" />
</form>
<script>
	$(document).ready(function() {
		var formulario = $("#logout-form");
		$("#link-submit").on("click", function(e) {
			formulario.submit();

		});
	});
</script>
</c:if>