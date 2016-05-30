package br.com.arqdsis.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ServletController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String url = req.getParameter("url");
		try {
			Class<?> clazz = Class.forName("br.com.arqdsis.servlet." + url + "Servlet");
			Servlet servlet = (Servlet) clazz.newInstance();
			String caminhoPage = servlet.executa(req, res);
			RequestDispatcher dispencher = req.getRequestDispatcher(caminhoPage);
			dispencher.forward(req, res);

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e.getMessage());
		}
	}
}
