package br.com.arqdsis.util;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.Test;

public class LocalDateAttributeConverterTest {

	@Test
	public void convertDateSQLToLocalDateTest() {
		LocalDateAttributeConverter converter = new LocalDateAttributeConverter();
		LocalDate dataEsperado = LocalDate.now();
		Date dataEsperado2 = Date.valueOf(dataEsperado);
		
		LocalDate dataResposta = converter.convertDateSQLToLocalDate(dataEsperado2);
		Date dataResposta2 = converter.convertLocalDateToDateSQL(dataEsperado);

		assertEquals(dataEsperado, dataResposta);
		assertEquals(dataEsperado2, dataResposta2);
		
	}

}
