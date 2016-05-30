package br.com.arqdsis.teste;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;

import br.com.arqdsis.models.Conta;
import br.com.arqdsis.models.RegistroDeOperacao;

public class TesteRegistroDeOperacao {

	/*
	 * Insere e recupera o mesmo registro, 
	 * testando tanto o select quanto o insert.
	 * 
	 */
	@Test
	public void testRegistrarAndConsultarRegistroDeOperacao() {
		RegistroDeOperacao registro = new RegistroDeOperacao();
		Conta conta = new Conta();

		registro.setTipoOperacao("Saque");
		registro.setTipoLancamento("Débito");
		registro.setDataLancamento(LocalDate.now());
		registro.setValorDaOperacao(new BigDecimal("5000.00"));
		conta.setNumeroAgencia(1L);
		conta.setNumeroConta(1L);
		registro.registrarOperacao(conta);

		Long pegarUltimoIdDaSessao = registro.getNumeroDocumento();
		RegistroDeOperacao registro2 = new RegistroDeOperacao();
		registro2.consultarOperacao(pegarUltimoIdDaSessao);

		assertEquals(registro, registro2);

	}
}
