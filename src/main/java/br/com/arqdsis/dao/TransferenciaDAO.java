package br.com.arqdsis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.arqdsis.factory.ConnectionFactory;
import br.com.arqdsis.models.Conta;

public class TransferenciaDAO {
	public Boolean atualizarSaldo(Conta conta) {
		String sql = "UPDATE conta SET saldo = ? WHERE numeroConta = ? and numeroAgencia = ?";
		try (Connection conn = ConnectionFactory.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setBigDecimal(1, conta.getSaldo());
			stmt.setLong(2, conta.getNumeroConta());
			stmt.setLong(3, conta.getNumeroAgencia());

			stmt.execute();

			return true;

		} catch (SQLException error) {
			error.printStackTrace();
			return false;
		}
	}
}
