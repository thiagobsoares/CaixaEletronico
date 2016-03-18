package br.com.arqdsis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.arqdsis.factory.ConnectionFactory;
import br.com.arqdsis.models.Conta;
import br.com.arqdsis.models.RegistroDeOperacao;
import br.com.arqdsis.models.TO.ExtratoTO;
import br.com.arqdsis.models.TO.RegistroDeOperacaoTO;
import br.com.arqdsis.util.LocalDateAttributeConverter;

public class RegistroDeOperacaoDAO {

	public Long registrarOperacao(RegistroDeOperacaoTO registro) {
		String sql = "INSERT INTO RegistroDeOperacao (numeroConta, numeroAgencia, dataLancamento, tipoOperacao, tipoLancamento, valorDaOperacao) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection conn = ConnectionFactory.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			Conta conta = registro.getConta();
			LocalDateAttributeConverter ldac = new LocalDateAttributeConverter();

			stmt.setLong(1, conta.getNumeroConta());
			stmt.setLong(2, conta.getNumeroAgencia());
			stmt.setDate(3, ldac.convertLocalDateToDateSQL(registro.getDataLancamento()));
			stmt.setString(4, registro.getTipoOperacao());
			stmt.setString(5, registro.getTipoLancamento());
			stmt.setBigDecimal(6, registro.getValorDaOperacao());

			stmt.executeUpdate();

			final ResultSet rs = stmt.getGeneratedKeys();
			
			if (rs.next())
				return rs.getLong(1);
			else
				return -1l;
			
		} catch (SQLException error) {
			error.printStackTrace();
			return -1l;
		}
	}

	public RegistroDeOperacaoTO consultarOperacaoUnica(Long numeroDocumento) {
		String sql = "SELECT * FROM RegistroDeOperacao WHERE numeroDocumento = ?";
		RegistroDeOperacaoTO registro = null;

		try (Connection conn = ConnectionFactory.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(sql);
			LocalDateAttributeConverter ldac = new LocalDateAttributeConverter();
			registro = new RegistroDeOperacaoTO();

			stmt.setLong(1, numeroDocumento);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				registro.setNumeroDocumento(rs.getLong("numeroDocumento"));
				registro.setDataLancamento(ldac.convertDateSQLToLocalDate(rs.getDate("dataLancamento")));
				registro.setTipoOperacao(rs.getString("tipoOperacao"));
				registro.setTipoLancamento(rs.getString("tipoLancamento"));
				registro.setValorDaOperacao(rs.getBigDecimal("valorDaOperacao"));
			}

			return registro;

		} catch (SQLException error) {
			error.printStackTrace();
			return registro;
		}
	}

	public Boolean consultarTodasOperacoes(ExtratoTO extratoTO) {
		String sql = "SELECT * FROM registrodeoperacao WHERE numeroConta = ? AND numeroAgencia = ? AND dataLancamento BETWEEN ? AND ?";

		try (Connection conn = ConnectionFactory.getConnection()) {
			PreparedStatement stm = conn.prepareStatement(sql);
			LocalDateAttributeConverter ldac = new LocalDateAttributeConverter();

			stm.setLong(1, extratoTO.getConta().getNumeroConta());
			stm.setLong(2, extratoTO.getConta().getNumeroAgencia());
			stm.setDate(3, ldac.convertLocalDateToDateSQL(extratoTO.getDataInicio()));
			stm.setDate(4, ldac.convertLocalDateToDateSQL(extratoTO.getDataFinal()));

			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				RegistroDeOperacao registro = new RegistroDeOperacao();

				registro.setNumeroDocumento(rs.getLong("numeroDocumento"));
				registro.setDataLancamento(ldac.convertDateSQLToLocalDate(rs.getDate("dataLancamento")));
				registro.setTipoOperacao(rs.getString("tipoOperacao"));
				registro.setTipoLancamento(rs.getString("tipoLancamento"));
				registro.setValorDaOperacao(rs.getBigDecimal("valorDaOperacao"));

				extratoTO.getListaRegistro().add(registro);
			}
			return true;
		} catch (SQLException error) {
			error.printStackTrace();
			return false;
		}
	}
}
