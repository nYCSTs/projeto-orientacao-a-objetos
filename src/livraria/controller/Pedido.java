package livraria.controller;
import java.text.DecimalFormat;
import java.util.ArrayList;
import livraria.model.Item;

public class Pedido {
	private boolean statusPagamento;
	private String formaPagamento;
	private ArrayList<Item> itensComprados = new ArrayList<Item>();
	private String orderCode;
	private String endereco;
	private boolean enviado = false;
	private String funcionarioResponsavel;
	private double totalPedido;
	private double frete;
	private double total;
	
	public Item criarItem(int quantidade, String bookName, int bookID, double preco, String categoria) {
		Item item = new Item(quantidade, bookName, bookID, preco, categoria);
		return item;
	}
	
	public void adicionarPedido(ArrayList<Item> sacola, boolean statusPagamento, String formaPagamento, String endereco, double pedido, double frete, double total) {
		this.statusPagamento = statusPagamento;
		this.formaPagamento = formaPagamento;
		this.endereco = endereco;
		this.totalPedido = pedido;
		this.frete = frete;
		this.total = total;
		for (int i = 0; i < sacola.size(); i++) {
			itensComprados.add(criarItem(sacola.get(i).getQuantidade(), sacola.get(i).getBookName(), sacola.get(i).getBookID(), sacola.get(i).getPreco(), sacola.get(i).getCategoria()));
		}
	}
	
	public void mostrarItens() {
		DecimalFormat format = new DecimalFormat("#0.00");
		
		System.out.println("\tPedido #" + this.orderCode);
		for (int i = 0; i < itensComprados.size(); i++) {
			System.out.println("\tID..................: " + itensComprados.get(i).getBookID());
			System.out.println("\tCategoria...........: " + itensComprados.get(i).getCategoria());
			System.out.println("\tNome................: " + itensComprados.get(i).getBookName());
			System.out.println("\tQuantidade..........: " + itensComprados.get(i).getQuantidade() + "x");
			System.out.println("\tPreco...............: " + format.format(itensComprados.get(i).getPreco()) + "R$\n");
			
		}
		System.out.println("\tPedido......................: " + format.format(this.totalPedido));
		System.out.println("\tFrete.......................: " + this.frete);
		System.out.println("\tTotal.......................: " + format.format(this.total));
		System.out.println("\tEndereco....................: " + this.endereco);
		System.out.println("\tPedido encaminhado..........: " + this.enviado);
		if (this.enviado) {
			System.out.println("Responsavel por encaminhar....: " + this.funcionarioResponsavel);
		}
		System.out.println("\n");
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
}
