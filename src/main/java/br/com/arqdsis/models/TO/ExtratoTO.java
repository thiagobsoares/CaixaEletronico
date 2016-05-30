package br.com.arqdsis.models.TO;

import java.time.LocalDate;
import java.util.List;

import br.com.arqdsis.models.Conta;
import br.com.arqdsis.models.RegistroDeOperacao;

public class ExtratoTO {

	private LocalDate dataInicio;
	private LocalDate dataFinal;
	private List<RegistroDeOperacao> listaRegistro;
	private Conta conta;


	public ExtratoTO(Conta conta, LocalDate dataInicio, LocalDate dataFinal, List<RegistroDeOperacao> listaRegistro) {
		this.conta = conta;
		this.dataInicio = dataInicio;
		this.dataFinal = dataFinal;
		this.listaRegistro = listaRegistro;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
	}

	public List<RegistroDeOperacao> getListaRegistro() {
		return listaRegistro;
	}

	public void setListaRegistro(List<RegistroDeOperacao> listaRegistro) {
		this.listaRegistro = listaRegistro;
	}

}
