package teste;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import model.Cliente;
import model.Livro;
import controller.Carrinho;

import org.junit.jupiter.api.Test;

class testePagamento {
	
	@Test
	void testPagamento() {
		Carrinho carrinho = new Carrinho();
		Livro livro = new Livro();
		livro.setAuthorName("Autor teste");
		livro.setBookName("Livro teste");
		livro.setCategoria("FICCAO");
		livro.setID(10);
		livro.setISBN("310833120381");
		livro.setQuantidadeEstoque(10);
		livro.setPreco(12.90);
		livro.setEditora("Leitura");
		ArrayList<Livro> catalogo = new ArrayList<Livro>();
		catalogo.add(livro);
		carrinho.adicionarItem(catalogo.get(0).getID(), catalogo.get(0).getBookName(), catalogo.get(0).getCategoria(), catalogo.get(0).getQuantidadeEstoque(), catalogo.get(0).getPreco());;
		Cliente cliente = new Cliente(0, "Lucas", "05170081146", "1234567", "Jorge", "Branca", "Incompleto", "Masculino", "Solteiro", "Brasileiro", "CLS 214", "Cliente", "6133462120", "teste@teste.com", "sasasa");
		
		
		assertEquals(1, carrinho.prosseguirPagamento(cliente, catalogo));
	}
	

}
