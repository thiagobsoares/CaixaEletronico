package br.com.arqdsis.teste;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import br.com.arqdsis.models.Conta;
import br.com.arqdsis.models.Saque;

public class TesteSaque {

	/*
	 * Saldo da conta de teste irá começar com 500.000
	 */
	@Test
	public void testValorDoSaqueEhValido() {
		Conta conta = new Conta();
		
		conta.setNumeroAgencia(1l);
		conta.setNumeroConta(1l);
		conta.setSaldo(new BigDecimal("500000"));
		
		// Como o valor é invalido '-10', será atribuido null ao valor,
		Saque saque = new Saque(conta, new BigDecimal("-10"));
		assertNull(saque.getValorDoSaque());
		
		// Como o valor é válido '10', não será null
		saque = new Saque(conta, new BigDecimal("10"));
		assertNotNull(saque.getValorDoSaque());
		
	}
	
	@Test
	public void testChecarSaldoSuficienteParaSaque() {
		Conta conta = new Conta();
		
		conta.setNumeroAgencia(1l);
		conta.setNumeroConta(1l);
		conta.setSaldo(new BigDecimal("500000"));
		
		Saque saque = new Saque(conta, new BigDecimal("500000"));
		assertTrue(saque.checarSaldoSuficienteParaSaque());
		
		saque = new Saque(conta, new BigDecimal("500001"));
		assertFalse(saque.checarSaldoSuficienteParaSaque());
		
		saque = new Saque(conta, new BigDecimal("500000.0000000001"));
		assertFalse(saque.checarSaldoSuficienteParaSaque());
		
	}

	/*
	 * Esse método irá testar o realizar saque (alterar o valor do saldo da conta no banco de dados)
	 * Alem disso, se a operação foi registrado no extrato da conta. 
	 */
	@Test
	public void testRealizarSaque() {
		Conta conta = new Conta();
		
		conta.setNumeroAgencia(1l);
		conta.setNumeroConta(1l);
		conta.setSaldo(new BigDecimal("500000"));
		
		Saque saque = new Saque(conta, new BigDecimal("500000"));
		assertTrue(saque.checarSaldoSuficienteParaSaque());
		assertTrue(saque.realizarSaque());
		
		
		
	}
	
	
}
