package br.com.arqdsis.controller;

import java.math.BigDecimal;

import br.com.arqdsis.excecoes.SaqueException;
import br.com.arqdsis.models.Conta;
import br.com.arqdsis.models.Saque;
import br.com.arqdsis.simulador.DispenserHardware;

public class SaqueController {

	private DispenserHardware dispenserHardware;

	public Boolean sacarDinheiro(Conta conta, BigDecimal valorDoSaque) throws SaqueException {
		Boolean retorno = false;
		Saque saque = new Saque(conta, valorDoSaque);
		dispenserHardware = new DispenserHardware();

		if (saque.checarSaldoSuficienteParaSaque()) {
			if (dispenserHardware.realizarSaqueNoDispenser(valorDoSaque)) {
				saque.realizarSaque();
				conta.recuperarConta();
				retorno = true;
			} else {
				throw new SaqueException("Combinação Inválida");
			}
		} else {
			throw new SaqueException("Saldo Insuficiente");
		}
		return retorno;
	}	
}
