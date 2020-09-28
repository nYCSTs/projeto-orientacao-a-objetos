package livraria_3;
import java.util.ArrayList;
import java.util.Scanner;

public class Livraria {
	public static String meuScanner(int tipo) {
		Scanner scan = new Scanner(System.in);
		if (tipo == 1) {
			return scan.next();
		} else {
			return scan.nextLine();
		}
	}	
	
	public static Usuario criarConta(int userID) {
		Usuario usuario = new Usuario();
		String nome, cpf, identidade, filiacao, escolaridade, sexo, estadoCivil, naturalidade, endereco, cargo, telefone, email, senha;
		
		System.out.print("Nome: ");
	    nome = meuScanner(2);
	    
	    do {
	    	System.out.print("CPF (somente numeros): ");
		    cpf = meuScanner(1);
	    } while (!cpf.matches("-?\\d+(\\.\\d+)?") || cpf.length() != 11);
	    
	    System.out.print("Identidade (somente numeros): ");
	    identidade = meuScanner(1);
	    
	    System.out.print("Filiação: ");
	    filiacao = meuScanner(2);
	    
	    System.out.print("Escolaridade: ");
	    escolaridade = meuScanner(2);
	    
	    System.out.print("Sexo (M/F): ");
	    sexo = meuScanner(1);
	    
	    System.out.print("Estado Civil: ");
	    estadoCivil = meuScanner(1);
	    
	    System.out.print("Naturalidade: ");
	    naturalidade = meuScanner(1);
	    
	    System.out.print("Endereço: ");
	    endereco = meuScanner(2);
	    
	    System.out.print("Cargo: ");
	    cargo = meuScanner(2);
	    
	    System.out.print("Telefone: ");
	    telefone = meuScanner(1);
	    
	    System.out.print("email: ");
	    email = meuScanner(1);
	    
	    System.out.print("senha: ");
	    senha = meuScanner(2);
		
		usuario.setValues(userID, nome, cpf, identidade, filiacao, escolaridade, sexo, estadoCivil, naturalidade, endereco, cargo, telefone, email, senha);
	    
		return usuario;
	}
	
	public static void verificarCatalogo(ArrayList<Livro> livros) {
		for (int i = 0; i < livros.size(); i++) {
			System.out.println("ID: " + livros.get(i).getID());
			System.out.println("Quantidade em estoque: " + livros.get(i).getQuantidadeEstoque());
			System.out.println("Nome: " + livros.get(i).getBookName());
			System.out.println("Autor: " + livros.get(i).getAuthorName());
			System.out.println("ISBN: " + livros.get(i).getISBN());
			System.out.println("---------------");
		}
	}
	
	public static void main(String[] args) {
		IO leitura = new IO();
		Livro livro = new Livro();

		ArrayList<Livro> livrosInfantil = new ArrayList<Livro>();
		ArrayList<Livro> livrosTecnico = new ArrayList<Livro>();
		ArrayList<Livro> livrosFiccao = new ArrayList<Livro>();
		ArrayList<Livro> livrosNFiccao = new ArrayList<Livro>();
		
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

		int input, userID = 1, bookID = 1, loggedAccount;
		boolean login_state = false, exit = false;
		
		//Leitura usuarios
		usuarios = leitura.leituraUsuarios();
		userID = leitura.getUserID();
		
		//Leitura livros
		for (; !exit; bookID++) {
			livro = leitura.leituraLivros("livro-".concat(Integer.toString(bookID)));
			switch (livro.getCategoria().trim()) {
				case "INFANTIL":
					livrosInfantil.add(livro);
					break;
				case "TECNICO":
					livrosTecnico.add(livro);
					break;
				case "FICCAO":
					livrosFiccao.add(livro);
					break;
				case "NAO FICCAO":
					livrosNFiccao.add(livro);
					break;
				default:
					System.out.println(livro.getCategoria());
					exit = true;
			}
		}
		
		exit = false;
		while (!exit) {
			//NAO LOGADO
			if (login_state == false) {
				System.out.println("\t1 - Realizar login\n\t2 - Realizar cadastro\n\t3 - Verificar catalogo\n\t4 - Sair\n>> ");
				input = Integer.parseInt(meuScanner(1));
				
				switch(input) {
				//login
				case 1:
					String email, senha, nome = "";
					System.out.print("email: ");
					email = meuScanner(1);
					System.out.print("senha: ");
					senha = meuScanner(2);
					
					for (loggedAccount = 0; loggedAccount < usuarios.size(); loggedAccount++) {			
						if (usuarios.get(loggedAccount).getEmail().compareTo(email) == 0) {
							if (usuarios.get(loggedAccount).getSenha().compareTo(senha) == 0) {
								nome = usuarios.get(loggedAccount).getNome();
								login_state = true;	
							} 
						}
					}
					System.out.println(login_state ? "Login realizado com sucesso! Bem vindo " + nome : "email e/ou senha incorretos.");
					break;
				//cadastro
				case 2:
					usuarios.add(criarConta(userID));
					leitura.registrarUsuario(usuarios.get(userID - 1), userID);
					userID++;
					break;
					
				//verificar catalogo
				case 3:
					System.out.println("\t1 - Infantil\n\t2 - Tecnico\n\t3 - Ficcao\n\t4 - Não Ficcao");
					input = Integer.parseInt(meuScanner(1));
					switch (input) {
					case 1:
						verificarCatalogo(livrosInfantil);
						break;
					case 2:
						verificarCatalogo(livrosTecnico);
						break;
					case 3:
						verificarCatalogo(livrosFiccao);
						break;
					case 4:
						verificarCatalogo(livrosNFiccao);
						break;
					}
					break;
				case 4:
					exit = true;
					break;
				default:
					System.out.println("Opcao invalida, tente novamente.");

				}
			} 
			//LOGADO
			else {
				System.out.println("\t1 - Verificar catalogo\n\t2 - Sair");
			}
		}
		
		/*
		while (true) {
			//não logado
			if (login_state == false) {
				System.out.print("1) Verificar catalogo\n2) Login\n3) Criar conta\n4) Sair\n >> ");
				input = Integer.parseInt(meuScanner(1));
				
				//catalogo
				if (input == 1) {
					//verificarCatalogo(livros);
					
				//realizar login
				} else if (input == 2) {
					System.out.print("email: ");
					email = meuScanner(1);
					System.out.print("senha: ");
					senha = meuScanner(2);
					
					for (int i = 0; i < usuarios.size(); i++) {			
						if (usuarios.get(i).getEmail().compareTo(email) == 0) {
							if (usuarios.get(i).getSenha().compareTo(senha) == 0) {
								login_state = true;
							} else {
								break;
							}
						}
					}
					
					if (login_state == false) {
						System.out.println("email/senha incorretos.");
					} else {
						System.out.println("login realizado com sucesso!");
					}
					
				//realizar cadastro
				} else if (input == 3) {
					//usuarios = leitura.insereUsuario(leitura.fileUsers, usuarios, lastUserID);
				//sair
				} else if (input == 4) {
					break;
				}
				
			//logado
			} else {				
				System.out.print("1) Comprar\n2) Verificar catalogo\n3) Adicionar Livro >> ");
				input = Integer.parseInt(meuScanner(1));
				switch(input) {
				case 1:
					break;
				case 2: 
					verificarCatalogo(livros);
				case 3:
					leitura.insereLivro(leitura.fileBooks, livros, lastBookID);
				}
				
				
			}
		}
		*/
			
	}
}