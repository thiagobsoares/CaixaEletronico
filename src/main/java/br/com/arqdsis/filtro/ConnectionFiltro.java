package br.com.arqdsis.filtro;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import br.com.arqdsis.factory.ConnectionFactory;

public class ConnectionFiltro implements Filter {
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {}

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		Connection conn = ConnectionFactory.getConnection();
		req.setAttribute("conn", conn);
		chain.doFilter(req, res);
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException("Erro fechando conexão");
		}
	}

}
