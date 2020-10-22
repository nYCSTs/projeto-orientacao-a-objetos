package livraria;

public class Item {
	private int quantidade;
	private String bookName;
	private int bookID;
	private double preco;
	private String categoria;
	
	public Item(int quantidade, String bookName, int bookID, double preco, String categoria) {
		this.quantidade = quantidade;
		this.bookName = bookName;
		this.bookID = bookID;
		this.preco = preco;
		this.categoria = categoria;
	}
	
	public int getBookID() {
		return this.bookID;
	}
	
	public int getQuantidade() {
		return this.quantidade;
	}
	
	public double getPreco() {
		return this.preco;
	}
	
	public String getBookName() {
		return this.bookName;
	}
	
	public String getCategoria() {
		return this.categoria;
	}
}
