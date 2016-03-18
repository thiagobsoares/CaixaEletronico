package br.com.arqdsis.teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import br.com.arqdsis.controller.SaqueController;
import br.com.arqdsis.models.Conta;
import br.com.arqdsis.simulador.DispenserHardware;

public class Main {
	public static void main(String[] args) {
		/*
		 * 
		 * Teste realizado com sucesso até a parte que o projeto 
		 * conecta com o banco de dados, por não estar implementado,
		 * ocorre um erro.
		 * 
		 */
		Conta conta = new Conta();
		
		conta.setNumeroConta(01L);
		conta.setNumeroAgencia(01L);
		conta.setSaldo(new BigDecimal("500000"));
		SaqueController controller = new SaqueController();
		controller.sacarDinheiro(conta, new BigDecimal("30000"));
		controller.sacarDinheiro(conta, new BigDecimal("10000"));
		controller.sacarDinheiro(conta, new BigDecimal("14000"));
		controller.sacarDinheiro(conta, new BigDecimal("2000"));
		
	}
}
