package br.com.arqdsis.controller;

import java.math.BigDecimal;

import br.com.arqdsis.conta_logada.ContaLogada;
import br.com.arqdsis.models.Conta;
import br.com.arqdsis.models.Saque;
import br.com.arqdsis.simulador.DispenserHardware;

public class SaqueController {

	private DispenserHardware dispenserHardware;

	public Boolean sacarDinheiro(Conta conta, BigDecimal valorDoSaque) {
		Boolean retorno = false;
		Saque saque = new Saque(conta, valorDoSaque);
		dispenserHardware = new DispenserHardware();

		if (saque.checarSaldoSuficienteParaSaque()) {
			if (dispenserHardware.realizarSaqueNoDispenser(valorDoSaque)) {
				saque.realizarSaque();
				ContaLogada.atualizarConta();
				
				retorno = true;
			} else {
				System.out.println("Combinação invalida");
			}
		} else {
			System.out.println("Saldo insuficiente");
		}
		System.out.println(ContaLogada.conta.getSaldo());
		return retorno;
	}	
}
