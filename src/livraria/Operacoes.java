package livraria;

import java.util.ArrayList;

public class Operacoes {
	public int realizarLogin(ArrayList<Usuario> usuario) {
		int loggedAccount;
		String email, senha, nome;
		Ferramentas ferramenta = new Ferramentas();
		
		System.out.print("email: ");
		email = ferramenta.scan();
		System.out.print("senha: ");
		senha = ferramenta.scan();
		
		for (loggedAccount = 0; loggedAccount < usuario.size(); loggedAccount++) {			
			if (usuario.get(loggedAccount).getEmail().equals(email)) {
				if (usuario.get(loggedAccount).getSenha().equals(senha)) {
					nome = usuario.get(loggedAccount).getNome();
					System.out.println("\nLogin realizado com sucesso! Bem vindo " + nome);
					return loggedAccount;
				} else {
					System.out.println("\nemail e/ou senha incorretos.");
					return -1;
				}
			}
		}
		System.out.println("\nemail e/ou senha incorretos.");
		return -1;
	}
	
	public Usuario registrarConta(int userID, String cargo) {
		String str;
		ArrayList<String> dados = new ArrayList<String>();
		Ferramentas ferramenta = new Ferramentas();
		
		System.out.print("Nome: ");
	    dados.add(ferramenta.scanNextLine());
	    
	    do {
	    	System.out.print("CPF (11 digitos, somente numeros): ");
		    str = ferramenta.scan();
	    } while (!str.matches("-?\\d+(\\.\\d+)?") || str.length() != 11);
	    dados.add(str);
	    
	    System.out.print("Identidade (somente numeros): ");
	    dados.add(ferramenta.scan());
	    
	    System.out.print("Nome do pai: ");
	    dados.add(ferramenta.scanNextLine());

	    System.out.print("Nome da mae: ");
	    dados.add(ferramenta.scanNextLine());
	    
	    System.out.print("Escolaridade: ");
	    dados.add(ferramenta.scanNextLine());
	    
	    do {
	    	System.out.print("Sexo (M/F): ");
	    	str = ferramenta.scan().toUpperCase();
	    } while (!str.equals("M") && !str.equals("F"));
	    dados.add(str);
	    
	    System.out.print("Estado Civil: ");
	    dados.add(ferramenta.scan());
	    
	    System.out.print("Naturalidade: ");
	    dados.add(ferramenta.scan());
	    
	    System.out.print("Endereco: ");
	    dados.add(ferramenta.scanNextLine());
	    
	    dados.add(cargo);
	    
	    System.out.print("Telefone: ");
	    dados.add(ferramenta.scan());
	    
    	System.out.print("email: ");
    	dados.add(ferramenta.scan());
	    	
	    System.out.print("senha: ");
	    dados.add(ferramenta.scanNextLine());
		
	    Usuario usuario = new Usuario(userID, dados.get(0), dados.get(1), dados.get(2), dados.get(3), dados.get(4), dados.get(5), dados.get(6), dados.get(7), dados.get(8), dados.get(9), dados.get(10), dados.get(11), dados.get(12), dados.get(13));
	    
	    return usuario;
	}

	public String mostrarCatalogo(ArrayList<Livro> livros) {
		int input;
		String categoria;
		Ferramentas ferramenta = new Ferramentas();
		
		do {
			System.out.println("\t1 - Infantil\n\t2 - Tecnico\n\t3 - Ficcao\n\t4 - Nao Ficcao");
			input = ferramenta.scanInt();
		} while (input < 1 || input > 4);
		
		switch (input) {
		case 1:
			categoria = "INFANTIL";
			break;
		case 2:
			categoria = "TECNICO";
			break;
		case 3:
			categoria = "FICCAO";
			break;
		default:
			categoria = "NAO FICCAO";
		}
		
		for (int i = 0; i < livros.size(); i++) {
			if (livros.get(i).getCategoria().trim().equals(categoria)) {
				System.out.println("\n\tID:.....................: " + livros.get(i).getID());
				System.out.println("\tNome:...................: " + livros.get(i).getBookName());
				System.out.println("\tPreco:..................: " + livros.get(i).getPreco() + "R$");
				System.out.println("\tAutor:..................: " + livros.get(i).getAuthorName());
				System.out.println("\tEditora:................: " + livros.get(i).getEditora());
				System.out.println("\tQuantidade em estoque:..: " + livros.get(i).getQuantidadeEstoque());
				
				
				System.out.println("\tISBN:...................: " + livros.get(i).getISBN() + "\n");
			}
		}
		
		return categoria;
	}

	public void realizarCompra(ArrayList<Livro> catalogo, Usuario usuario) {
		int input, idSelecionado, quantidade;
		String categoria;
		Carrinho carrinho = new Carrinho();
		Ferramentas ferramenta = new Ferramentas();
		
		while (true) {
			do {
				System.out.println("\n\t1 - Verificar catalogo\n\t2 - Verificar carrinho\n\t3 - Editar carrinho\n\t4 - Realizar o pagamento\n\t5 - Voltar ao menu anterior");
				input = ferramenta.scanInt();
			} while (input < 1 && input > 5);
			
			switch (input) {
			//verificar catalogo
			case 1:
				categoria = mostrarCatalogo(catalogo);
				do {
					System.out.println("\n\t1 - Adicionar livro ao carrinho\n\t2 - Retornar ao menu anterior");
					input = ferramenta.scanInt();
				} while (input < 1 && input > 2);
				
				switch (input) {
				case 1:
					do {
						System.out.println("ID do livro: ");
						idSelecionado = ferramenta.scanInt();
					} while (idSelecionado < 0 && catalogo.size() > idSelecionado);
					
					for (int i = 0; i < catalogo.size(); i++) {
						if (catalogo.get(i).getID() == idSelecionado) {
							//verifica se ha estoque
							if (catalogo.get(i).getQuantidadeEstoque() == 0) {
								System.out.println("Este livro esta indisponivel no momento. :(");
								break;
							} else if (!catalogo.get(i).getCategoria().trim().equals(categoria)) {
								System.out.println("O ID escolhido nao foi encontrado para a categoria " + categoria.toLowerCase() + ".");
								break;
							} else {
								do {
									System.out.println("Quantidade: ");
									quantidade = ferramenta.scanInt();
								} while (catalogo.get(i).getQuantidadeEstoque() < quantidade);
								
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
			//realizar pagamento
			case 4:
				carrinho.pagamento(usuario);
				return;
			//voltar
			case 5:
				return;
			}
		}
	}

}
