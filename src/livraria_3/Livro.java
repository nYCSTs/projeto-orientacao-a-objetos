package livraria_3;

public class Livro {
	private String categoria;
	private int ID;
	private int quantidadeEstoque;
	private String bookName;
	private String authorName;
	private String ISBN;
	
	/*
	public void criarLivro(int id, int quantidadeEstoque, String bookName, String authorName, String ISBN) {
		this.ID = id;
		this.quantidadeEstoque = quantidadeEstoque;
		this.bookName = bookName;
		this.authorName = authorName;
		this.ISBN = ISBN;
	}
	*/
	public void setID(int id) {
		this.ID = id;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
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


		//GEET
	public int getID() {
		return this.ID;
	}
	
	public String getCategoria() {
		return this.categoria;
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
