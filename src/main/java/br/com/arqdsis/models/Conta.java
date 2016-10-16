package br.com.arqdsis.models;

import java.math.BigDecimal;

import br.com.arqdsis.dao.ContaDAO;
import br.com.arqdsis.models.TO.ContaTO;

public class Conta {

	private ContaDAO contaDAO;

	private String cliente;
	private Long numeroAgencia;
	private Long numeroConta;
	private Long senha;
	private BigDecimal saldo;
	private Boolean administrador;

	public Conta() {
		contaDAO = new ContaDAO();
	}

	public Boolean getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Boolean administrador) {
		this.administrador = administrador;
	}

	public Long getNumeroAgencia() {
		return numeroAgencia;
	}

	public void setNumeroAgencia(Long numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}

	public Long getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(Long numeroConta) {
		this.numeroConta = numeroConta;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public Long getSenha() {
		return senha;
	}

	public void setSenha(Long senha) {
		this.senha = senha;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public Boolean recuperarContaSenha() {
		ContaTO contaTO = new ContaTO();

		contaTO.setNumeroAgencia(numeroAgencia);
		contaTO.setNumeroConta(numeroConta);
		contaTO.setSenha(senha);
		if(contaDAO.recuperarContaSenha(contaTO) != null){
			
			cliente = contaTO.getCliente();
			numeroAgencia = contaTO.getNumeroAgencia();
			numeroConta = contaTO.getNumeroConta();
			senha = contaTO.getSenha();
			saldo = contaTO.getSaldo();
			administrador = contaTO.getAdministrador();
			
			return true;
		}else{
			return false;
		}
	}
	
	public Boolean recuperarConta() {
		ContaTO contaTO = new ContaTO();

		contaTO.setNumeroAgencia(numeroAgencia);
		contaTO.setNumeroConta(numeroConta);
		if(contaDAO.recuperarConta(contaTO) != null){
			
			cliente = contaTO.getCliente();
			numeroAgencia = contaTO.getNumeroAgencia();
			numeroConta = contaTO.getNumeroConta();
			saldo = contaTO.getSaldo();
			administrador = contaTO.getAdministrador();
			
			return true;
		}else{
			return false;
		}
	}
}
