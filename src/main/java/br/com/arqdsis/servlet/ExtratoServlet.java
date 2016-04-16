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

import br.com.arqdsis.models.Conta;
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
		req.setAttribute("dataAtual", LocalDate.now());
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/conteudo/extrato.jsp");

		String parData = req.getParameter("data");
		String parDataFim = req.getParameter("dataFim");
		Conta conta = (Conta) req.getSession().getAttribute("conta");

		req.setAttribute("dataAtual", LocalDate.now());

		try {
			if (parData != null) {

				if (parData.equals("on")) {
					parData = req.getParameter("dataInicio");
				}

				LocalDate inicial = LocalDate.parse(parData);
				LocalDate fim;

				if (parDataFim == null)
					fim = LocalDate.now();
				else
					fim = LocalDate.parse(parDataFim);

				extrato = new Extrato(conta, inicial, fim);
				extrato.recuperarTodosRegistrosDeOperacoes();
				List<RegistroDeOperacao> listaRegistro = extrato.getListaRegistro();

				req.setAttribute("registros", listaRegistro);
			}
		} catch (Exception ex) {

		}
		dispatcher.forward(req, resp);
	}
}
