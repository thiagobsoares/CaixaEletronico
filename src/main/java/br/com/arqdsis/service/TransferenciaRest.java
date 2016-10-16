package br.com.arqdsis.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.arqdsis.controller.TransferenciaController;
import br.com.arqdsis.excecoes.TransferenciaException;
import br.com.arqdsis.models.Conta;
import br.com.arqdsis.util.JSonFacade;

@WebServlet("/service/transferencia")
public class TransferenciaRest extends HttpServlet {

	/**
	 * 
	 */
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

		String msgRetorno = "";

		Conta conta = (Conta) req.getSession().getAttribute("conta");

		String agenciaDestinatario = req.getParameter("agencia");
		String contaDestinatario = req.getParameter("conta");
		String valor = req.getParameter("valor");

		PrintWriter out = resp.getWriter();

		try {
			if (valor != null && valor.matches("\\d+") && agenciaDestinatario != null
					&& agenciaDestinatario.matches("\\d+") && contaDestinatario != null
					&& contaDestinatario.matches("\\d+")) {
				TransferenciaController controller = new TransferenciaController();
				BigDecimal valorDaTransferencia = new BigDecimal(valor);

				if (controller.transferir(conta, agenciaDestinatario, contaDestinatario, valorDaTransferencia)) {
					msgRetorno = "Transferencia realizado com sucesso.";
				}

				out.println(JSonFacade.menssagem(msgRetorno));

			} else {
				out.println(JSonFacade.menssagem("Dados Inválidos"));
				throw new TransferenciaException("Dados Inválidos");
			}
		} catch (TransferenciaException ex) {
			out.println(JSonFacade.errorToJSon(ex));
		}
	}
}
