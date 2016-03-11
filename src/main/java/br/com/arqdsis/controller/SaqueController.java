package br.com.arqdsis.controller;

import java.math.BigDecimal;

import br.com.arqdsis.models.Conta;
import br.com.arqdsis.models.Saque;
import br.com.arqdsis.simulador.DispenserHardware;

public class SaqueController {

	private Conta conta;
	private DispenserHardware dispenserHardware;

	public void sacarDinheiro(BigDecimal valorDoSaque) {

		Saque saque = new Saque(conta, valorDoSaque);

		if (saque.checarSaldoSuficienteParaSaque()) {
			if (dispenserHardware.checarDisponibilidadeDeNotas(valorDoSaque)) {
				saque.realizarSaque();				
			}else{
				System.out.println("Combinação invalida");
			}
		} else {
			System.out.println("Saldo insuficiente");
		}
	}
}
