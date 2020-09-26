package livraria_3;

public class Livro {
	private int ID;
	private int quantidadeEstoque;
	private String bookName;
	private String authorName;
	private String ISBN;
	
	public void criarLivro(int quantidadeEstoque, String bookName, String authorName, String ISBN) {
		this.quantidadeEstoque = quantidadeEstoque;
		this.bookName = bookName;
		this.authorName = authorName;
		this.ISBN = ISBN;
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
	
	public int getID() {
		return this.ID;
	}
	
	
	public Livro(int id) {
		this.ID = id;
	}
}
