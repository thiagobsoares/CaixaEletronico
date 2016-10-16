<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<c:import url="header.jsp"></c:import>
	<div class="container">
		<c:import url="menu-lateral.jsp"></c:import>
		<div class="conteudo col-md-10">
			<div class="col-md-12">
				<h2 class="conteudo-titulo">Transferencia</h2>
			</div>
			<c:if test="${resposta}">
				<div class="row">
					<div class="col-md-4 col-md-offset-4">
						<p class="text-danger">${msgRetorno}</p>
					</div>
				</div>
			</c:if>

			<c:if test="${not resposta}">
				<div class="row">
					<div class="col-md-4 col-md-offset-4">
						<p class="text-danger">${msgRetorno}</p>
					</div>
				</div>
			</c:if>
		</div>
	</div>

</body>
</html>