package br.com.arqdsis.teste;

import java.math.BigDecimal;
import java.time.LocalDate;

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


		int num = 50;

		for (int i = 0; i < num; i++) {
			registro.setDataLancamento(LocalDate.now().minusDays((long)(Math.random() * 5)));
			int lanc = (int) (Math.random() * 2) + 1;
			
			if (lanc == 1)
				registro.setTipoLancamento(RegistroDeOperacao.TIPO_LANCAMENTO_CREDITO);
			else
				registro.setTipoLancamento(RegistroDeOperacao.TIPO_LANCAMENTO_DEBITO);
			
			registro.setTipoOperacao("Movimentação");
			registro.setValorDaOperacao(new BigDecimal((int)(Math.random() * 1000)));
			registro.registrarOperacao(conta);

		}

	}
}
