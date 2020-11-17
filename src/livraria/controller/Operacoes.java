package livraria.controller;
import java.util.ArrayList;
import livraria.model.Cliente;
import livraria.model.Funcionario;
import livraria.model.Livro;
import livraria.view.Menu;

public class Operacoes {
	private Menu menu = new Menu();
	private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	private ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
	
	public int realizarLogin(String tipoLogin) {
		int indexContaLogada;
		String email, senha, nome;
		Ferramenta ferramenta = new Ferramenta();
		
		System.out.print("email: ");
		email = ferramenta.scan();
		System.out.print("senha: ");
		senha = ferramenta.scan();
		
		switch (tipoLogin) {
			case "cliente":
				for (indexContaLogada = 0; indexContaLogada < this.clientes.size(); indexContaLogada++) {			
					if (this.clientes.get(indexContaLogada).getEmail().equals(email)) {
						if (this.clientes.get(indexContaLogada).getSenha().equals(senha)) {
							nome = this.clientes.get(indexContaLogada).getNome();
							System.out.println("\nLogin realizado com sucesso!\nBem vindo " + nome + "\n");
							return indexContaLogada;
						} else {
							break;
						}
					}
				}
				break;
				
			case "funcionario":
				for (indexContaLogada = 0; indexContaLogada < this.funcionarios.size(); indexContaLogada++) {			
					if (this.funcionarios.get(indexContaLogada).getEmail().equals(email)) {
						if (this.funcionarios.get(indexContaLogada).getSenha().equals(senha)) {
							nome = this.funcionarios.get(indexContaLogada).getNome();
							System.out.println("\nLogin realizado com sucesso!\nBem vindo " + nome + "\n");
							return indexContaLogada;
						} else {
							break;
						}
					}
				}
		}
		System.out.println("\nemail e/ou senha incorretos.");
		return -1;
	}
	
	public void registrarConta(int idUsuario, String cargo) {
		ArrayList<String> dados = new ArrayList<String>();
		
		menu.textoRealizarCadastro(dados, cargo);
		switch (cargo) {
			case "cliente":
				Cliente cliente = new Cliente(idUsuario, dados.get(0), dados.get(1), dados.get(2), dados.get(3), dados.get(4), dados.get(5), dados.get(6), dados.get(7), dados.get(8), dados.get(9), cargo, dados.get(10), dados.get(11), dados.get(12));
				clientes.add(cliente);
				break;
			case "funcionario":
				Funcionario funcionario = new Funcionario(idUsuario, dados.get(0), dados.get(1), dados.get(2), dados.get(3), dados.get(4), dados.get(5), dados.get(6), dados.get(7), dados.get(8), dados.get(9), cargo, dados.get(10), dados.get(11), dados.get(12));
				funcionarios.add(funcionario);
		}
	}

	public String mostrarCatalogo(ArrayList<Livro> livros) {
		String categoria = menu.textoEscolherGenero();
		
		System.out.println("\nLivros da categoria " + categoria);
		for (int i = 0; i < livros.size(); i++) {
			if (livros.get(i).getCategoria().trim().equals(categoria)) {
				System.out.println("\n\tID:.....................: " + livros.get(i).getID());
				System.out.println("\tTitulo:.................: " + livros.get(i).getBookName());
				System.out.println("\tPreco:..................: " + livros.get(i).getPreco() + "R$");
				System.out.println("\tAutor:..................: " + livros.get(i).getAuthorName());
				System.out.println("\tEditora:................: " + livros.get(i).getEditora());
				System.out.println("\tQuantidade em estoque:..: " + livros.get(i).getQuantidadeEstoque());
				System.out.println("\tISBN:...................: " + livros.get(i).getISBN() + "\n");
			}
		}
		
		return categoria;
	}

	public void realizarCompra(ArrayList<Livro> catalogo, Cliente cliente) {
		int idSelecionado, quantidade = 0;
		String categoria = "";
		//Carrinho "limpo"
		Carrinho carrinho = new Carrinho();
		
		while (true) {			
			switch (menu.textoCompra()) {
			case 1:
				categoria = mostrarCatalogo(catalogo);
				
				switch (menu.textoAdicionar()) {
				case 1:
					idSelecionado = menu.textoObterID(catalogo.size());
					
					for (int i = 0; i < catalogo.size(); i++) {
						if (catalogo.get(i).getID() == idSelecionado) {
							//verifica se ha estoque
							if (catalogo.get(i).getQuantidadeEstoque() == 0) {
								System.out.println("Este livro esta sem estoque no momento. :(");
								break;
							} else if (!catalogo.get(i).getCategoria().trim().equals(categoria)) {
								System.out.println("O ID escolhido nao foi encontrado para a categoria " + categoria.toLowerCase() + ".");
								break;
							} else {
								quantidade = menu.textoObterQuantidade(catalogo.get(i).getQuantidadeEstoque());
								
								//atualiza a quantidade de livros em estoque
								catalogo.get(i).setQuantidadeEstoque(catalogo.get(i).getQuantidadeEstoque() - quantidade);
								
								//adiciona item ao carrinho
								carrinho.adicionarItem(idSelecionado, catalogo.get(i).getBookName(), categoria, quantidade, catalogo.get(i).getPreco());
									
								System.out.println("Livro adicionado ao carrinho!");
							}									
						}
					}
					break;
				case 2:
					continue;
				}
				break;
			//verificar carrinho
			case 2:
				carrinho.verificarCarrinho();
				break;
			//remover item do carrinho
			case 3:
				carrinho.removerItem(catalogo);
				break;
			//IR PARA PAGAMENTO
			case 4:
				carrinho.pagamento(cliente);
				return;
			//VOLTAR
			case 5:
				return;
			}
		}
	}
	
	public Operacoes(ArrayList<Cliente> clientes, ArrayList<Funcionario> funcionarios) {
		this.clientes = clientes;
		this.funcionarios = funcionarios;
	}
}
