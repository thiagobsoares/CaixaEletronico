package br.com.arqdsis.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.arqdsis.dao.ExtratoDAO;
import br.com.arqdsis.models.TO.ExtratoTO;

public class Extrato {

	private ExtratoDAO extratoDAO;
	private List<RegistroDeOperacao> listaRegistro;
	private LocalDate dataInicio;
	private LocalDate dataFinal;
	private Conta conta;

	public Extrato(Conta conta, LocalDate dataInicio, LocalDate dataFinal) {
		listaRegistro = new ArrayList<>();
		this.dataInicio = dataInicio;
		this.dataFinal = dataFinal;
		this.conta = conta;
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

	public void addRegistroNoExtrato(RegistroDeOperacao registroDoExtrato) {
		listaRegistro.add(registroDoExtrato);
	}

	public List<RegistroDeOperacao> consultar() {
		ExtratoTO extratoTO = new ExtratoTO(conta, dataInicio, dataFinal, listaRegistro);
		if (extratoDAO.consultar(extratoTO)) {
			return this.listaRegistro;
		} else {
			return null;
		}
	}
	
	
}
