package br.com.arqdsis.servlet;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.arqdsis.controller.TransferenciaController;
import br.com.arqdsis.excecoes.TransferenciaException;
import br.com.arqdsis.models.Conta;

@WebServlet("/transferencia")
public class TransferenciaServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/conteudo/transferencia.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		Boolean sucesso = false;
		String msgRetorno = "";

		Conta conta = (Conta) req.getSession().getAttribute("conta");

		String agenciaDestinatario = req.getParameter("agencia");
		String contaDestinatario = req.getParameter("conta");
		String valor = req.getParameter("valor");

		try {
			if (valor != null && valor.matches("\\d+") &&
					agenciaDestinatario != null && agenciaDestinatario.matches("\\d+") &&
							contaDestinatario != null && contaDestinatario.matches("\\d+")) {
				TransferenciaController controller = new TransferenciaController();
				BigDecimal valorDaTransferencia = new BigDecimal(valor);

				if (controller.transferir(conta, agenciaDestinatario, contaDestinatario, valorDaTransferencia)) {
					sucesso = true;
					msgRetorno = "Transferencia realizado com sucesso.";
				}

			} else {
				throw new TransferenciaException("Dados Inválidos");
			}
		} catch (TransferenciaException ex) {
			msgRetorno = ex.getMsg();
		}

		req.setAttribute("resposta", sucesso);
		req.setAttribute("msgRetorno", msgRetorno);
		dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/conteudo/resposta-transferencia.jsp");
		dispatcher.forward(req, resp);
	}

}
