package controller;
import java.util.ArrayList;
import model.Cliente;
import model.Funcionario;
import model.IO;
import model.Livro;
import view.Menu;

// ----------------------------------------------------------- MAIN ----------------------------------------------------------- //

/**
 * Controla toda a execucao do programa (MAIN)
 * @author lucas
 * @version 1.0 (Nov 2020)
 */
public class Livraria {		
	public static void main(String[] args) {	
		Menu menu = new Menu();
		IO leitura = new IO();

		ArrayList<Livro> catalogo = new ArrayList<Livro>();
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
		
		//Leitura dos clientes e funcionarios previamente cadastrados (eclipse-workspace\livraria\clientes e eclipse-workspace\livraria\funcionarios)
		leitura.leituraUsuarios(clientes, funcionarios);
		//Leitura dos livros (eclipse-workspace\livraria\livros)
		leitura.leituraLivros(catalogo);

		Operacoes op = new Operacoes(clientes, funcionarios);
		
		//INDEX DA CONTA LOGADA
		int indexContaLogada = -1;
		
		//VARIAVEIS DE CONTROLE
		boolean cliente = false, funcionario = false; 
		boolean fecharPrograma = false;
		
		while (!fecharPrograma) {
			//VISITANTE
			if (!cliente && !funcionario) {				
				switch (menu.textoVisitante()) {
					//REALIZAR LOGIN
					case 1:
						switch (menu.textoLoginCadastro("login")) {
							//LOGIN CLIENTE
							case 1:
								op.realizarLogin("cliente");
								cliente = op.getEstadoLogin();
								break;
							//LOGIN FUNCIONARIO
							case 2:
								op.realizarLogin("funcionario");
								funcionario = op.getEstadoLogin();
						}
						indexContaLogada = op.getIndexContaLogada();	
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
						op.mostrarCatalogo(catalogo, false);
						break;
					//--------------------------------------------------------------------
					//SAIR
					case 4:
						fecharPrograma = true;
				}
			} 
			
			//USUARIO
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
							fecharPrograma = true;
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
						//CADASTRAR NOVO LIVRO
						case 3:
							catalogo.add(leitura.registrarLivro(menu.textoCadastrarLivro(catalogo.size() + 1), catalogo.size() + 1, "livros/livro-"));
							break;
						//--------------------------------------------------------------------
						//LOGOUT
						case 4:
							funcionario = false;
							indexContaLogada = -1;
							break;
						//--------------------------------------------------------------------
						//SAIR
						case 5:
							fecharPrograma = true;
					}
				}
			}				
		}	
	}
}
