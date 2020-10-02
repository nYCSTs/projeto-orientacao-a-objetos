package livraria_3;
import java.util.ArrayList;
/* TO DO
 * solicitar endereco na parte do pagamento
 * info
 * mostrar todos itens antes de realizar compra
 * deixar tudo public
 * arrumar caso de 0 em "insira o id do livro desejado (insira 0 para retornar a selecao de categoria)"
 * 
 * limpeza de tela apos realizar cadastro e logar
 * melhorar cadastro, colocando restricoes
 * comentar mais o codigo
 * melhorar textos
 */

/*  PERGUNTAS
 *  main cheia, e um problema?
 *  limpar tela
 *  perguntar sobre praticamente toda parte final do texto 
 *  quem pode ver a info?
 *  o que e filiação, cargo
 */

public class Livraria {		
	public static void main(String[] args) {
		IO leitura = new IO();
		Livro livro = new Livro();
		Ferramentas ferramenta = new Ferramentas();
		Usuario usuario = new Usuario();
		Compra compra = new Compra();
		Info info = new Info();

		ArrayList<Livro> catalogo = new ArrayList<Livro>();
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

		int input, userID = 1, loggedAccount = 0, id_sel = -1, quantidade, qnt_total = 0;
		boolean login_state = false, exit = false;
		
		//Leitura usuarios
		usuarios = leitura.leituraUsuarios();
		userID = leitura.getUserID();
		
		//Leitura livros
		catalogo = leitura.leituraLivros();

		while (!exit) {
			//NAO LOGADO
			if (login_state == false) {
				System.out.println("\t1 - Realizar login\n\t2 - Realizar cadastro\n\t3 - Verificar catalogo\n\t4 - Sair\n>> ");	
				input = ferramenta.scanInt();
				switch (input) {
				//login
				case 1:
					String email, senha, nome = "";
					System.out.print("email: ");
					email = ferramenta.scan();
					System.out.print("senha: ");
					senha = ferramenta.scan();
					
					for (loggedAccount = 0; loggedAccount < usuarios.size(); loggedAccount++) {			
						if (usuarios.get(loggedAccount).getEmail().compareTo(email) == 0) {
							if (usuarios.get(loggedAccount).getSenha().compareTo(senha) == 0) {
								nome = usuarios.get(loggedAccount).getNome();
								login_state = true;	
								break;
							} 
						}
					}
					System.out.println(login_state ? "\nLogin realizado com sucesso! Bem vindo " + nome : "\nemail e/ou senha incorretos.");
					break;
				//cadastro
				case 2:
					usuarios.add(usuario.criarConta(userID));  
					leitura.registrarUsuario(usuarios.get(userID - 1), userID);
					userID++;
					break;
					
				//verificar catalogo
				case 3:
					do {
						System.out.println("\t1 - Infantil\n\t2 - Tecnico\n\t3 - Ficcao\n\t4 - Não Ficcao");
						input = ferramenta.scanInt();
					} while (input < 1 || input > 4);
					switch (input) {
					case 1:
						livro.mostrarCatalogo("INFANTIL", catalogo);
						break;
					case 2:
						livro.mostrarCatalogo("TECNICO", catalogo);
						break;
					case 3:
						livro.mostrarCatalogo("FICCAO", catalogo);
						break;
					case 4:
						livro.mostrarCatalogo("NAO FICCAO", catalogo);
						break;
					}
					break;
				//sair
				case 4:
					exit = true;
				}
			} 
			
			//LOGADO
			else {
				do {
					System.out.println("\t1 - Verificar catalogo\n\t2 - INFO\n\t3 - Logout\n\t4 - Sair");
					input = ferramenta.scanInt();
				} while (input < 1 || input > 4);
				
				//verificar catalogo
				if (input == 1) {
					while (!exit) {
						do {
							System.out.println("\t1 - Infantil\n\t2 - Tecnico\n\t3 - Ficcao\n\t4 - Não Ficcao");
							input = ferramenta.scanInt();
						} while (input < 1 || input > 4);
						
						switch (input) {
						case 1:
							livro.mostrarCatalogo("INFANTIL", catalogo);
							break;
						case 2:
							livro.mostrarCatalogo("TECNICO", catalogo);
							break;
						case 3:
							livro.mostrarCatalogo("FICCAO", catalogo);
							break;
						case 4:
							livro.mostrarCatalogo("NAO FICCAO", catalogo);
							break;
						}	
						
						do {
							System.out.println("\n\t1 - Adicionar livro ao carrinho\n\t2 - Voltar a escolha de categoria\n\t3 - Editar carrinho\n\t4 - Prosseguir para compra");
							input = ferramenta.scanInt();
						} while (input < 1 || input > 2);
						
						//exit e false
						if (input == 1) {
							while (!exit) {				
								do {
									System.out.println("Insira o ID do livro desejado (insira 0 para retornar a selecao de categoria): ");
									id_sel = ferramenta.scanInt();
								} while (catalogo.size() < id_sel && id_sel != 0);
								
								
								for (int i = 0; i < catalogo.size() && id_sel != 0; i++) {
									if (catalogo.get(i).getID() == id_sel) {
										if (catalogo.get(i).getQuantidadeEstoque() == 0) {
											System.out.println("Este livro esta indisponivel no momento. :(");
											exit = true;
											break;
										} else {
											do {
												System.out.println("Quantidade: ");
												quantidade = ferramenta.scanInt();
											} while (catalogo.get(i).getQuantidadeEstoque() < quantidade);
											catalogo.get(i).setQuantidadeEstoque(catalogo.get(i).getQuantidadeEstoque() - quantidade);
											compra.adicionarItem(i, catalogo.get(i).getBookName(), quantidade, catalogo.get(i).getPreco());
											System.out.println("Livro adicionado ao carrinho!\n");
											qnt_total += quantidade;
										}									
										
										while (!exit) {
											System.out.println("\t1 - Continuar comprando\n\t2 - Editar carrinho\n\t3 - Prosseguir para pagamento");
											input = ferramenta.scanInt();
											
											if (input == 1) {
												exit = true;
											} else if (input == 2) {
												catalogo = compra.removerItem(catalogo);
												qnt_total -= quantidade;
											} else {
												exit = true;
												id_sel = -1;
											}
										}
										break;
									}
								}
							}

							if (id_sel == -1) {
								compra.pagamento();
								if (compra.getStatusPagamento()) {
									System.out.println(compra.getCEP().charAt(0) == '6' ? "\nObrigado, seu pedido sera entregue daqui a 10 dias." : "\nObrigado, seu pedido sera entregue daqui a 5 dias");
									usuarios.get(loggedAccount).addCompra(qnt_total, compra);
								}
 							} else {
 								exit = false;
 								continue;
 							}
						} else if (input == 2) {
							exit = false;
							continue;
						} else if (input == 3) {
							compra.pagamento();
							if (compra.getStatusPagamento()) {
								System.out.println(compra.getCEP().charAt(0) == '6' ? "\nObrigado, seu pedido sera entregue daqui a 10 dias." : "\nObrigado, seu pedido sera entregue daqui a 5 dias");
								usuarios.get(loggedAccount).addCompra(qnt_total, compra);
							}
						}
						break;
					}
					
				//INFO
				} else if (input == 2) {
					System.out.println("1 - Livros em estoque\n\t2 - Dados de todos usuarios\n\t3 - \n");
					info.dadosUsuario(usuarios);
				}
				
				//LOGOUT
				else if (input == 3) {
					login_state = false;
				} 
				
				//FECHAR
				else {
					exit = true;
				}
			}
			
		}
	}
}
