package br.com.arqdsis.teste;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import br.com.arqdsis.simulador.DispenserHardware;

public class TesteDispenser {

	@Test
	public void testRealizarSaqueNoDispenser() {
		DispenserHardware dispenser = new DispenserHardware();
		
		assertEquals(new BigDecimal(55000), DispenserHardware.getValorTotalNoDispenser());
		assertTrue(dispenser.realizarSaqueNoDispenser(new BigDecimal(30000)));
		
		assertEquals(new BigDecimal(25000), DispenserHardware.getValorTotalNoDispenser());
		assertTrue(dispenser.realizarSaqueNoDispenser(new BigDecimal(10000)));
		
		assertEquals(new BigDecimal(15000), DispenserHardware.getValorTotalNoDispenser());
		assertFalse(dispenser.realizarSaqueNoDispenser(new BigDecimal(10001)));

		assertEquals(new BigDecimal(15000), DispenserHardware.getValorTotalNoDispenser());
		assertTrue(dispenser.realizarSaqueNoDispenser(new BigDecimal(14000)));
		
		assertEquals(new BigDecimal(1000), DispenserHardware.getValorTotalNoDispenser());
		assertFalse(dispenser.realizarSaqueNoDispenser(new BigDecimal(2000)));
		
		assertEquals(new BigDecimal(1000), DispenserHardware.getValorTotalNoDispenser());
		assertFalse(dispenser.realizarSaqueNoDispenser(new BigDecimal(1010)));
		
		assertEquals(new BigDecimal(1000), DispenserHardware.getValorTotalNoDispenser());
		assertTrue(dispenser.realizarSaqueNoDispenser(new BigDecimal(1000)));

		assertEquals(new BigDecimal(0), DispenserHardware.getValorTotalNoDispenser());
		assertFalse(dispenser.realizarSaqueNoDispenser(new BigDecimal(10)));
		
		assertEquals(new BigDecimal(0), DispenserHardware.getValorTotalNoDispenser());
	}

}
