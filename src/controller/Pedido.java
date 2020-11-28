package controller;
import java.text.DecimalFormat;
import java.util.ArrayList;
import model.Item;

/**
 * Responsavel por manejar os pedidos feitos
 * @author lucas
 * @version 1.0 (Nov 2020)
 */
public class Pedido {	
	private double frete;
	private double totalItens;
	private double totalPedido;
	private boolean enviado = false;
	private boolean statusPagamento = true;
	private String endereco;
	private String orderCode;
	private String formaPagamento;
	private String funcionarioResponsavel;
	private ArrayList<Item> itensComprados = new ArrayList<Item>();
	
	/**
	 * Construir um objeto da <a href="classe-item">classe Item</a> para ser adicionado na sacola 
	 * @param quantidade quantidade de itens a ser adicionadas ao carrinho
	 * @param livroNome nome do livro adicionado ao carrinho
	 * @param livroID id do livro adicionado ao carrinho
	 * @param preco preco do livro adicionado ao carrinho
	 * @param categoria categoria do livro em questao
	 * @return o objeto em questao
	 */
	public Item criarItem(int quantidade, String livroNome, int livroID, double preco, String categoria) {
		Item item = new Item(quantidade, livroNome, livroID, preco, categoria);
		return item;
	}
	
	/**
	 * Apos o pagamento o pedido e confirmado e adicionado na lista de pedidos feitos pelo cliente. Somente realiza adicao do pedido ao ArrayList itensComprados, nao possuindo retorno
	 * @param sacola um <a href="https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html">ArrayList</a> de todos os itens presentes no carrinho 
	 * @param formaPagamento forma de pagamento escolhida pelo cliente
	 * @param endereco endereco completo do comprador
	 * @param pedido referente ao valor dos itens
	 * @param frete referente ao valor do frete
	 * @param total referente ao total (valor dos itens + valor do frete)
	 */
	public void adicionarPedido(ArrayList<Item> sacola, String formaPagamento, String endereco, double totalItens, double frete, double totalPedido) {
		this.formaPagamento = formaPagamento;
		this.endereco = endereco;
		this.totalItens = totalItens;
		this.frete = frete;
		this.totalPedido = totalPedido;
		for (int i = 0; i < sacola.size(); i++) {
			itensComprados.add(criarItem(sacola.get(i).getQuantidade(), sacola.get(i).getBookName(), sacola.get(i).getBookID(), sacola.get(i).getPreco(), sacola.get(i).getCategoria()));
		}
	}
	
	/**
	 * Responsavel por mostrar todos os itens de cada pedido. Sendo somente um metodo para exibir informacoes nao possui retorno 
	 */
	public void mostrarItens() {
		DecimalFormat format = new DecimalFormat("#0.00");
		
		System.out.println("\tPedido #" + this.orderCode);
		for (int i = 0; i < itensComprados.size(); i++) {
			System.out.println("\t\tID..................: " + itensComprados.get(i).getBookID());
			System.out.println("\t\tCategoria...........: " + itensComprados.get(i).getCategoria());
			System.out.println("\t\tNome................: " + itensComprados.get(i).getBookName());
			System.out.println("\t\tQuantidade..........: " + itensComprados.get(i).getQuantidade() + "x");
			System.out.println("\t\tPreco...............: " + format.format(itensComprados.get(i).getPreco() * itensComprados.get(i).getQuantidade()) + "R$ (" + format.format(itensComprados.get(i).getPreco()) + "R$ cada unidade) \n");
			
		}
		System.out.println("\tItens.......................: " + format.format(this.totalItens) + "R$");
		System.out.println("\tFrete.......................: " + format.format(this.frete) + "R$");
		System.out.println("\tTotal (Itens + Frete).......: " + format.format(this.totalPedido) + "R$");
		System.out.println("\tEndereco....................: " + this.endereco);
		System.out.println("\tPedido encaminhado..........: " + this.enviado + "\n");
	}
	
	//SET
	public void setCodigoDePedido(String orderCode) {
		this.orderCode = orderCode;
	}
	
	public void setStatusEnvio(boolean status) {
		this.enviado = status;
	}
	
	public void setFuncionarioResponsavel(String nome) {
		this.funcionarioResponsavel = nome;
	}
	
	//GET
	public String getFormaPagamento() {
		return this.formaPagamento;
	}
	
	public boolean getStatusPagamento() {
		return this.statusPagamento;
	}
	
	public String getOrderCode() {
		return this.orderCode;
	}
	
	public String getFuncionarioResponsavel() {
		return this.funcionarioResponsavel;
	}
	
	public boolean getEnviado() {
		return this.enviado;
	}
}
