package model;


/**
 * <div id="classe-item">Referente as caracteristicas dos livros</div>
 * @author lucas
 * @version 1.0 (Nov 2020)
 */
public class Item {
	private int quantidade;
	private int livroID;
	private double preco;
	private String livroNome;
	private String categoria;
	
	/**
	 * Construtor para a classe
	 * @param quantidade quantidade de livros
	 * @param livroNome nome do livro
	 * @param livroID id associado ao livro
	 * @param preco preco do livro
	 * @param categoria categoria do livro
	 */
	public Item(int quantidade, String livroNome, int livroID, double preco, String categoria) {
		this.quantidade = quantidade;
		this.livroNome = livroNome;
		this.livroID = livroID;
		this.preco = preco;
		this.categoria = categoria;
	}
	
	public int getBookID() {
		return this.livroID;
	}
	
	public int getQuantidade() {
		return this.quantidade;
	}
	
	public double getPreco() {
		return this.preco;
	}
	
	public String getBookName() {
		return this.livroNome;
	}
	
	public String getCategoria() {
		return this.categoria;
	}
}
