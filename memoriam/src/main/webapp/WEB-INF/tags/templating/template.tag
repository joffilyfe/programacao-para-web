<%@ tag description="Template para JSP" pageEncoding="UTF-8"%>
<%@ attribute name="title" required="true" %>
<%@ attribute name="theader" required="false" fragment="true" %>
<%@ attribute name="tscript" required="false" fragment="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mm" tagdir="/WEB-INF/tags/messages" %>
<%@ taglib prefix="tt" tagdir="/WEB-INF/tags/templating" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/stylesheets/bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/stylesheets/memoriam.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/stylesheets/login.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/assets/stylesheets/bootstrap/js/bootstrap.min.js"></script>
		<title>Memoriam - ${title}</title>
	</head>
	
	<body>
		<c:if test="${not empty theader}">
			<jsp:invoke fragment="theader"/>
		</c:if>
		<div class="container">
			<tt:menu />
		</div>
		<div class="container">
			<mm:messages value="${msgs}" erroStyle="danger" infoStyle="info" avisoStyle="warning"/>
		</div>
		<div class="container">
			<jsp:doBody />
		</div>
		<jsp:invoke fragment="tscript"/>
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
	</body>
</html>