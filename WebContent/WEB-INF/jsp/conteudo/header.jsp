<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<c:url value="/resources/js/jquery-2.2.2.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bootstrap.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/estrutura.css"/>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Caixa Eletronico</title>
</head>
<body>
	<!-- Titulo -->
	<div class="container menu-titulo">
		<div class="col-md-11">
			<h1>Bem Vindo, ${conta.cliente}</h1>
		</div>
		<div class="col-md-1">
			<a class="btn btn-deslogar" href="<c:url value="/deslogar"/>">Deslogar</a>
		</div>
	</div>
</body>
</html>