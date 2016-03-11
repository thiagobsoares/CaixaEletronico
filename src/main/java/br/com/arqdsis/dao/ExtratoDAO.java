package br.com.arqdsis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.arqdsis.factory.ConnectionFactory;
import br.com.arqdsis.models.RegistroDeOperacao;
import br.com.arqdsis.models.TO.ExtratoTO;

public class ExtratoDAO {

	public boolean consultar(ExtratoTO extratoTO) {
		String sql = "SELECT * FROM movimentacao WHERE conta = ? AND agencia = ? AND data_lancamento BETWEEN ? AND ?";

		try (Connection conn = ConnectionFactory.getConnection()) {
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setLong(1, extratoTO.getConta().getNumeroConta());
			stm.setLong(2, extratoTO.getConta().getNumeroAgencia());
			stm.setString(3, extratoTO.getDataInicio().toString());
			stm.setString(4, extratoTO.getDataFinal().toString());

			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				RegistroDeOperacao registro = new RegistroDeOperacao();

				registro.setNumeroDocumento(rs.getLong("numero_documeto"));
				// registro.setDataLancamento(rs.getDate("dataLancamento"));
				registro.setTipoOperacao(rs.getString("tipo_operacao"));
				registro.setTipoLancamento(rs.getString("tipo_lancamento"));
				registro.setValorDaOperacao(rs.getBigDecimal("valor"));

				extratoTO.getListaRegistro().add(registro);
			}
			return true;
		} catch (SQLException error) {
			error.printStackTrace();
			return false;
		}
	}

}
