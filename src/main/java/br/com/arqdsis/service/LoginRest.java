package br.com.arqdsis.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.arqdsis.models.Conta;
import br.com.arqdsis.util.JSonFacade;

@WebServlet("/service/login")
public class LoginRest extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		super.service(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Conta conta = null;
		String msg = "";

		String agencia = req.getParameter("agencia");
		Boolean agenciaValida = agencia.matches("\\d{4}");

		String numConta = req.getParameter("conta");
		Boolean numContaValida = numConta.matches("\\d{6}");

		String senha = req.getParameter("senha");
		Boolean senhaValida = senha.matches("\\d{6}");

		PrintWriter out = resp.getWriter();

		if (agenciaValida && numContaValida && senhaValida) {
			conta = new Conta();
			conta.setNumeroAgencia(Long.parseLong(agencia));
			conta.setNumeroConta(Long.parseLong(numConta));
			conta.setSenha(Long.parseLong(senha));

			if (conta.recuperarContaSenha()) {
				req.getSession().setAttribute("conta", conta);
				msg = "Logado com sucesso";
			} else {
				msg = "Conta não existe";
			}
		} else {
			msg = "Dados inválidos";
		}

		out.println(JSonFacade.menssagem(msg));
	}

}
