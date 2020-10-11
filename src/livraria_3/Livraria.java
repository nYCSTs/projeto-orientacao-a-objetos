package livraria_3;
import java.util.ArrayList;

/*
 * Pagamentos efetuados [INFO]
 * Perfil de cada cliente [INFO]
 * 
 * 
 * 
 * 
 * O que e pra mostrar na relacao de clientes cadastrados
 * Quem pode ver INFO
 * Qual e a diferenca entre cliente e funcionario. O que cada um pode fazer de diferente
 * 
 * 
 */

public class Livraria {		
	public static void main(String[] args) {
		IO leitura = new IO();
		Menu menu = new Menu();
		Operacoes op = new Operacoes();
		Info info = new Info();
	
		//todos os livros cadastrados
		ArrayList<Livro> catalogo = new ArrayList<Livro>();
		//todos os usuarios cadastrados
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

		int nextID, loggedAccount = 0;
		boolean exit = false;
		
		//Leitura usuarios previamente cadastrados
		usuarios = leitura.leituraUsuarios();
		nextID = usuarios.size() + 1;
		
		//Leitura livros previamente cadastrados
		catalogo = leitura.leituraLivros();

		while (!exit) {
			//NAO LOGADO
			if (loggedAccount == 0) {				
				switch (menu.loggedOffText()) {
					//login
					case 1:
						//retorna a posicao da conta logada no ArrayList
						loggedAccount = op.realizarLogin(usuarios);
						break;
					
					//cadastro
					case 2:
						//adiciona usuario na lista de usuarios
						usuarios.add(op.registrarConta(nextID));
						//cria o usuario no .txt
						leitura.registrarUsuario(usuarios.get(nextID - 1), nextID);
						nextID++;
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
				switch (menu.loggedInText()) {
				//compra
				case 1:
					op.realizarCompra(catalogo, usuarios.get(loggedAccount));
					break;
				
				//info
				case 2:
					//System.out.println("\n\t1 - Relacao de livros em estoque\n\t2 - Relacao de lista de compras\n\t3 - Relacao de clientes cadastrados\n\t4 - Pagamentos efetuados\n\t5 - Perfil de cada cliente");
					switch(menu.infoText()) {
					case 1:
						info.relacaoEstoque(catalogo);
						break;
					case 2:
						info.listaCompras(usuarios);
						break;
					case 3:
						info.clientesCadastrados(usuarios);
						break;
					case 4:
						//
						break;
					case 5:
						//
					}
					break;
					
				//logoff
				case 3:
					loggedAccount = 0;
					break;
					
				//sair
				case 4:
					exit = true;
				}
			}				
		}	
	}
}
