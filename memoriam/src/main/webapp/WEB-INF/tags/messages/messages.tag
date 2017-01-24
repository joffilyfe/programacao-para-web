<%@ tag description="Tag apra exibir mensagens diversas" pageEncoding="UTF-8"%>
<%@ tag import="br.edu.ifpb.memoriam.facade.Categoria" %>
<%@ tag import="br.edu.ifpb.memoriam.facade.Mensagem" %>
<%@ attribute name="value" required="true" rtexprvalue="true" type="java.util.List" %>
<%@ attribute name="erroStyle" required="false" rtexprvalue="true" %>
<%@ attribute name="infoStyle" required="false" rtexprvalue="true" %>
<%@ attribute name="avisoStyle" required="false" rtexprvalue="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="container">
	<c:if test="${not empty value}">
		<c:forEach var="msg" items="${value}">
			<c:if test="${msg.categoria eq 'INFO' }">
				<c:set var="estilo" value="${infoStyle}"/>
			</c:if>
			<c:if test="${msg.categoria eq 'ERRO'}">
				<c:set var="estilo" value="${erroStyle}"/>
			</c:if>
			<c:if test="${msg.categoria eq 'AVISO' }">
				<c:set var="estilo" value="${avisoStyle}"/>
			</c:if>
			<div class="alert alert-${estilo}" role="alert">${msg.mensagem}</div>
		</c:forEach>
	</c:if>
</div>