package br.com.arqdsis.models.TO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.arqdsis.models.Conta;

public class SaqueTO {
	private BigDecimal valorNovoSaldo;
	private Conta conta;
	private LocalDateTime dataLancamento;

	public SaqueTO(BigDecimal novoSaldo, Conta conta, LocalDateTime dataAtual) {
		this.valorNovoSaldo = novoSaldo;
		this.conta = conta;
		this.dataLancamento = dataAtual;
	}

	public BigDecimal getValorNovoSaldo() {
		return valorNovoSaldo;
	}

	public void setValorNovoSaldo(BigDecimal valorNovoSaldo) {
		this.valorNovoSaldo = valorNovoSaldo;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public LocalDateTime getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(LocalDateTime dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
}
