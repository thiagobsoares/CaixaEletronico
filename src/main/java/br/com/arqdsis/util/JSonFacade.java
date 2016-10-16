package br.com.arqdsis.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.arqdsis.models.RegistroDeOperacao;

public class JSonFacade<T> {
	public static StringBuilder montaJSon(HttpServletRequest request) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = request.getReader();
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line).append('\n');
			}
		} finally {
			reader.close();
		}
		return sb;
	}

	public static String listToJSon(List<RegistroDeOperacao> lista) {
		JSONArray vetor = new JSONArray();
		for (RegistroDeOperacao registroDeOperacao : lista) {
			JSONObject object = new JSONObject();
			try {
				object.put("numeroDoc", registroDeOperacao.getNumeroDocumento());
				object.put("dataLancamento", registroDeOperacao.getDataLancamento().toString());
				object.put("tipoOperacao", registroDeOperacao.getTipoOperacao());
				object.put("tipoLancamento", registroDeOperacao.getTipoLancamento());
				object.put("valorDaOperacao", registroDeOperacao.getValorDaOperacao().toString());
				vetor.put(object);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return vetor.toString();
	}

	public static String errorToJSon(Exception e) {
		JSONObject object = new JSONObject();
		try {
			object.put("error", e.toString());
		} catch (JSONException e1) {
			e.printStackTrace();
		}
		return object.toString();
	}

	public static String menssagem(String msgRetorno) {
		JSONObject object = new JSONObject();
		try {
			object.put("menssagem", msgRetorno);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		return object.toString();
	}
}