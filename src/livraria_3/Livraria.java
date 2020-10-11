package livraria_3;
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
		
		int nextID, loggedAccount = -1;
		boolean exit = false, cliente = false, funcionario = false;
		
		//Leitura clientes previamente cadastrados
		funcionarios = leitura.leituraUsuarios("funcionarios/funcionario-");
		clientes = leitura.leituraUsuarios("clientes/cliente-");
		nextID = clientes.size() + 1;
		
		//Leitura livros previamente cadastrados
		catalogo = leitura.leituraLivros();

		while (!exit) {
			//NAO LOGADO
			if (!cliente && !funcionario) {				
				switch (menu.loggedOffText()) {
					//login
					case 1:
						//retorna a posicao da conta logada no ArrayList
						loggedAccount = op.realizarLogin(clientes);
						cliente = true;
						break;
					case 2:
						loggedAccount = op.realizarLogin(funcionarios);
						funcionario = true;
						break;
						
					//cadastro
					case 3:
						//adiciona usuario na lista de clientes
						clientes.add(op.registrarConta(nextID, clientes));
						//cria o usuario no .txt
						leitura.registrarUsuario(clientes.get(nextID - 1), nextID);
						nextID++;
						break;
						
					//verificar catalogo
					case 4:
						op.mostrarCatalogo(catalogo);
						break;
					
					//sair do programa
					case 5:
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
						case 1:
							gerenciar.relacaoEstoque(catalogo);
							break;
						case 2:
							gerenciar.listaCompras(clientes);
							break;
						case 3:
							gerenciar.clientesCadastrados(clientes);
							break;
						case 4:
							//
							break;
						case 5:
							//
						}
						break;
						
					//voltar
					case 3:
						break;
						
					//logout
					case 4:
						funcionario = false;
						loggedAccount = -1;
					}
				}
				
			}				
		}	
	}
}
