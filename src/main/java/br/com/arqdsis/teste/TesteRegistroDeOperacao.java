package br.com.arqdsis.teste;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.junit.Test;

import br.com.arqdsis.factory.ConnectionFactory;
import br.com.arqdsis.models.Conta;
import br.com.arqdsis.models.RegistroDeOperacao;

public class TesteRegistroDeOperacao {

	@Test
	public void testRegistrarAndConsultarRegistroDeOperacao() {
		RegistroDeOperacao registro = new RegistroDeOperacao();

		registro.setTipoOperacao("Saque");
		registro.setTipoLancamento("Débito");
		registro.setDataLancamento(LocalDateTime.now());
		registro.setValorDaOperacao(new BigDecimal("5000"));

		Conta conta = new Conta();
		conta.setNumeroAgencia(01L);
		conta.setNumeroConta(01L);

		registro.registrarOperacao(conta);
		
		Long pegarUltimoIdDaSessao = pegarUltimoIdDaSessao();
		RegistroDeOperacao consultarOperacao = registro.consultarOperacao(pegarUltimoIdDaSessao);
		
		assertEquals(registro, consultarOperacao);

	}

	private Long pegarUltimoIdDaSessao() {
		Connection conn = ConnectionFactory.getConnection();
		String sqlSelect = "SELECT max(numeroDocumento) as num FROM RegistroDeOperacao";
		Long idGerado = -1l;

		try (PreparedStatement pst1 = conn.prepareStatement(sqlSelect); ResultSet rs = pst1.executeQuery();) {
			if (rs.next()) {
				idGerado = rs.getLong("num");
			}
			return idGerado;
		} catch (SQLException e) {
			e.printStackTrace();
			return idGerado;
		}
	}

}
