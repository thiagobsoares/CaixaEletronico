package br.com.arqdsis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.arqdsis.factory.ConnectionFactory;
import br.com.arqdsis.models.TO.ContaTO;

public class ContaDAO {

	public ContaTO recuperarContaSenha(ContaTO conta) {
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
				return conta;
			} else {
				return null;
			}

		} catch (SQLException error) {
			error.printStackTrace();
			return null;
		}
	}
	
	public ContaTO recuperarConta(ContaTO conta) {
		String sql = "SELECT * FROM conta WHERE numeroConta = ? and numeroAgencia = ?";
		try (Connection conn = ConnectionFactory.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, conta.getNumeroConta());
			stmt.setLong(2, conta.getNumeroAgencia());

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				conta.setCliente(rs.getString("cliente"));
				conta.setSaldo(rs.getBigDecimal("saldo"));
				conta.setAdministrador(rs.getBoolean("administrador"));
				return conta;
			} else {
				return null;
			}

		} catch (SQLException error) {
			error.printStackTrace();
			return null;
		}
	}

}
