package br.com.arqdsis.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.arqdsis.conta_logada.ContaLogada;
import br.com.arqdsis.models.Extrato;
import br.com.arqdsis.models.RegistroDeOperacao;

@WebServlet("/extrato")
public class ExtratoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Extrato extrato;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/conteudo/extrato.jsp");
		req.setAttribute("conta", ContaLogada.conta);
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/conteudo/extrato.jsp");
		String parData = req.getParameter("data");
		req.setAttribute("conta", ContaLogada.conta);

		if (parData != null) {
			Integer dias = Integer.parseInt(parData);

			LocalDate inicial = LocalDate.now().minusDays(dias);
			LocalDate fim = LocalDate.now();

			extrato = new Extrato(ContaLogada.conta, inicial, fim);
			extrato.recuperarTodosRegistrosDeOperacoes();
			List<RegistroDeOperacao> listaRegistro = extrato.getListaRegistro();

			req.setAttribute("registros", listaRegistro);
		}

		dispatcher.forward(req, resp);
	}
}
