package br.com.arqdsis.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.arqdsis.dao.SaqueDAO;
import br.com.arqdsis.excecoes.SaqueException;
import br.com.arqdsis.models.TO.SaqueTO;

public class Saque {

	private Conta conta;
	private BigDecimal valorDoSaque;
	private SaqueDAO saqueDAO;

	public Saque(Conta conta, BigDecimal valorDoSaque) throws SaqueException {
		this.setConta(conta);
		this.setValorDoSaque(valorDoSaque);
		saqueDAO = new SaqueDAO();
	}

	public BigDecimal getValorDoSaque() {
		return valorDoSaque;
	}

	public void setValorDoSaque(BigDecimal valorDoSaque) throws SaqueException {
		if (valorDoSaque.compareTo(new BigDecimal("0")) > 0) {
			this.valorDoSaque = valorDoSaque;
		} else {
			throw new SaqueException("Valor inválido");
		}
	}

	public Boolean checarSaldoSuficienteParaSaque() {
		BigDecimal saldoAtual = conta.getSaldo();
		if (valorDoSaque.compareTo(saldoAtual) <= 0) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean realizarSaque() {
		BigDecimal novoSaldo = conta.getSaldo().subtract(valorDoSaque);
		SaqueTO saqueTO = new SaqueTO(novoSaldo, conta, LocalDateTime.now());

		if (saqueDAO.atualizarSaldo(saqueTO)) {
			
			RegistroDeOperacao registroDeOperacao = new RegistroDeOperacao();
			
			registroDeOperacao.setDataLancamento(LocalDate.now());
			registroDeOperacao.setTipoLancamento(RegistroDeOperacao.TIPO_LANCAMENTO_DEBITO);
			registroDeOperacao.setTipoOperacao(RegistroDeOperacao.TIPO_OPERACAO_SAQUE);
			registroDeOperacao.setValorDaOperacao(this.valorDoSaque);
			
			registroDeOperacao.registrarOperacao(conta);
			
			return true;
		} else
			return false;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

}
