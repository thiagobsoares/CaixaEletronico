package br.com.arqdsis.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.arqdsis.dao.RegistroDeOperacaoDAO;
import br.com.arqdsis.models.TO.ExtratoTO;

public class Extrato {

	private RegistroDeOperacaoDAO dao;
	private List<RegistroDeOperacao> listaRegistro;
	private LocalDate dataInicio;
	private LocalDate dataFinal;
	private Conta conta;

	public Extrato(Conta conta, LocalDate dataInicio, LocalDate dataFinal) {
		listaRegistro = new ArrayList<>();
		this.dataInicio = dataInicio;
		this.dataFinal = dataFinal;
		this.conta = conta;
		dao = new RegistroDeOperacaoDAO();
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

	public Boolean recuperarTodosRegistrosDeOperacoes() {
		ExtratoTO extratoTO = new ExtratoTO(conta, dataInicio, dataFinal, listaRegistro);
		return dao.consultarTodasOperacoes(extratoTO);
	}
	
	
}
