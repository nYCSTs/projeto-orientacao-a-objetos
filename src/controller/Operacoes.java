package controller;
import java.util.ArrayList;
import model.Cliente;
import model.Funcionario;
import model.Livro;
import view.Menu;


/**
 * Realiza algumas operacoes da aplicacao, como a parte de login e de cadastro, seja de clientes seja de funcionarios. Tambem e o responsavel por realizar a operacoes de mostrar o catalogo e de iniciar o processo de compra
 * @author lucas
 * @version 1.0 (Nov 2020)
 */
public class Operacoes {
	private int indexContaLogada = -1;
	private boolean estadoLogin = false;
	private Menu menu = new Menu();
	private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	private ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
	
	/**
	 * Realiza o login dos clientes. E pedido um email e senha. E verificado se o email esta presente na lista de cadastrados, estando verifica se a senha correesponde. Em caso negativo informa o visitante do erro e continua deslogado
	 * @param tipoLogin a operacoes para realizar o login de cliente e funcionario e praticamente a mesma, sendo assim o metodo e reaproveitado. Para controlar qual tipo de conta sera logada, e passado o tipo de conta a ser logada (cliente ou funcionario)
	 * @return caso o login seja feito com sucesso retorna a posicao do cliente/funcionario no ArrayList. Caso haja algum erro de login e retornado o valor -1
	 */
	public void realizarLogin(String tipoLogin) {
		int indexContaLogada;
		String email, senha, nome;
		
		email = menu.textObterEmail();
		senha = menu.textoObterSenha();
		
		switch (tipoLogin) {
			case "cliente":
				for (indexContaLogada = 0; indexContaLogada < this.clientes.size(); indexContaLogada++) {			
					if (this.clientes.get(indexContaLogada).getEmail().equals(email)) {
						if (this.clientes.get(indexContaLogada).getSenha().equals(senha)) {
							nome = this.clientes.get(indexContaLogada).getNome();
							System.out.println("\nLogin realizado com sucesso!\nBem vindo " + nome);
							this.indexContaLogada = indexContaLogada;
							this.estadoLogin = true;
							return;
						} else {
							this.indexContaLogada = -1;
							this.estadoLogin = false;
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
							this.indexContaLogada = indexContaLogada;
							this.estadoLogin = true;
							return;
						} else {
							this.indexContaLogada = -1;
							this.estadoLogin = false;
						}
					}
				}
		}
		System.out.println("\nemail e/ou senha incorretos.");
	}
	
	public int getIndexContaLogada() {
		return this.indexContaLogada;
	}
	
	public boolean getEstadoLogin() {
		return this.estadoLogin;
	}
	
	/**
	 * realiza o criacao de uma nova conta, de <b>cliente</b> ou de <b>funcionario</b>
	 * @param idUsuario o id que sera atribuido a nova conta
	 * @param cargo que tipo de conta sera criada, de Cliente ou de Funcionario
	 */
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

	/**
	 * Mostra os livros cadastrados, independente da quantidade em estoque. Caso a pessoa olhando o catalogo esteja deslogada e pedido para que se realize o login ou o cadastro para que seja possivel adicionar itens ao carrinho
	 * @param livros todos os livros cadastrados na livraria
	 * @param statusLogin usado para controlar se o visitante/cliente tera a possibilidade de escolher um ID a ser adicionado ao carrinho. Essa possibilidade so e acessivel para a pessoa logada em sua conta
	 * @return caso o cliente nao esteja logado retorna 0, caso contrario, retorna o valor do metodo textoAdicionar
	 */
	public int mostrarCatalogo(ArrayList<Livro> livros, boolean statusLogin) {
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
			if (!statusLogin) {
				System.out.println("Para adiciona-los ao carrinho realize o login, caso nao possua uma conta, realize o cadastro.");
			} else {
				return menu.textoAdicionar();
			}
		} 

		return 0;
	}

	/**
	 * Inicia o processo de compra
	 * @param livros mostra o catalogo ao cliente
	 * @param cliente o cliente que esta realizando a compra
	 */
	public void realizarCompra(ArrayList<Livro> livros, Cliente cliente) {
		int idSelecionado, quantidade = 0;
		Carrinho carrinho = new Carrinho();
		
		while (true) {			
			switch (menu.textoCompra()) {
				case 1:
					switch (mostrarCatalogo(livros, true)) {
						case 1:
							idSelecionado = menu.textoObterID(livros.size());
							//procura pelo id escolhido
							if (livros.get(idSelecionado - 1).getID() == idSelecionado) {
								//verifica se ha estoque
								if (livros.get(idSelecionado - 1).getQuantidadeEstoque() == 0) {
									System.out.println("Este livro esta sem estoque no momento. :(");
								} else {
									switch (menu.textoConfirmarItem(livros.get(idSelecionado - 1))) {
										case 1:
											quantidade = menu.textoObterQuantidade(livros.get(idSelecionado - 1).getQuantidadeEstoque());
											//atualiza a quantidade de livros em estoque
											livros.get(idSelecionado - 1).setQuantidadeEstoque(livros.get(idSelecionado - 1).getQuantidadeEstoque() - quantidade);
											//adiciona item ao carrinho
											carrinho.adicionarItem(idSelecionado, livros.get(idSelecionado - 1).getBookName(), livros.get(idSelecionado - 1).getCategoria(), quantidade, livros.get(idSelecionado - 1).getPreco());
											System.out.println("Livro adicionado ao carrinho!");
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
					carrinho.removerItem(livros);
					break;
				//ir para pagamento
				case 4:
					switch(carrinho.prosseguirPagamento(cliente, livros)) {
						case 1:
							return;
						}
					break;
				//retornar
				case 5:
					for (int i = 0; i < carrinho.getSacola().size(); i++) {
						livros.get(carrinho.getSacola().get(i).getBookID() - 1).setQuantidadeEstoque(carrinho.getSacola().get(i).getQuantidade() + livros.get(carrinho.getSacola().get(i).getBookID() - 1).getQuantidadeEstoque());
					}
					return;
			}
			
		}
	}

	/**
	 * Construtor 
	 * @param clientes lista de clientes cadastrados
	 * @param funcionarios lista de funcionarios cadastrados
	 */
	public Operacoes(ArrayList<Cliente> clientes, ArrayList<Funcionario> funcionarios) {
		this.clientes = clientes;
		this.funcionarios = funcionarios;
	}
}
