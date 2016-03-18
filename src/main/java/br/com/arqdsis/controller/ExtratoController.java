package br.com.arqdsis.controller;

import java.time.LocalDate;

import br.com.arqdsis.models.Conta;
import br.com.arqdsis.models.Extrato;

public class ExtratoController {

	private Extrato extrato;
	
	public void consultarExtrato(Conta conta, LocalDate dataInicio, LocalDate dataFinal){
		
		extrato = new Extrato(conta, dataInicio, dataFinal);
		if(extrato.recuperarTodosRegistrosDeOperacoes()){
			System.out.println("Extrato consultado com sucesso");
		}else{
			
		}
	}
}
