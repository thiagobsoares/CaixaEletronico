package br.com.arqdsis.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.arqdsis.models.Conta;
import br.com.arqdsis.models.Extrato;
import br.com.arqdsis.models.RegistroDeOperacao;
import br.com.arqdsis.util.JSonFacade;

@WebServlet("/service/extrato")
public class ExtratoRest extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Extrato extrato;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		super.service(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String parData = req.getParameter("dataInicio");
		String parDataFim = req.getParameter("dataFim");
		Conta conta = (Conta) req.getSession().getAttribute("conta");

		PrintWriter out = resp.getWriter();

		try {
			if (parData != null) {

				LocalDate inicial = LocalDate.parse(parData);
				LocalDate fim;

				if (parDataFim == null)
					fim = LocalDate.now();
				else
					fim = LocalDate.parse(parDataFim);

				extrato = new Extrato(conta, inicial, fim);
				extrato.recuperarTodosRegistrosDeOperacoes();
				List<RegistroDeOperacao> listaRegistro = extrato.getListaRegistro();

				out.println(JSonFacade.listToJSon(listaRegistro));

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			out.println(JSonFacade.errorToJSon(ex));
		}
	}
}
