package br.com.arqdsis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.arqdsis.factory.ConnectionFactory;
import br.com.arqdsis.models.Conta;
import br.com.arqdsis.models.RegistroDeOperacao;

public class RegistroDeOperacaoDAO {
	public Boolean registrarOperacao(Conta conta, RegistroDeOperacao registro) {
		String sql = "INSERT INTO movimentacao VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = ConnectionFactory.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, conta.getNumeroConta());
			stmt.setLong(2, conta.getNumeroAgencia());
			stmt.setLong(3, registro.getNumeroDocumento());
			stmt.setString(4, registro.getDataLancamento().toString());
			stmt.setString(5, registro.getTipoOperacao());
			stmt.setString(6, registro.getTipoLancamento());
			stmt.setBigDecimal(7, registro.getValorDaOperacao());

			return stmt.execute();

		} catch (SQLException error) {
			error.printStackTrace();
			return false;
		}
	}
}
