package br.com.arqdsis.teste;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.arqdsis.controller.SaqueController;
import br.com.arqdsis.models.Conta;
import br.com.arqdsis.models.RegistroDeOperacao;

public class Main {
	public static void main(String[] args) {
		/*
		 * 
		 * Teste realizado com sucesso até a parte que o projeto conecta com o
		 * banco de dados, por não estar implementado, ocorre um erro.
		 * 
		 */
		Conta conta = new Conta();

		conta.setNumeroConta(123123L);
		conta.setNumeroAgencia(1234L);

		RegistroDeOperacao registro = new RegistroDeOperacao();


		int num = 0;

		for (int i = 0; i < num; i++) {
			int ano = (int) (Math.random() * 3);
			int mes = (int) (Math.random() * 12) + 1;
			int dia = (int) (Math.random() * 27) + 1;
			registro.setDataLancamento(LocalDate.of(ano + 2012, mes, dia));
			int lanc = (int) (Math.random() * 2) + 1;
			
			if (lanc == 1)
				registro.setTipoLancamento(registro.TIPO_LANCAMENTO_CREDITO);
			else
				registro.setTipoLancamento(registro.TIPO_LANCAMENTO_DEBITO);
			
			registro.setTipoOperacao("Movimentação");
			registro.setValorDaOperacao(new BigDecimal((int)(Math.random() * 1000)));
			registro.registrarOperacao(conta);

		}

	}
}
