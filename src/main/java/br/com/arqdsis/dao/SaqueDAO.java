package br.com.arqdsis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.arqdsis.factory.ConnectionFactory;
import br.com.arqdsis.models.TO.SaqueTO;

public class SaqueDAO {

	public Boolean atualizarSaldo(SaqueTO saqueTO) {
		String sql = "UPDATE conta SET saldo = ? WHERE numeroConta = ? and numeroAgencia = ?";
		
		try(Connection conn = ConnectionFactory.getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setBigDecimal(1, saqueTO.getValorNovoSaldo());
			stmt.setLong(2, saqueTO.getConta().getNumeroConta());
			stmt.setLong(3, saqueTO.getConta().getNumeroAgencia());
			
			return stmt.execute();
			
		}catch(SQLException error){
			error.printStackTrace();
			return false;
		}
	}
	
	

}
