package livraria_3;
import java.util.ArrayList;

public class Compra {
	private ArrayList<Pedido> sacola = new ArrayList<Pedido>();
	private String CEP;
	private double total = 0;
	private boolean pago;
	private boolean entregue;
	
	public void infoIndividual() {
		System.out.println("Foram comprados " + sacola.size() + " livros.");
		for (int i = 0; i < sacola.size(); i++) {
			System.out.println("ID do livro: " + sacola.get(i).idLivro);
			System.out.println("Nome do livro: " + sacola.get(i).nomeLivro);
			System.out.println("Quantidade comprada: " + sacola.get(i).quantidade);
			System.out.println("Preco: " + sacola.get(i).preco);
		}
	}
	
	public void adicionarItem(int idLivro, String nomeLivro, int quantidade, double preco) {
		Pedido pedido = new Pedido();
		pedido.idLivro = idLivro;
		pedido.nomeLivro = nomeLivro;
		pedido.quantidade = quantidade;
		pedido.preco = preco;
		this.total += preco;
		sacola.add(pedido);
	}
	
	public ArrayList<Livro> removerItem(ArrayList<Livro> catalogo) {
		int item_removido;
		Ferramentas ferramenta = new Ferramentas();
		System.out.println("Atualmente seu carrinho conta com os itens: ");
		for (int i = 0; i < sacola.size(); i++) {
			System.out.println("(" + (i+1) + ") " + sacola.get(i).nomeLivro + "(" + sacola.get(i).quantidade + "x)  -  " + sacola.get(i).quantidade * sacola.get(i).preco + "R$");
		}
		do {
			System.out.println("Qual item deseja remover?");
			item_removido = ferramenta.scanInt();
		} while (item_removido < 1 && item_removido > sacola.size());
		
		catalogo.get(sacola.get(item_removido - 1).idLivro).setQuantidadeEstoque(catalogo.get(sacola.get(item_removido - 1).idLivro).getQuantidadeEstoque() + sacola.get(item_removido - 1).quantidade);
		return catalogo;
	}
	
	public void pagamento() {
		int entrega;
		Ferramentas scan = new Ferramentas();
		System.out.println("Insira seu CEP: ");
		this.CEP = scan.scan();
		if (this.CEP.charAt(0) == '6') {
			entrega = 30;
		} else {
			entrega = 10;
		}
		System.out.println("Livros:............ " + (this.total - entrega));
		System.out.println("Entrega:........... " + entrega);
		System.out.println("Total a ser pago:.. " + this.total);
		/*
		System.out.println("Numero do cartao: ");
		System.out.println("Validade: ");
		System.out.println("Codigo de Seguranca: ");
		*/
		
		this.pago = true;
	}
	
	public boolean getStatusPagamento() {
		return this.pago;
	}
	
	public String getCEP() {
		return this.CEP;
	}
}
