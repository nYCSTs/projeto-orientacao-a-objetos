package livraria_3;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class Carrinho {
	private ArrayList<Item> sacola = new ArrayList<Item>();
	public double total = 0;
	public int quantidadeLivros = 0;
	private DecimalFormat format = new DecimalFormat("#0.00");
	
	public boolean verificarCarrinho() {
		double total = 0;

		if (sacola.size() > 0) {
			System.out.println("Atualmente seu carrinho conta com os itens: ");
			for (int i = 0; i < sacola.size(); i++) {
				System.out.println("(" + (i+1) + ") " + sacola.get(i).getBookName() + " (" + sacola.get(i).getQuantidade() + "x)  -  " + format.format(sacola.get(i).getQuantidade() * sacola.get(i).getPreco()) + "R$");
				total += sacola.get(i).getQuantidade() * sacola.get(i).getPreco();
			}
			System.out.println("Total: " + format.format(total) + "R$");
			return true;
		} else {
			System.out.println("O carrinho esta vazio.");
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
		Ferramentas ferramenta = new Ferramentas();
		
		if (verificarCarrinho()) {
			System.out.println("Deseja remover algum item? (S/N)");
			opc = ferramenta.scan().toUpperCase().charAt(0);
			
			switch (opc) {
			case 'S':
				do {
					System.out.println("Qual item deseja remover?");
					item_removido = ferramenta.scanInt();
				} while (item_removido < 1 && item_removido > sacola.size());
				
				catalogo.get(sacola.get(item_removido - 1).getBookID() - 1).setQuantidadeEstoque(catalogo.get(sacola.get(item_removido - 1).getBookID() - 1).getQuantidadeEstoque() + sacola.get(item_removido - 1).getQuantidade());
				sacola.remove(item_removido - 1);
			}	
		} 
	}
	
	public void pagamento(Usuario usuario) {
		if (verificarCarrinho()) {
			int input;
			double frete;
			Pedido pedido = new Pedido();
			String cep, pagamento = "";
			boolean statusPagamento = false;
			Ferramentas ferramenta = new Ferramentas();
			 
			do {
				System.out.println("\n\t1 - Continuar com pagamento\n\t2 - Voltar ao menu anterior");
				input = ferramenta.scanInt();
			} while (input < 1 && input > 2);
			
			switch (input) {
			case 1:
				do {
				System.out.println("CEP para entrega: ");
				cep = ferramenta.scan();
					if (cep.charAt(0) == '6') {
						frete = 35;
					} else if (cep.charAt(0) == '4') {
						frete = 25;
					} else {
						frete = 10;
					}
					
					System.out.println("Livros.......: " + format.format(this.total) + "R$");
					System.out.println("Frete........: " + frete + "R$");
					System.out.println("Total........: " + format.format(this.total + frete) + "R$");
					
					do {
						System.out.println("\n\t1 - Finalizar pedido\n\t2 - Mudar CEP");
						input = ferramenta.scanInt();
					} while (input < 1 && input > 2);
				} while (input != 1);
				
				do {
					System.out.println("\n\tForma de pagamento: \n1 - Cartao\n2 - Boleto");
					input = ferramenta.scanInt();
				} while (input < 1 && input > 2);
				
				switch (input) {
				case 1:
					pagamento = "cartao de credito";
					System.out.println("Insira os numeros do cartao de credito: ");
					ferramenta.scan();
					System.out.println("Insira os digitos de seguranca: ");
					ferramenta.scan();
					System.out.println("Pagamento feito com sucesso, obrigado!\n");
					statusPagamento = true;
					
					break;
				case 2:
					System.out.println("O boleto foi enviado para seu email.\n");
					pagamento = "boleto bancario";
					statusPagamento = false;
					
					break;
				}
				
				pedido.adicionarPedido(sacola, statusPagamento, pagamento);
				pedido.setOrderCode(ferramenta.generatePedido().toUpperCase());
				usuario.adicionarCompra(pedido);

				return;
			case 2:
				return;
			}
		} 
	}
}
