package livraria_3;
import java.util.ArrayList;

public class Livro {
	private String categoria;
	private int ID;
	private double preco;
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
	public void mostrarCatalogo(String categoria, ArrayList<Livro> livros) {
		for (int i = 0; i < livros.size(); i++) {
			if (livros.get(i).getCategoria().trim().equals(categoria)) {
				System.out.println("\n\tID:..................... " + livros.get(i).getID());
				System.out.println("\tPreco:.................. " + livros.get(i).getPreco() + "R$");
				System.out.println("\tQuantidade em estoque:.. " + livros.get(i).getQuantidadeEstoque());
				System.out.println("\tNome:................... " + livros.get(i).getBookName());
				System.out.println("\tAutor:.................. " + livros.get(i).getAuthorName());
				System.out.println("\tISBN:................... " + livros.get(i).getISBN() + "\n");
			}
		}
	}
	
	
	//SET
	public void setID(int id) {
		this.ID = id;
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


		//GEET
	public int getID() {
		return this.ID;
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
