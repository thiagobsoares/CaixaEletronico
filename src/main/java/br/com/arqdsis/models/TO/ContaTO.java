package br.com.arqdsis.models.TO;

import java.math.BigDecimal;

public class ContaTO {
	
	private String cliente;
	private Long numeroAgencia;
	private Long numeroConta;
	private Long senha;
	private BigDecimal saldo;
	private Boolean administrador;
	
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
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
	public Long getSenha() {
		return senha;
	}
	public void setSenha(Long senha) {
		this.senha = senha;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	public Boolean getAdministrador() {
		return administrador;
	}
	public void setAdministrador(Boolean administrador) {
		this.administrador = administrador;
	}
}
