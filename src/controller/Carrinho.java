package controller;
import java.util.ArrayList;
import model.Cliente;
import model.Item;
import model.Livro;
import view.Menu;

/**
 * Responsavel pela manipulacao de adicao e remocao dos itens adicionados pelo cliente ao carrinho
 * @author lucas
 * @version 1.0 (Nov 2020)
 */
public class Carrinho {
	private Menu menu = new Menu();
	private Pagamento pagamento = new Pagamento();
	private ArrayList<Item> sacola = new ArrayList<Item>();
	public double total = 0;
	public int quantidadeLivros = 0;
	private Ferramenta ferramenta = new Ferramenta();
	
	/**
	 * Verifica se o carrinho esta vazio, nao estando vazio, faz uma descricao dos itens contidos no carrinho
	 * @return verdadeiro caso o carrinho possua itens e falso caso esteja vazio
	 */
	public boolean verificarCarrinho() {
		double total = 0;
		String espacamento = " ";

		if (sacola.size() > 0) {
			System.out.println("Atualmente seu carrinho conta com os itens: ");
			System.out.println(espacamento.repeat(90) + " Q " + "  I     " + "    total" );
			for (int i = 0; i < sacola.size(); i++) {
				System.out.println("(" + (i+1) + ") " + sacola.get(i).getBookName() + espacamento.repeat(85 - sacola.get(i).getBookName().length()) + "(" + sacola.get(i).getQuantidade() + "x " + ferramenta.obterValorFormatado(sacola.get(i).getPreco()) + ")  -  " + ferramenta.obterValorFormatado(sacola.get(i).getQuantidade() * sacola.get(i).getPreco()) + "R$");
				total += sacola.get(i).getQuantidade() * sacola.get(i).getPreco();
			}
			this.total = total;
			System.out.println("Valor total: " + ferramenta.obterValorFormatado(this.total) + "R$");
			return true;
		} else {
			System.out.println("\nO carrinho esta vazio.");
			return false;
		}
	}
	
	/**
	 * Responsavel por adicionar itens ao carrinho (sacola)
	 * @param idLivro ID do livro a ser adicionado
	 * @param nomeLivro nome do livro a ser adicionado
	 * @param categoria a categoria a qual o livro pertence
	 * @param quantidade quantidade a ser adicionada ao carrinho
	 * @param preco preco de cada unidade
	 */
	public void adicionarItem(int idLivro, String nomeLivro, String categoria, int quantidade, double preco) {
		Item item = new Item(quantidade, nomeLivro, idLivro, preco, categoria);	
		
		this.total += (preco * quantidade);
		this.quantidadeLivros += quantidade;
		sacola.add(item);
	}

	/**
	 * Responsavel por remover do carrinho (sacola) um determinado item e atualizar o estoque com a quantidade removida
	 * @param catalogo lista de todos os livros registrados. Usado para atualizar a quantidade em estoque caso o cliente deseje remover itens do carrinho
	 * @return verifica o estado do carrinho apos a remocao de um item. Verdadeiro caso haja itens no carrinho e falso caso esteja vazio
	 */
	public boolean removerItem(ArrayList<Livro> catalogo) {
		int item_removido;
		
		if (verificarCarrinho()) {
			item_removido = menu.textoRemoverItem(sacola.size());
			if (item_removido != 0) {
				catalogo.get(sacola.get(item_removido - 1).getBookID() - 1).setQuantidadeEstoque(catalogo.get(sacola.get(item_removido - 1).getBookID() - 1).getQuantidadeEstoque() + sacola.get(item_removido - 1).getQuantidade());
				sacola.remove(item_removido - 1);
				return verificarCarrinho();
			}
			return true;
		} 
		return false;
	}
	
	/**
	 * 
	 * @param cliente objeto referente ao cliente que esta realizando a compra
	 * @param catalogo lista de todos os livros registrados. E usado caso o cliente deseje remover um item antes de finalizar a compra
	 * @return se pagamento for feito retorna 1, caso contrario, 0
	 */
	public int prosseguirPagamento(Cliente cliente, ArrayList<Livro> catalogo) {
		if (verificarCarrinho()) {
			while (true) {
				switch (menu.textoProsseguirPagamento()) {
					case 1:
						System.out.println(total);
						pagamento.realizarPagamento(sacola, cliente, total);
						return 1;
					case 2:
						if (!removerItem(catalogo)) {
							return 0;
						}
						break;
					case 3:
						return 0;
				}
			}
		}
		return 0;
	}
	
	public ArrayList<Item> getSacola() {
		return this.sacola;
	}
}
