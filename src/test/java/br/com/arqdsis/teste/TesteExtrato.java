package br.com.arqdsis.teste;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import br.com.arqdsis.factory.ConnectionFactory;
import br.com.arqdsis.models.Conta;
import br.com.arqdsis.models.Extrato;
import br.com.arqdsis.models.RegistroDeOperacao;

public class TesteExtrato {

	/*
	 * Irá adicionar alguns registro e salva em uma lista da classe,
	 * depois recupera todos os registros do banco,
	 * ordena ambas as listas e faz a comparação registro por registros,
	 * se todos registros forem iguais, significa que tanto o insert quando select,
	 * derão sucesso. 
	 * 
	 */
	
	@Test
	public void test() {
		List<RegistroDeOperacao> listaRegistroEsperado = new ArrayList<RegistroDeOperacao>();
		Conta conta = new Conta();

		conta.setNumeroAgencia(2L);
		conta.setNumeroConta(2L);
		
		//apagar registro de teste anteriores
		apagarTodosRegistrosDaContaParaRealizarTeste(conta);
		
		RegistroDeOperacao registro = new RegistroDeOperacao();
		registro.setDataLancamento(LocalDate.now());
		registro.setTipoLancamento("Débito");
		registro.setTipoOperacao("Saque");
		registro.setValorDaOperacao(new BigDecimal("10000"));
		registro.registrarOperacao(conta);
		listaRegistroEsperado.add(registro);

		registro = new RegistroDeOperacao();
		registro.setDataLancamento(LocalDate.now().minusDays(30));
		registro.setTipoLancamento("Crédito");
		registro.setTipoOperacao("Reembolso");
		registro.setValorDaOperacao(new BigDecimal("50000"));
		registro.registrarOperacao(conta);
		listaRegistroEsperado.add(registro);

		registro = new RegistroDeOperacao();
		registro.setDataLancamento(LocalDate.now().minusDays(10));
		registro.setTipoLancamento("Crédito");
		registro.setTipoOperacao("Reembolso");
		registro.setValorDaOperacao(new BigDecimal("1000"));
		registro.registrarOperacao(conta);
		listaRegistroEsperado.add(registro);

		registro = new RegistroDeOperacao();
		registro.setDataLancamento(LocalDate.now().minusMonths(30));
		registro.setTipoLancamento("Crédito");
		registro.setTipoOperacao("Doação");
		registro.setValorDaOperacao(new BigDecimal("10000000"));
		registro.registrarOperacao(conta);
		listaRegistroEsperado.add(registro);

		registro = new RegistroDeOperacao();
		registro.setDataLancamento(LocalDate.now().minusMonths(30));
		registro.setTipoLancamento("Débito");
		registro.setTipoOperacao("Imposto de Renda");
		registro.setValorDaOperacao(new BigDecimal("1000000"));
		registro.registrarOperacao(conta);
		listaRegistroEsperado.add(registro);

		Extrato extrato = new Extrato(conta, LocalDate.now().minusYears(10), LocalDate.now().plusDays(1));
		extrato.recuperarTodosRegistrosDeOperacoes();
		List<RegistroDeOperacao> listaRegistroObtido = extrato.getListaRegistro();

		Collections.sort(listaRegistroEsperado);
		Collections.sort(listaRegistroObtido);

		for (int i = 0; i < listaRegistroObtido.size(); i++) {
			assertEquals(listaRegistroEsperado.get(i), listaRegistroObtido.get(i));
		}

	}

	private Boolean apagarTodosRegistrosDaContaParaRealizarTeste(Conta conta) {
		String sql = "DELETE FROM registrodeoperacao WHERE numeroConta = ? AND numeroAgencia = ?";

		try (Connection conn = ConnectionFactory.getConnection()) {
			PreparedStatement stm = conn.prepareStatement(sql);

			stm.setLong(1, conta.getNumeroConta());
			stm.setLong(2, conta.getNumeroAgencia());

			stm.execute();
			
			return true;
		} catch (SQLException error) {
			error.printStackTrace();
			return false;
		}
	}

}
