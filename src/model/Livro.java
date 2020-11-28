/**
 * <div id="classe-livro></div>
 */
package model;

/**
 * Possui caracteristicas significativas para identificar os livros da livraria
 * @author lucas
 * @version 1.0 (Nov 2020)
 */
public class Livro {
	private int ID;
	private int quantidadePaginas;
	private int quantidadeEstoque;
	private double preco;
	private String nomeLivro;
	private String nomeAutor;
	private String ISBN;
	private String editora;
	private String idioma;
	private String categoria;
	
	
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

	public void setBookName(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}

	public void setAuthorName(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}
	
	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}
	
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	
	public void setQuantidadePaginas(int quantidadePaginas) {
		this.quantidadePaginas = quantidadePaginas;
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
		return this.nomeLivro;
	}
	
	public String getAuthorName() {
		return this.nomeAutor;
	}
	
	public String getISBN() {
		return this.ISBN;
	}	
	
	public String getIdioma() {
		return this.idioma;
	}
	
	public int getQuantidadePaginas() {
		return this.quantidadePaginas;
	}
}
