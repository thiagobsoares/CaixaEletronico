package br.com.arqdsis.excecoes;

public class SaqueException extends Exception {

	private static final long serialVersionUID = 7141826578561498643L;
	private String msg;

	public SaqueException(String string) {
		super(string);
		msg = string;
	}

	public String getMsg() {
		return msg;
	}
}
