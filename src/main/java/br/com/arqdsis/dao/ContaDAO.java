package br.com.arqdsis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.arqdsis.factory.ConnectionFactory;
import br.com.arqdsis.models.Conta;

public class ContaDAO {

	public Boolean recuperarConta(Conta conta) {
		String sql = "SELECT * FROM conta WHERE numeroConta = ? and numeroAgencia = ? and senha = ?";
		try (Connection conn = ConnectionFactory.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, conta.getNumeroConta());
			stmt.setLong(2, conta.getNumeroAgencia());
			stmt.setLong(3, conta.getSenha());

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				conta.setCliente(rs.getString("cliente"));
				conta.setSaldo(rs.getBigDecimal("saldo"));
				conta.setAdministrador(rs.getBoolean("administrador"));
				return true;
			} else {
				return false;
			}

		} catch (SQLException error) {
			error.printStackTrace();
			return false;
		}
	}

}
