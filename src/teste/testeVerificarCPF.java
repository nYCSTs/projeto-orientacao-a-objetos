package teste;

import static org.junit.jupiter.api.Assertions.*;
import controller.Ferramenta;

import org.junit.jupiter.api.Test;

class testeVerificarCPF {
	Ferramenta ferramenta = new Ferramenta();
	
	@Test
	void testPagamento() {
		assertEquals(true, ferramenta.verificaCPF("99999999999"));
	}

}
