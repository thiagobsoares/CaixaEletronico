package br.com.arqdsis.test_rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

public class TesteRestExtrato {

	public static void main(String[] args) {
		acessarLoginEConsultarExtrato();
	}

	public static void acessarSemLogar() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8088/banco/service");
		String resultado = target.path("/extrato").request().get(String.class);

		System.out.println(resultado);
	}

	public static void acessarLoginCorreto() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8088/banco/service").path("/login");
		Form form = new Form();
		form.param("agencia", "1234");
		form.param("conta", "123456");
		form.param("senha", "123456");

		String resultado = target.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);

		System.out.println(resultado);
	}

	public static void acessarLoginInvalido() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8088/banco/service").path("/login");
		Form form = new Form();
		form.param("agencia", "1234");
		form.param("conta", "123956");
		form.param("senha", "123456");

		String resultado = target.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);

		System.out.println(resultado);
	}

	public static void acessarLoginDadosIncorretos() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8088/banco/service").path("/login");
		Form form = new Form();
		form.param("agencia", "1234");
		form.param("conta", "123456");
		form.param("senha", "aaaa");

		String resultado = target.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);

		System.out.println(resultado);
	}

	public static void acessarLoginEConsultarExtrato() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8088/banco/service");
		WebTarget path = target.path("/login");

		Form form = new Form();
		form.param("agencia", "1234");
		form.param("conta", "123456");
		form.param("senha", "123456");

		String resultado = path.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);

		System.out.println(resultado);

		WebTarget path2 = target.path("/extrato");
		form = new Form();
		form.param("dataInicio", "2016-11-01");
		form.param("dataFim", "2016-07-07");

		resultado = path2.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);

		System.out.println(resultado);
	}
}
