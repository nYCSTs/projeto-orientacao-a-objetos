package controller;
import java.util.ArrayList;

import model.Cliente;
import model.Funcionario;
import model.Livro;
import view.Menu;

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
							System.out.println("\nLogin realizado com sucesso!\nBem vindo " + nome);
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
							System.out.println("\nLogin realizado com sucesso!\nBem vindo " + nome);
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

	public int mostrarCatalogo(ArrayList<Livro> livros, boolean status) {
		String entrada;
		int quantidadeEncontrada = 0;
		switch (menu.textoMetodoDeBusca()) {
			case 1:
				entrada = menu.textoBuscaTitulo();
				for (int i = 0; i < livros.size(); i++) {
					if (livros.get(i).getBookName().toLowerCase().trim().contains(entrada)) {
						menu.mostrarInfo(livros.get(i));
						quantidadeEncontrada += 1;
					}
				}
				
				break;
			case 2:
				entrada = menu.textoEscolherGenero();
				System.out.println("\nLivros da categoria " + entrada);
				for (int i = 0; i < livros.size(); i++) {
					if (livros.get(i).getCategoria().trim().equals(entrada)) {
						menu.mostrarInfo(livros.get(i));
						quantidadeEncontrada += 1;
					}
				}
		}
		System.out.println("Foram encontrados " + quantidadeEncontrada + " livro(s).");
		if (quantidadeEncontrada != 0) {
			if (!status) {
				System.out.println("Para adiciona-los ao carrinho realize o login, caso nao possua uma conta, realize o cadastro.");
			} else {
				return menu.textoAdicionar();
			}
		} 

		return 0;
		
	}

	public void realizarCompra(ArrayList<Livro> catalogo, Cliente cliente) {
		int idSelecionado, quantidade = 0;
		//Carrinho "limpo"
		Carrinho carrinho = new Carrinho();
		
		while (true) {			
			switch (menu.textoCompra()) {
			case 1:
				switch (mostrarCatalogo(catalogo, true)) {
					case 1:
						idSelecionado = menu.textoObterID(catalogo.size());

						if (catalogo.get(idSelecionado - 1).getID() == idSelecionado) {
							//verifica se ha estoque
							if (catalogo.get(idSelecionado - 1).getQuantidadeEstoque() == 0) {
								System.out.println("Este livro esta sem estoque no momento. :(");
								break;
							} else {
								switch (menu.textoConfirmarItem(catalogo.get(idSelecionado - 1))) {
									case 1:
										quantidade = menu.textoObterQuantidade(catalogo.get(idSelecionado - 1).getQuantidadeEstoque());
										
										//atualiza a quantidade de livros em estoque
										catalogo.get(idSelecionado - 1).setQuantidadeEstoque(catalogo.get(idSelecionado - 1).getQuantidadeEstoque() - quantidade);
										
										//adiciona item ao carrinho
										carrinho.adicionarItem(idSelecionado, catalogo.get(idSelecionado - 1).getBookName(), catalogo.get(idSelecionado - 1).getCategoria(), quantidade, catalogo.get(idSelecionado - 1).getPreco());
											
										System.out.println("Livro adicionado ao carrinho!");
										break;
								}									
							}
						}
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
				switch(carrinho.pagamento(cliente, catalogo)) {
				case 1:
					return;
				}
				break;
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
