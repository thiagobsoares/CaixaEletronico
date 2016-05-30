package br.com.arqdsis.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Servlet {
	public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception;
}
