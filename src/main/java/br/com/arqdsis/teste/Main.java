package br.com.arqdsis.teste;

import java.math.BigDecimal;

import br.com.arqdsis.controller.SaqueController;
import br.com.arqdsis.models.Conta;

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
		controller.sacarDinheiro(conta, new BigDecimal("1000"));
		
	}
}
