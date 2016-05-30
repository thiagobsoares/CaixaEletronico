<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bootstrap.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/index.css"/>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Caixa Eletronico</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-11 ">
				<h1 class="titulo-principal-index">Bem Vindo ao Caixa
					Eletrônico</h1>
			</div>
			<div class="col-md-1 btn-logar">
				<a class="btn-lg btn-info" href="<c:url value='/login'/>">Logar</a>
			</div>
		</div>
		<div class="row">
			<img class="img-responsive imagem-logotipo-index"
				src='<c:url value="/resources/img/logotipo.jpg"/>' />

		</div>
	</div>
</body>
</html>