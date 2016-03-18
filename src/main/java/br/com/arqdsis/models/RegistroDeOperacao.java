package br.com.arqdsis.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.arqdsis.dao.RegistroDeOperacaoDAO;
import br.com.arqdsis.models.TO.RegistroDeOperacaoTO;

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
		RegistroDeOperacaoTO registroTO = new RegistroDeOperacaoTO();
		registroTO.setRegistroDeOperacao(this);
		registroTO.setConta(conta);

		dao.registrarOperacao(registroTO);

		return true;
	}

	public RegistroDeOperacao consultarOperacao(Long numeroDocumento) {
		RegistroDeOperacaoDAO dao = new RegistroDeOperacaoDAO();

		return dao.consultarOperacao(numeroDocumento);

	}

	@Override
	public String toString() {
		return "RegistroDeOperacao [numeroDocumento=" + numeroDocumento + ", dataLancamento=" + dataLancamento
				+ ", tipoOperacao=" + tipoOperacao + ", tipoLancamento=" + tipoLancamento + ", valorDaOperacao="
				+ valorDaOperacao + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataLancamento == null) ? 0 : dataLancamento.hashCode());
		result = prime * result + ((numeroDocumento == null) ? 0 : numeroDocumento.hashCode());
		result = prime * result + ((tipoLancamento == null) ? 0 : tipoLancamento.hashCode());
		result = prime * result + ((tipoOperacao == null) ? 0 : tipoOperacao.hashCode());
		result = prime * result + ((valorDaOperacao == null) ? 0 : valorDaOperacao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistroDeOperacao other = (RegistroDeOperacao) obj;
		if (dataLancamento == null) {
			if (other.dataLancamento != null)
				return false;
		} else if (!dataLancamento.equals(other.dataLancamento))
			return false;
		if (tipoLancamento == null) {
			if (other.tipoLancamento != null)
				return false;
		} else if (!tipoLancamento.equals(other.tipoLancamento))
			return false;
		if (tipoOperacao == null) {
			if (other.tipoOperacao != null)
				return false;
		} else if (!tipoOperacao.equals(other.tipoOperacao))
			return false;
		if (valorDaOperacao == null) {
			if (other.valorDaOperacao != null)
				return false;
		} else if (!valorDaOperacao.equals(other.valorDaOperacao))
			return false;
		return true;
	}

}
