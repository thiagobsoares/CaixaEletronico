package br.com.arqdsis.models.TO;

import br.com.arqdsis.models.Conta;
import br.com.arqdsis.models.RegistroDeOperacao;

public class RegistroDeOperacaoTO {
	private Conta conta;
	private RegistroDeOperacao registroDeOperacao;

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public RegistroDeOperacao getRegistroDeOperacao() {
		return registroDeOperacao;
	}

	public void setRegistroDeOperacao(RegistroDeOperacao registroDeOperacao) {
		this.registroDeOperacao = registroDeOperacao;
	}
}
