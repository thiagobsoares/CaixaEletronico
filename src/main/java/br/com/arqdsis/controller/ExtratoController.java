package br.com.arqdsis.controller;

import java.time.LocalDate;

import br.com.arqdsis.models.Conta;
import br.com.arqdsis.models.Extrato;

public class ExtratoController {

	private Conta conta;
	private Extrato extrato;
	
	public void consultarExtrato(LocalDate dataInicio, LocalDate dataFinal){
		extrato = new Extrato(conta, dataInicio, dataFinal);
		if(extrato.consultar() != null){
			System.out.println("Extrato consultado com sucesso");
		}else{
			
		}
	}
}
