package br.com.arqdsis.simulador;

import java.math.BigDecimal;

public class DispenserHardware {

	/*
	 * 
	 * Class responsável por simular o hardware do dispenser (quantidade de notas e combinações)
	 * 
	 */
	private static BigDecimal valorTotalNoDispenser;
	private static Integer qtdNota10 = 1000;
	private static Integer qtdNota20 = 1000;
	private static Integer qtdNota50 = 500;
	
	public DispenserHardware(){
		atualizarValorTotalDispenser();
	}
	private void atualizarValorTotalDispenser(){
		valorTotalNoDispenser = new BigDecimal(qtdNota10 * 10 + qtdNota20 * 20 + qtdNota50 * 50);
	}
	public Boolean checarDisponibilidadeDeNotas(BigDecimal valorDoSaque) {
		if(checarValorDispenserMenorQueValorSaque(valorDoSaque)){
			if(checarCombinacaoDasNotas(valorDoSaque)){
				atualizarValorTotalDispenser();
				return true;
			}
		}
		return false;
	}
	
	private boolean checarCombinacaoDasNotas(BigDecimal valorDoSaque) {
		BigDecimal valorDoSaqueAuxiliar = new BigDecimal(valorDoSaque.toBigInteger());
		Integer qtdNota50Aux = qtdNota50;
		Integer qtdNota20Aux = qtdNota20;
		Integer qtdNota10Aux = qtdNota10;
		
		qtdNota50Aux -= valorDoSaqueAuxiliar.divide(new BigDecimal(50)).intValue();
		
		if (qtdNota50Aux < 0) {
			valorDoSaqueAuxiliar = valorDoSaqueAuxiliar.subtract(new BigDecimal(qtdNota50Aux * 50));
			qtdNota50Aux = 0;
		} else {
			valorDoSaqueAuxiliar = valorDoSaqueAuxiliar.remainder(new BigDecimal(50));
		}
		

		qtdNota20Aux -= valorDoSaqueAuxiliar.divide(new BigDecimal(20)).intValue();
		
		if (qtdNota20Aux < 0) {
			valorDoSaqueAuxiliar = valorDoSaqueAuxiliar.subtract(new BigDecimal(qtdNota20Aux * 20));
			qtdNota20Aux = 0;
		} else {
			valorDoSaqueAuxiliar = valorDoSaqueAuxiliar.remainder(new BigDecimal(20));
		}

		qtdNota10Aux -= valorDoSaqueAuxiliar.divide(new BigDecimal(50)).intValue();
		
		if (qtdNota10Aux < 0) {
			System.out.println("Quantidade de notas insuficientes");
			return false;
		} else {
			valorDoSaqueAuxiliar = valorDoSaqueAuxiliar.remainder(new BigDecimal(10));
		}
		
		if(valorDoSaqueAuxiliar.equals(new BigDecimal("0"))){
			atualizarQuantidadeDeNotasDoDispenser(qtdNota10Aux, qtdNota20Aux, qtdNota50Aux);
			return true;
		}else{
			System.out.println("Combinação invalida");
			return false;
		}
			
		
	}
	private void atualizarQuantidadeDeNotasDoDispenser(Integer qtdNota10Aux, Integer qtdNota20Aux,
			Integer qtdNota50Aux) {
		qtdNota10 = qtdNota10Aux;
		qtdNota20 = qtdNota20Aux;
		qtdNota50 = qtdNota50Aux;
	}
	
	private Boolean checarValorDispenserMenorQueValorSaque(BigDecimal valorDoSaque){
		if (valorDoSaque.compareTo(valorTotalNoDispenser) <= 0) {
			System.out.println("transação permitida");
			return true;
		} else {
			System.out.println("recusar transação");
			return false;
		}
	}

}
