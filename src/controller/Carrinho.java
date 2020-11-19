package controller;
import java.util.ArrayList;
import model.Cliente;
import model.Item;
import model.Livro;
import view.Menu;
import java.text.DecimalFormat;

public class Carrinho {
	private Menu menu = new Menu();
	private ArrayList<Item> sacola = new ArrayList<Item>();
	public double total = 0;
	public int quantidadeLivros = 0;
	private DecimalFormat format = new DecimalFormat("#0.00");
	
	public boolean verificarCarrinho() {
		double total = 0;
		String espacamento = " ";

		if (sacola.size() > 0) {
			System.out.println("Atualmente seu carrinho conta com os itens: ");
			System.out.println(espacamento.repeat(41) + "Q " + "  I     " + "    total" );
			for (int i = 0; i < sacola.size(); i++) {
				System.out.println("(" + (i+1) + ") " + sacola.get(i).getBookName() + espacamento.repeat(35 - sacola.get(i).getBookName().length()) + "(" + sacola.get(i).getQuantidade() + "x " + format.format(sacola.get(i).getPreco()) + ")  -  " + format.format(sacola.get(i).getQuantidade() * sacola.get(i).getPreco()) + "R$");
				total += sacola.get(i).getQuantidade() * sacola.get(i).getPreco();
			}
			System.out.println("Valor total: " + format.format(total) + "R$");
			return true;
		} else {
			System.out.println("\nO carrinho esta vazio.");
			return false;
		}
	}
	
	public void adicionarItem(int idLivro, String nomeLivro, String categoria, int quantidade, double preco) {
		Item item = new Item(quantidade, nomeLivro, idLivro, preco, categoria);	
		
		this.total += (preco * quantidade);
		this.quantidadeLivros += quantidade;
		sacola.add(item);
	}

	public void removerItem(ArrayList<Livro> catalogo) {
		char opc;
		int item_removido;
		Ferramenta ferramenta = new Ferramenta();
		
		if (verificarCarrinho()) {
			System.out.println("\nDeseja remover algum item? (S/N)");
			opc = ferramenta.scan().toUpperCase().charAt(0);
			
			switch (opc) {
			case 'S':
				item_removido = menu.textoRemoverItem(sacola.size());
				catalogo.get(sacola.get(item_removido - 1).getBookID() - 1).setQuantidadeEstoque(catalogo.get(sacola.get(item_removido - 1).getBookID() - 1).getQuantidadeEstoque() + sacola.get(item_removido - 1).getQuantidade());
				sacola.remove(item_removido - 1);
			}	
		} 
	}
	
	public int pagamento(Cliente cliente, ArrayList<Livro> catalogo) {
		if (verificarCarrinho()) {
			double frete;
			Pedido pedido = new Pedido();
			String cep, enderecoCompleto, formaPagamento = "";
			Ferramenta ferramenta = new Ferramenta();
			 
			while (true) {
				switch (menu.textoProsseguirPagamento()) {
					case 1:
						do {
							cep = menu.textoObterCep();
						
							if (cep.charAt(0) == '6') {
								frete = 35;
							} else if (cep.charAt(0) == '4') {
								frete = 25;
							} else {
								frete = 10;
							}
							
							System.out.println("\nLivros:...........: " + format.format(this.total) + "R$");
							System.out.println("Frete:............: " + format.format(frete) + "R$");
							System.out.println("Total:............: " + format.format(this.total + frete) + "R$");
								
						} while (menu.textoAlterarCep() != 1);
						
						switch (menu.textoFormaPagamento()) {
							case 1:
								formaPagamento = "cartao de credito";
								menu.textoDadosCartao();
								break;
							case 2:
								System.out.println("O boleto foi enviado para seu email.\n");
								formaPagamento = "boleto bancario";
						}
						
						enderecoCompleto = menu.textoObterEndereco();
						
						System.out.println("Pagamento feito com sucesso, obrigado!\n");
						
						pedido.adicionarPedido(sacola, true, formaPagamento, enderecoCompleto, this.total, frete, this.total + frete);
						pedido.setCodigoDePedido(ferramenta.gerarPedido());
						cliente.adicionarCompra(pedido);
						cliente.atualizarQuantidadeComprada(sacola);
						return 1;
					case 2:
						removerItem(catalogo);
						if (!verificarCarrinho()) {
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
}
