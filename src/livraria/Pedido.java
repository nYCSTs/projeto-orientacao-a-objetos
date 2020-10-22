package livraria;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Pedido {
	private boolean statusPagamento;
	private String formaPagamento;
	private ArrayList<Item> itensComprados = new ArrayList<Item>();
	private String orderCode;
	private boolean enviado = false;
	
	public Item criarItem(int quantidade, String bookName, int bookID, double preco, String categoria) {
		Item item = new Item(quantidade, bookName, bookID, preco, categoria);
		return item;
	}
	
	public void adicionarPedido(ArrayList<Item> sacola, boolean statusPagamento, String formaPagamento) {
		this.statusPagamento = statusPagamento;
		this.formaPagamento = formaPagamento;
		for (int i = 0; i < sacola.size(); i++) {
			itensComprados.add(criarItem(sacola.get(i).getQuantidade(), sacola.get(i).getBookName(), sacola.get(i).getBookID(), sacola.get(i).getPreco(), sacola.get(i).getCategoria()));
		}
	}
	
	public void mostrarItens() {
		DecimalFormat format = new DecimalFormat("#0.00");
		
		System.out.println("\tPedido #" + this.orderCode);
		for (int i = 0; i < itensComprados.size(); i++) {
			System.out.println("\tID..............: " + itensComprados.get(i).getBookID());
			System.out.println("\tCategoria.......: " + itensComprados.get(i).getCategoria());
			System.out.println("\tNome............: " + itensComprados.get(i).getBookName());
			System.out.println("\tQuantidade......: " + itensComprados.get(i).getQuantidade() + "x");
			System.out.println("\tPreco...........: " + format.format(itensComprados.get(i).getPreco()) + "R$\n");
		}
		System.out.println("\tPedido enviado: " + this.enviado + "\n\n");
	}
	
	//SET
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	
	public void setStatusEnvio(boolean status) {
		this.enviado = status;
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
