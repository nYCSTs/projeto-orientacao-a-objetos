package livraria;
import java.util.ArrayList;

public class Livraria {		
	public static void main(String[] args) {
		IO leitura = new IO();
		Menu menu = new Menu();
		Operacoes op = new Operacoes();
		Gerenciar gerenciar = new Gerenciar();
	
		//todos os livros cadastrados
		ArrayList<Livro> catalogo = new ArrayList<Livro>();
		//todos os clientes cadastrados
		ArrayList<Usuario> clientes = new ArrayList<Usuario>();
		ArrayList<Usuario> funcionarios = new ArrayList<Usuario>();
		
		int userID, funcID, loggedAccount = -1;
		boolean exit = false, cliente = false, funcionario = false;
		
		//Leitura clientes previamente cadastrados 
		funcionarios = leitura.leituraUsuarios("funcionarios/funcionario-");
		clientes = leitura.leituraUsuarios("clientes/cliente-");
		userID = clientes.size() + 1;
		funcID = funcionarios.size() + 1;
		
		//Leitura livros previamente cadastrados
		catalogo = leitura.leituraLivros();

		while (!exit) {
			//NAO LOGADO
			if (loggedAccount == -1) {				
				switch (menu.loggedOffText()) {
					//realizar login
					case 1:
						switch (menu.realizarLoginCadastro("login")) {
						//login de cliente
						case 1:
							//retorna a posicao da conta logada no ArrayList
							loggedAccount = op.realizarLogin(clientes);
							if (loggedAccount != -1) {
								cliente = true;
							}
							break;
						//login de funcionario
						case 2:
							loggedAccount = op.realizarLogin(funcionarios);
							if (loggedAccount != -1) {
								funcionario = true;
							}
							break;	
						}
						break;
						
					//cadastro
					case 2:
						switch (menu.realizarLoginCadastro("cadastro")) {
						//cadastro de cliente
						case 1:
							//adiciona usuario na lista de clientes
							clientes.add(op.registrarConta(userID, "Cliente"));
							//adiciona o cliente no arquivo .txt
							leitura.registrarUsuario(clientes.get(userID - 1), userID, "clientes/cliente-");
							userID++;
							break;	
						//cadastro de funcionario
						case 2:
							//adiciona usuario na lista de clientes
							funcionarios.add(op.registrarConta(funcID, "Funcionario"));
							//adiciona o funcionario no arquivo .txt
							leitura.registrarUsuario(funcionarios.get(funcID - 1), funcID, "funcionarios/funcionario-");
							funcID++;
							break;	
						}
						break;
						
					//verificar catalogo
					case 3:
						op.mostrarCatalogo(catalogo);
						break;
					
					//sair do programa
					case 4:
						exit = true;
				}
			} 
			
			//LOGADO
			else {
				//cliente
				if (cliente) {
					switch (menu.loggedInClienteText()) {
					//compra
					case 1:
						op.realizarCompra(catalogo, clientes.get(loggedAccount));
						break;
					//olhar compras
					case 2:
						clientes.get(loggedAccount).mostrarCompras();
						break;
						
					//logoff
					case 3:
						cliente = false;
						loggedAccount = -1;
						break;
						
					//sair
					case 4:
						exit = true;
					}
				} else if (funcionario) {
					//funcionario
					switch (menu.loggedInFuncionarioText()) {
					//gerenciar pedidos
					case 1:
						gerenciar.gerenciarCompras(clientes);
						break;
						
					//info 
					case 2:
						switch(menu.infoText()) {
						//relacao livros em estoque
						case 1:
							gerenciar.relacaoEstoque(catalogo);
							break;
						//relacao das listas de compras 
						case 2:
							gerenciar.listaCompras(clientes);
							break;
						//relacao de clientes cadastrados
						case 3:
							gerenciar.clientesCadastrados(clientes);
							break;
						//relacao dos pagamentos efetuados
						case 4:
							gerenciar.verificarPagamentos(clientes);
							break;
						//lista dos clientes por perfil
						case 5:
							//nao implementado ainda
						}
						break;
						
					//logout
					case 3:
						funcionario = false;
						loggedAccount = -1;
					}
				}
			}				
		}	
	}
}
