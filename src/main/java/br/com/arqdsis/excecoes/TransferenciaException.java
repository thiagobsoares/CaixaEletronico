package br.com.arqdsis.excecoes;

public class TransferenciaException extends Exception {

	private static final long serialVersionUID = 7141826578561498643L;
	private String msg;

	public TransferenciaException(String string) {
		super(string);
		msg = string;
	}

	public String getMsg() {
		return msg;
	}
}
