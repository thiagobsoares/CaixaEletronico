package br.com.arqdsis.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.arqdsis.conta_logada.ContaLogada;
import br.com.arqdsis.models.Conta;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Conta conta = null;
		RequestDispatcher dispatcher = null;
		Boolean error = false;

		String agencia = req.getParameter("agencia");
		Boolean agenciaValida = agencia.matches("\\d{4}");

		String numConta = req.getParameter("conta");
		Boolean numContaValida = numConta.matches("\\d{6}");

		String senha = req.getParameter("senha");
		Boolean senhaValida = senha.matches("\\d{6}");

		if (agenciaValida && numContaValida && senhaValida) {
			conta = new Conta();
			conta.setNumeroAgencia(Long.parseLong(agencia));
			conta.setNumeroConta(Long.parseLong(numConta));
			conta.setSenha(Long.parseLong(senha));

			if (conta.recuperarConta()) {
				dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/conteudo/home.jsp");
				ContaLogada.conta = conta;
				req.setAttribute("conta", ContaLogada.conta);
			} else {
				error = true;
				req.setAttribute("error", error);
				dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			}
		} else {
			error = true;
			req.setAttribute("error", error);
			dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		}
		dispatcher.forward(req, resp);
	}

}
