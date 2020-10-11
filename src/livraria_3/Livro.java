package livraria_3;

public class Livro {
	private String categoria;
	private int ID;
	private double preco;
	private int quantidadeEstoque;
	private String bookName;
	private String authorName;
	private String ISBN;
	private String editora;
	
	
	//SET
	public void setID(int id) {
		this.ID = id;
	}
	
	public void setEditora(String editora) {
		this.editora = editora;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public void setQuantidadeEstoque(int quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}

	//GET
	public int getID() {
		return this.ID;
	}
	
	public String getEditora() {
		return this.editora;
	}
	
	public String getCategoria() {
		return this.categoria;
	}
	
	public double getPreco() {
		return this.preco;
	}
	
	public int getQuantidadeEstoque() {
		return this.quantidadeEstoque;
	}
	
	public String getBookName() {
		return this.bookName;
	}
	
	public String getAuthorName() {
		return this.authorName;
	}
	
	public String getISBN() {
		return this.ISBN;
	}
	
	
}
