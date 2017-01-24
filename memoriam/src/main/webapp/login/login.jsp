<%@ taglib prefix="tt" tagdir="/WEB-INF/tags/templating"%>

<tt:template title="Login">
	<jsp:body>
			<form class="form-signin" method="POST" action="${pageContext.request.contextPath}/controller.do?op=login">
				<label for="login">Usuário (e-mail)</label>
				<input type="email" name="login" id="login" class="form-control">

				<label for="senha">Senha</label>
				<input type="password" name="senha" id="senha" class="form-control">
				
				<button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
			</form>
	</jsp:body>
</tt:template>

