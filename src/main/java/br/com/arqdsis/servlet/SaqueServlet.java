package br.com.arqdsis.servlet;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.arqdsis.controller.SaqueController;
import br.com.arqdsis.excecoes.SaqueException;
import br.com.arqdsis.models.Conta;

@WebServlet("/saque")
public class SaqueServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/conteudo/saque.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		Boolean sucesso = false;
		String msgRetorno = "";

		String[] parameterValues = req.getParameterValues("valor");
		Conta conta = (Conta) req.getSession().getAttribute("conta");
		String parameter = null;

		/*
		 * Gambiarra.
		 */
		if (parameterValues.length > 1) {
			if (parameterValues[0].equals("") && !parameterValues[1].equals("")) {
				parameter = parameterValues[1];
			}

			if (!parameterValues[0].equals("") && parameterValues[1].equals("")) {
				parameter = parameterValues[0];
			}

			if (parameterValues[0].equals("10") && !parameterValues[1].equals("")) {
				parameter = parameterValues[1];
			}
		} else {
			parameter = parameterValues[0];
		}

		try {
			if (parameter != null && parameter.matches("\\d+")) {
				SaqueController controller = new SaqueController();
				BigDecimal valorDoSaque = new BigDecimal(parameter);

				if (controller.sacarDinheiro(conta, valorDoSaque)) {
					sucesso = true;
					msgRetorno = "Saque realizado com sucesso.";
				}

			} else {
				throw new SaqueException("Dados Inválidos");
			}
		} catch (SaqueException ex) {
			msgRetorno = ex.getMsg();
		}

		req.setAttribute("resposta", sucesso);
		req.setAttribute("msgRetorno", msgRetorno);
		dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/conteudo/resposta-saque.jsp");
		dispatcher.forward(req, resp);
	}

}
