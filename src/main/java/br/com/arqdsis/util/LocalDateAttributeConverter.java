package br.com.arqdsis.util;

import java.sql.Date;
import java.time.LocalDate;

public class LocalDateAttributeConverter{

	public Date convertLocalDateToDateSQL(LocalDate localDate) {
		return Date.valueOf(localDate);
	}

	public LocalDate convertDateSQLToLocalDate(Date date) {
		return date.toLocalDate();
	}

}
