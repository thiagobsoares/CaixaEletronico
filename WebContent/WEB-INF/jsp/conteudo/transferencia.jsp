<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>
	<c:import url="header.jsp" />
	<div class="container">

		<c:import url="menu-lateral.jsp" />
		<div class="conteudo col-md-10">
			<div class="col-md-12">
				<h2 class="conteudo-titulo">Saque</h2>
			</div>
			<div class="col-md-12 conteudo-saque">
				<form action='<c:url value="/transferencia"/>' method="POST">
					<div class="col-md-3 botao-esquerda"></div>
					<div class="col-md-6 botao-meio">
						<table class="table">
							<tr>
								<td> Agência: </td>
								<td> <input type="text" name="agencia" /> </td>
							</tr>
							<tr>
								<td> Conta: </td>
								<td> <input type="text" name="conta" /> </td>
							</tr>

							<tr>
								<td> Valor: </td>
								<td> <input type="text" name="valor" /> </td>
							</tr>


							<tr>
								<td></td>
								<td><input type="submit" value="Transferir" /></td>
							</tr>
							</table>
					</div>
					<div class="col-md-3 botao-direito"></div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>