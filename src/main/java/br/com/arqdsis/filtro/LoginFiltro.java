package br.com.arqdsis.filtro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import br.com.arqdsis.models.Conta;

public class LoginFiltro implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		
		if(request.getRequestURI().contains("resources") || !request.getRequestURI().contains("banco")){
			chain.doFilter(req, res);
			return;
		}
		
		Boolean servletValidacao = request.getServletPath().equals("/login");
		Boolean servletIndex = request.getServletPath().equals("/index.jsp");
		Conta conta = (Conta) request.getSession().getAttribute("conta");
		
		if (!servletIndex && !servletValidacao && conta == null) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(req, res);
			return;
		}

		chain.doFilter(req, res);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
