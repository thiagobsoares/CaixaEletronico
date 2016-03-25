package br.com.arqdsis.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

import br.com.arqdsis.dao.RegistroDeOperacaoDAO;
import br.com.arqdsis.models.TO.RegistroDeOperacaoTO;

public class RegistroDeOperacao implements Comparable<RegistroDeOperacao> {

	private Long numeroDocumento;
	private LocalDate dataLancamento;
	private String tipoOperacao;
	private String tipoLancamento;
	private BigDecimal valorDaOperacao;


	public final static String TIPO_LANCAMENTO_CREDITO = "Crédito";
	public final static String TIPO_LANCAMENTO_DEBITO = "Débito";
	public static final String TIPO_OPERACAO_SAQUE = "Saque";
	
	private RegistroDeOperacaoDAO dao;

	public RegistroDeOperacao() {
		dao = new RegistroDeOperacaoDAO();
	}

	public Long getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(Long numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getDataLancamento() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return dataLancamento.format(formatter);
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

	public Boolean registrarOperacao(Conta conta) {
		RegistroDeOperacaoTO registroTO = new RegistroDeOperacaoTO();

		registroTO.setConta(conta);
		registroTO.setNumeroDocumento(this.numeroDocumento);
		registroTO.setDataLancamento(this.dataLancamento);
		registroTO.setTipoLancamento(this.tipoLancamento);
		registroTO.setTipoOperacao(this.tipoOperacao);
		registroTO.setValorDaOperacao(this.valorDaOperacao);

		Long numeroDocumento = dao.registrarOperacao(registroTO);
		
		if(numeroDocumento != -1){
			this.numeroDocumento = numeroDocumento;
			return true;
		}else{
			return false;
		}
	}

	public Boolean consultarOperacao(Long numeroDocumento) {

		RegistroDeOperacaoTO registro = dao.consultarOperacaoUnica(numeroDocumento);
		this.numeroDocumento = registro.getNumeroDocumento();
		this.dataLancamento = registro.getDataLancamento();
		this.tipoLancamento = registro.getTipoLancamento();
		this.tipoOperacao = registro.getTipoOperacao();
		this.valorDaOperacao = registro.getValorDaOperacao();

		return true;
	}

	@Override
	public String toString() {
		return "numeroDocumento=" + numeroDocumento + "\ntipoOperacao=" + tipoOperacao;
	}

	@Override
	public int compareTo(RegistroDeOperacao obj) {
		return numeroDocumento.compareTo(obj.getNumeroDocumento());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numeroDocumento == null) ? 0 : numeroDocumento.hashCode());
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
		if (numeroDocumento == null) {
			if (other.numeroDocumento != null)
				return false;
		} else if (!numeroDocumento.equals(other.numeroDocumento))
			return false;
		return true;
	}
	
	public static Comparator<RegistroDeOperacao> comparatorPorData(){
		return new Comparator<RegistroDeOperacao>() {

			@Override
			public int compare(RegistroDeOperacao o1, RegistroDeOperacao o2) {
				String[] os1 = o1.getDataLancamento().split("/");
				String[] os2 = o2.getDataLancamento().split("/");
				if(!os1[2].equals(os2[2]))
					return os1[2].compareTo(os2[2]);
				else if(!os1[1].equals(os2[1]))
					return os1[1].compareTo(os2[1]);
				else
					return os1[0].compareTo(os2[0]);
			}
			
		};
	}

}
