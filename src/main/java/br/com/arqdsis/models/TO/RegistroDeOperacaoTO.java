package br.com.arqdsis.models.TO;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.arqdsis.models.Conta;

public class RegistroDeOperacaoTO {
	private Conta conta;
	private Long numeroDocumento;
	private LocalDate dataLancamento;
	private String tipoOperacao;
	private String tipoLancamento;
	private BigDecimal valorDaOperacao;

	public Long getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(Long numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public LocalDate getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(LocalDate dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public String getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(String tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}

	public BigDecimal getValorDaOperacao() {
		return valorDaOperacao;
	}

	public void setValorDaOperacao(BigDecimal valorDaOperacao) {
		this.valorDaOperacao = valorDaOperacao;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

}
