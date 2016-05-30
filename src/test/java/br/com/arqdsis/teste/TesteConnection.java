package br.com.arqdsis.teste;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

import br.com.arqdsis.factory.ConnectionFactory;

public class TesteConnection {

	@Test
	public void testeConexao() {
		Connection connection = ConnectionFactory.getConnection();
		assertNotNull(connection);
	}

}
