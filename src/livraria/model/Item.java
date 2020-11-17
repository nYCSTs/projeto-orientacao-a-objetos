package livraria.model;

public class Item {
	private int quantidade;
	private String nomeDoLivro ;
	private int idDoLivro;
	private double preco;
	private String categoria;
	
	public Item(int quantidade, String nomeDoLivro, int idDoLivro, double preco, String categoria) {
		this.quantidade = quantidade;
		this.nomeDoLivro = nomeDoLivro;
		this.idDoLivro = idDoLivro;
		this.preco = preco;
		this.categoria = categoria;
	}
	
	public int getBookID() {
		return this.idDoLivro;
	}
	
	public int getQuantidade() {
		return this.quantidade;
	}
	
	public double getPreco() {
		return this.preco;
	}
	
	public String getBookName() {
		return this.nomeDoLivro;
	}
	
	public String getCategoria() {
		return this.categoria;
	}
}
