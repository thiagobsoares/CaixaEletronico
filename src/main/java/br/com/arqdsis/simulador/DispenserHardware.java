package br.com.arqdsis.simulador;

import java.math.BigDecimal;

import br.com.arqdsis.excecoes.SaqueException;

public class DispenserHardware {

	/*
	 * 
	 * Class responsável por simular o hardware do dispenser (quantidade de
	 * notas e combinações)
	 * 
	 */
	private static BigDecimal valorTotalNoDispenser;
	private static Integer qtdNota10 = 1000;
	private static Integer qtdNota20 = 1000;
	private static Integer qtdNota50 = 500;

	public DispenserHardware() {
		atualizarValorTotalDispenser();
	}

	public static BigDecimal getValorTotalNoDispenser() {
		return valorTotalNoDispenser;
	}

	public static void setValorTotalNoDispenser(BigDecimal valorTotalNoDispenser) {
		DispenserHardware.valorTotalNoDispenser = valorTotalNoDispenser;
	}

	public static Integer getQtdNota10() {
		return qtdNota10;
	}

	public static void setQtdNota10(Integer qtdNota10) {
		DispenserHardware.qtdNota10 = qtdNota10;
	}

	public static Integer getQtdNota20() {
		return qtdNota20;
	}

	public static void setQtdNota20(Integer qtdNota20) {
		DispenserHardware.qtdNota20 = qtdNota20;
	}

	public static Integer getQtdNota50() {
		return qtdNota50;
	}

	public static void setQtdNota50(Integer qtdNota50) {
		DispenserHardware.qtdNota50 = qtdNota50;
	}

	private void atualizarValorTotalDispenser() {
		valorTotalNoDispenser = new BigDecimal(qtdNota10 * 10 + qtdNota20 * 20 + qtdNota50 * 50);
	}

	public Boolean realizarSaqueNoDispenser(BigDecimal valorDoSaque) throws SaqueException {
		if (checarValorDispenserMenorQueValorSaque(valorDoSaque)) {
			if (checarCombinacaoDasNotas(valorDoSaque)) {
				atualizarValorTotalDispenser();
				return true;
			}
		}
		return false;
	}

	private boolean checarCombinacaoDasNotas(BigDecimal valorDoSaque) throws SaqueException {
		BigDecimal valorDoSaqueAuxiliar = new BigDecimal(valorDoSaque.toBigInteger());
		Integer qtdNota50Aux = new Integer(qtdNota50);
		Integer qtdNota20Aux = new Integer(qtdNota20);
		Integer qtdNota10Aux = new Integer(qtdNota10);

		qtdNota50Aux -= valorDoSaqueAuxiliar.divide(new BigDecimal(50)).intValue();
		if (qtdNota50Aux < 0) {
			valorDoSaqueAuxiliar = valorDoSaqueAuxiliar.subtract(new BigDecimal(qtdNota50 * 50));
			qtdNota50Aux = 0;
		} else {
			valorDoSaqueAuxiliar = valorDoSaqueAuxiliar.remainder(new BigDecimal(50));
		}
		qtdNota20Aux -= valorDoSaqueAuxiliar.divide(new BigDecimal(20)).intValue();

		if (qtdNota20Aux < 0) {
			valorDoSaqueAuxiliar = valorDoSaqueAuxiliar.subtract(new BigDecimal(qtdNota20 * 20));
			qtdNota20Aux = 0;
		} else {
			valorDoSaqueAuxiliar = valorDoSaqueAuxiliar.remainder(new BigDecimal(20));
		}

		qtdNota10Aux -= valorDoSaqueAuxiliar.divide(new BigDecimal(10)).intValue();

		if (qtdNota10Aux < 0) {
			throw new SaqueException("Quantidade de notas insuficientes");
		} else {
			valorDoSaqueAuxiliar = valorDoSaqueAuxiliar.remainder(new BigDecimal(10));
		}

		if (valorDoSaqueAuxiliar.equals(new BigDecimal("0"))) {
			atualizarQuantidadeDeNotasDoDispenser(qtdNota10Aux, qtdNota20Aux, qtdNota50Aux);
			return true;
		} else {
			return false;
		}

	}

	private void atualizarQuantidadeDeNotasDoDispenser(Integer qtdNota10Aux, Integer qtdNota20Aux,
			Integer qtdNota50Aux) {
		qtdNota10 = qtdNota10Aux;
		qtdNota20 = qtdNota20Aux;
		qtdNota50 = qtdNota50Aux;
	}

	private Boolean checarValorDispenserMenorQueValorSaque(BigDecimal valorDoSaque) {
		if (valorDoSaque.compareTo(valorTotalNoDispenser) <= 0) {
			return true;
		} else {
			return false;
		}
	}

}
