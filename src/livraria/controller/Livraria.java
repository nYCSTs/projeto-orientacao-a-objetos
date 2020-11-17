package livraria.controller;
import java.util.ArrayList;
import livraria.model.*;
import livraria.view.Menu;

// ----------------------------------------------------------- MAIN ----------------------------------------------------------- //

public class Livraria {		
	public static void main(String[] args) {	
		Menu menu = new Menu();
		IO leitura = new IO();
		
		ArrayList<Livro> catalogo = new ArrayList<Livro>();
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
		
		//Leitura clientes previamente cadastrados (eclipse-workspace\livraria\clientes e eclipse-workspace\livraria\funcionarios)
		leitura.leituraUsuarios(clientes, funcionarios);
		//Leitura de livros (eclipse-workspace\livraria\livros)
		catalogo = leitura.leituraLivros();
		
		Operacoes op = new Operacoes(clientes, funcionarios);
		
		int indexContaLogada = -1;
		//VARIAVEIS DE CONTROLE
		boolean sair = false, cliente = false, funcionario = false;
		
		while (!sair) {
			//DESLOGADO
			if (!cliente && !funcionario) {				
				switch (menu.textoDeslogado()) {
					//REALIZAR LOGIN
					case 1:
						switch (menu.textoLoginCadastro("login")) {
							//LOGIN CLIENTE
							case 1:
								indexContaLogada = op.realizarLogin("cliente");
								if (indexContaLogada != -1) {
									cliente = true;
								}
								break;
							//LOGIN FUNCIONARIO
							case 2:
								indexContaLogada = op.realizarLogin("funcionario");
								if (indexContaLogada != -1) {
									funcionario = true;
								}
						}
						break;
					//--------------------------------------------------------------------	
					//REALIZAR CADASTRO
					case 2:
						switch (menu.textoLoginCadastro("cadastro")) {
							//CADASTRO DE CLIENTES
							case 1:
								op.registrarConta(clientes.size() + 1, "cliente");
								//CRIA UM ARQUIVO .TXT PARA O CLIENTE CADASTRADO
								leitura.registrarUsuario(clientes.get(clientes.size() - 1), clientes.size(), "clientes/cliente-");
								break;	
							//CADASTRO DE FUNCIONARIO
							case 2:
								op.registrarConta(funcionarios.size() + 1, "funcionario");
								//CRIA UM ARQUIVO .TXT PARA O FUNCIONARIO CADASTRADO
								leitura.registrarUsuario(funcionarios.get(funcionarios.size() - 1), funcionarios.size(), "funcionarios/funcionario-");	
						}
						break;
					//--------------------------------------------------------------------
					//ABRIR CATALOGO
					case 3:
						op.mostrarCatalogo(catalogo);
						System.out.println("\tPara adicionar livros ao carrinho realize o login/cadastro.\n");
						break;
					//--------------------------------------------------------------------
					//SAIR
					case 4:
						sair = true;
				}
			} 
			
			//LOGADO
			else {		
				//CLIENTE LOGADO
				if (cliente) {
					switch (menu.textoClienteLogado()) {
						//REALIZAR COMPRAS
						case 1:
							op.realizarCompra(catalogo, clientes.get(indexContaLogada));
							break;
						//--------------------------------------------------------------------
						//OLHAR COMPRAS FEITAS
						case 2:
							clientes.get(indexContaLogada).mostrarCompras();
							break;
						//--------------------------------------------------------------------	
						//LOGOUT
						case 3:
							cliente = false;
							indexContaLogada = -1;
							break;
						//--------------------------------------------------------------------
						//SAIR
						case 4:
							sair = true;
					}
				} else if (funcionario) {
					//FUNCIONARIO LOGADO
					switch (menu.textoFuncionarioLogado()) {
						//ENCAMINHAR COMPRAS
						case 1:
							funcionarios.get(indexContaLogada).encaminharCompras(clientes);
							break;
						//--------------------------------------------------------------------
						//INFO
						case 2:
							switch(menu.textoInfo()) {
								//RELACAO LIVROS EM ESTOQUE
								case 1:
									funcionarios.get(indexContaLogada).relacaoEstoque(catalogo);
									break;
								//RELACAO DAS LISTAS DE COMPRAS
								case 2:
									funcionarios.get(indexContaLogada).listaCompras(clientes);
									break;
								//RELACAO CLIENTES CADASTRADOS
								case 3:
									funcionarios.get(indexContaLogada).clientesCadastrados(clientes);
									break;
								//RELACAO PAGAMENTOS EFETUADOS
								case 4:
									funcionarios.get(indexContaLogada).verificarPagamentos(clientes);
									break;
								//RELACAO DO PERFIL DE CADA CLIENTE
								case 5:
									funcionarios.get(indexContaLogada).categoriasPorCliente(clientes);
							}
							break;
						//--------------------------------------------------------------------
						//LOGOUT
						case 3:
							funcionario = false;
							indexContaLogada = -1;
							break;
						//--------------------------------------------------------------------
						//SAIR
						case 4:
							sair = true;
					}
				}
			}				
		}	
	}
}
