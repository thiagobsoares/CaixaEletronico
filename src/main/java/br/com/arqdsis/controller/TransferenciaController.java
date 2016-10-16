package br.com.arqdsis.controller;

import java.math.BigDecimal;

import br.com.arqdsis.excecoes.TransferenciaException;
import br.com.arqdsis.models.Conta;
import br.com.arqdsis.models.Transferencia;

public class TransferenciaController {

	public Boolean transferir(Conta conta, String agenciaDestinatario, String contaDestinatario,
			BigDecimal valorDaTransferencia) throws TransferenciaException {
		Conta contaDestinataria = new Conta();
		Long contaDest = Long.parseLong(contaDestinatario);
		Long agenciaDest = Long.parseLong(agenciaDestinatario);
		contaDestinataria.setNumeroAgencia(agenciaDest);
		contaDestinataria.setNumeroConta(contaDest);

		if (!contaDestinataria.recuperarConta()) {
			throw new TransferenciaException("Conta destinatária não existe.");
		}

		if (conta.getSaldo().compareTo(valorDaTransferencia) <= 0) {
			throw new TransferenciaException("Saldo insuficiente");
		}

		Transferencia transferencia = new Transferencia(conta, contaDestinataria, valorDaTransferencia);

		transferencia.realizarTransferencia();

		return true;
	}

}
