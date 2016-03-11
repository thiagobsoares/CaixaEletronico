package br.com.arqdsis.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.arqdsis.dao.RegistroDeOperacaoDAO;

public class RegistroDeOperacao {

	private Long numeroDocumento;
	private LocalDateTime dataLancamento;
	private String tipoOperacao;
	private String tipoLancamento;
	private BigDecimal valorDaOperacao;

	public Long getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(Long numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public LocalDateTime getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(LocalDateTime dataLancamento) {
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

	public Boolean registrarOperacao(Conta conta) {
		RegistroDeOperacaoDAO dao = new RegistroDeOperacaoDAO();
		dao.registrarOperacao(conta, this);
		return true;
	}
}
