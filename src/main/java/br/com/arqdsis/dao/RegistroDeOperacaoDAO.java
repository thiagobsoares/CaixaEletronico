package br.com.arqdsis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.arqdsis.factory.ConnectionFactory;
import br.com.arqdsis.models.Conta;
import br.com.arqdsis.models.RegistroDeOperacao;
import br.com.arqdsis.models.TO.RegistroDeOperacaoTO;

public class RegistroDeOperacaoDAO {
	public Boolean registrarOperacao(RegistroDeOperacaoTO registroTO) {
		String sql = "INSERT INTO RegistroDeOperacao (numeroConta, numeroAgencia, dataLancamento, tipoOperacao, tipoLancamento, valorDaOperacao) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection conn = ConnectionFactory.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(sql);
			RegistroDeOperacao registro = registroTO.getRegistroDeOperacao();
			Conta conta = registroTO.getConta();
			
			stmt.setLong(1, conta.getNumeroConta());
			stmt.setLong(2, conta.getNumeroAgencia());
			stmt.setString(3, registro.getDataLancamento().toString());
			stmt.setString(4, registro.getTipoOperacao());
			stmt.setString(5, registro.getTipoLancamento());
			stmt.setBigDecimal(6, registro.getValorDaOperacao());

			return stmt.execute();

		} catch (SQLException error) {
			error.printStackTrace();
			return false;
		}
	}

	public RegistroDeOperacao consultarOperacao(Long numeroDocumento) {
		String sql = "SELECT * FROM RegistroDeOperacao WHERE numeroDocumento = ?";
		RegistroDeOperacao registro = null;
		
		try (Connection conn = ConnectionFactory.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(sql);
			registro = new RegistroDeOperacao();
			
			stmt.setLong(1, numeroDocumento);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()){
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDateTime dateTime = LocalDateTime.parse(rs.getString("dataLancamento").substring(0, 10), formatter);
				
			    registro.setNumeroDocumento(rs.getLong("numeroDocumento"));
			    registro.setDataLancamento(dateTime);
			    registro.setTipoLancamento(rs.getString("tipoOperacao"));
			    registro.setTipoLancamento(rs.getString("tipoLancamento"));
			    registro.setValorDaOperacao(rs.getBigDecimal("valorDaOperacao"));
			}
			pu 2 / mak / es
			
			return registro;

		} catch (SQLException error) {
			error.printStackTrace();
			return registro;
		}
	}
}
