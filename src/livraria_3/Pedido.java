package livraria_3;

public class Pedido {
	private int quantidade;
	private String nomeLivro;
	private int idLivro;
	private double preco;
	private String categoria;
	private boolean pago;
	private String formaPagamento;
	
	public Pedido(int idLivro, String nomeLivro, String categoria, int quantidade, double preco) {
		this.idLivro = idLivro;
		this.nomeLivro = nomeLivro;
		this.categoria = categoria;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	
	//SET
	public void setPagamento(boolean pago, String formaPagamento) {
		this.pago = true;
		this.formaPagamento = formaPagamento;
	}
	
	
	//GET
	public int getIDLivro() {
		return this.idLivro;
	}
	
	public int getQuantidade() {
		return this.quantidade;
	}
	
	public double getPreco() {
		return this.preco;
	}
	
	public String getNomeLivro() {
		return this.nomeLivro;
	}
	
	public String getCategoria() {
		return this.categoria;
	}
	
	public String getFormaPagamento() {
		return this.formaPagamento;
	}
	
	public boolean getStatusPagamento() {
		return this.pago;
	}
}
