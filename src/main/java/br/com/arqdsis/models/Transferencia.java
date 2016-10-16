package br.com.arqdsis.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.arqdsis.dao.TransferenciaDAO;

public class Transferencia {

	private Conta contaOrigem;
	private Conta contaDestino;
	private BigDecimal valor;
	private TransferenciaDAO transferenciaDAO;

	public Transferencia(Conta conta, Conta contaDestinataria, BigDecimal valorDaTransferencia) {
		contaOrigem = conta;
		contaDestino = contaDestinataria;
		valor = valorDaTransferencia;
		transferenciaDAO = new TransferenciaDAO();
	}

	public Conta getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(Conta contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public Conta getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(Conta contaDestino) {
		this.contaDestino = contaDestino;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Boolean realizarTransferencia() {
		contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valor));
		contaDestino.setSaldo(contaDestino.getSaldo().add(valor));

		if (transferenciaDAO.atualizarSaldo(contaOrigem)) {

			RegistroDeOperacao registroDeOperacao = new RegistroDeOperacao();

			registroDeOperacao.setDataLancamento(LocalDate.now());
			registroDeOperacao.setTipoLancamento(RegistroDeOperacao.TIPO_LANCAMENTO_DEBITO);
			registroDeOperacao.setTipoOperacao(RegistroDeOperacao.TIPO_OPERACAO_TRANSFERENCIA);
			registroDeOperacao.setValorDaOperacao(this.valor);

			registroDeOperacao.registrarOperacao(contaOrigem);

		} else {
			return false;
		}

		if (transferenciaDAO.atualizarSaldo(contaDestino)) {

			RegistroDeOperacao registroDeOperacao = new RegistroDeOperacao();

			registroDeOperacao.setDataLancamento(LocalDate.now());
			registroDeOperacao.setTipoLancamento(RegistroDeOperacao.TIPO_LANCAMENTO_CREDITO);
			registroDeOperacao.setTipoOperacao(RegistroDeOperacao.TIPO_OPERACAO_TRANSFERENCIA);
			registroDeOperacao.setValorDaOperacao(this.valor);

			registroDeOperacao.registrarOperacao(contaDestino);

		} else {
			return false;
		}

		return true;
	}
}
