package teste;
import static org.junit.jupiter.api.Assertions.*;
import view.*;
import org.junit.jupiter.api.Test;

class testeEscolherCategoria {
	Menu menu = new Menu();
	
	@Test
	void testPagamento() {
		assertEquals("INFANTIL", menu.textoEscolherGenero());
	}

}
