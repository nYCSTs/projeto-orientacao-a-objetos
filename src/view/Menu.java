package view;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import controller.Ferramenta;
import model.Livro;

/**
 * Ira controlar toda a interacao com o usuario, dispondo das opcoes de entrada e obtendo a opcao do usuario para determinado cenario
 * @author lucas
 * @version 1.0 (Nov 2020)
 */
public class Menu {
	private int entrada;
	private String str;
	private Ferramenta ferramenta = new Ferramenta();
	
	//----------------------------------------------------------------------------
	//GERAL
	/**
	 * Metodo de busca pelo qual o cliente ou visitante ira utilizar para buscar livros
	 * @return opcao escolhida pelo cliente, onde <p>(1) metodo de busca pelo nome do livro (ou uma parte do nome)<p>(2) mostrar livros pelas categorias
	 */
	public String textObterEmail() {
		System.out.print("Email: ");
		return ferramenta.scanNextLine();
	}
	
	public String textoObterSenha() {
		System.out.print("Senha: ");
		return ferramenta.scanNextLine();
	}
	
	
	public int textoMetodoDeBusca() {
		do {
			System.out.print("Metodo de busca: \n\t1 - Pesquisar por nome\n\t2 - Pesquisar por categoria\n>> ");
			this.entrada = ferramenta.scanInt();
		} while (this.entrada < 1 || this.entrada > 2);
			
		return this.entrada;
	}
	
	/**
	 * Ira obter do cliente ou visitando o nome do livro a ser pesquisado no catalogo da livraria
	 * @return nome do livro a ser procurado
	 */
	public String textoBuscaTitulo() {
		System.out.print("Nome do livro: ");
		this.str = ferramenta.scanNextLine();
		return this.str.toLowerCase();
		
	}
	
	/**
	 * Ira obter do cliente ou visitante a categoria desejada a ser pesquisada no catalogo da livraria
	 * @return a categoria escolhida
	 */
	public String textoEscolherGenero() {
		Map<Integer,String> categoria = new HashMap<Integer,String>();
		categoria.put(1, "INFANTIL");
		categoria.put(2, "TECNICO");
		categoria.put(3, "FICCAO");
		categoria.put(4, "NAO FICCAO");
		
		do {
			System.out.print("Categorias:\n\n\t1 - Infantil\n\t2 - Tecnico\n\t3 - Ficcao\n\t4 - Nao Ficcao\n>> ");
			this.entrada = ferramenta.scanInt();
		} while (this.entrada < 1 || this.entrada > 4);
		
		
		return categoria.get(this.entrada);
	}	
	
	public void mostrarInfo(Livro livro) {
		System.out.println("\n\tID......................: " + livro.getID());
		System.out.println("\tTitulo..................: " + livro.getBookName() + " (" + livro.getIdioma() + ")");
		System.out.println("\tPreco...................: " + livro.getPreco() + "R$");
		System.out.println("\tAutor...................: " + livro.getAuthorName());
		System.out.println("\tEditora.................: " + livro.getEditora());
		System.out.println("\tQuantidade de paginas...: " + livro.getQuantidadePaginas());
		System.out.println("\tQuantidade em estoque...: " + livro.getQuantidadeEstoque());
		System.out.println("\tISBN....................: " + livro.getISBN() + "\n");
	}
	
	//----------------------------------------------------------------------------
	//DESLOGADO
	/**
	 * Menu principal no caso de um usuario nao logado estar usando a livraria 
	 * @return a opcao escolhida pelo usuario, onde: <p>(1) realizar login em alguma conta ja criada<p>(2) realizar o cadastro na livraria<p>(3) verifica o catalogo<p>(4) sai da livraria, fechando o programa
	 */
	public int textoVisitante() {
		do { 
			System.out.print("\n\t****************************\n\t* 1 - Realizar login       *\n\t* 2 - Realizar cadastro    *\n\t* 3 - Verificar catalogo   *\n\t* 4 - Sair                 *\n\t****************************\n>> ");	
			this.entrada = ferramenta.scanInt();
		} while (this.entrada < 1 || this.entrada > 4);
		
		return this.entrada;
	}
	
	/**
	 * O cliente escolhe se fara login ou cadastro em uma conta de cliente ou de funcionario
	 * @param tipo a tela de login e cadastro e a mesma, mudando somente a parte do texto que especifica que <b>tipo de operacao</b> e feita
	 * @return a opcao escolhida pelo usuario, onde: <p>(1) login ou cadastro de um cliente<p>(2) login ou cadastro de um funcionario
	 */
	public int textoLoginCadastro(String tipo) {
		do {
			System.out.print("\t1 - " + tipo + " de cliente\n\t2 - " + tipo + " de funcionario\n>> ");
			this.entrada = ferramenta.scanInt();
		} while (this.entrada < 1 || this.entrada > 2);
		
		return this.entrada;
	}
	
	/**
	 * Ira obter todos os dados necessarios para se realizar um cadastro e os armazena no <a href="https://docs.oracle.com/'se/8/docs/api/java/util/ArrayList.html">ArrayList</a> dados
	 * @param dados um <a href="https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html">ArrayList</a> auxiliar que ira armazenar cada entrada do usuario
	 * @param cargo o cargo da conta a ser criada, cliente ou funcionario
	 */
	public void textoRealizarCadastro(ArrayList<String> dados, String cargo) {
		Ferramenta ferramenta = new Ferramenta();
		
		//NOME
		System.out.print("Nome: ");
	    dados.add(ferramenta.scanNextLine());
	    
	    //CPF
	    do {
	    	System.out.print("CPF (11 digitos, somente numeros): ");
		    this.str = ferramenta.scanNextLine();
	    } while (!ferramenta.verificaCPF(this.str));
	    dados.add(str);
	    
	    //IDENTIDADE
	    do {
	    	System.out.print("Identidade (somente numeros): ");
		    this.str = ferramenta.scanNextLine();
	    } while (!this.str.matches("[0-9]+") || (this.str.length() != 7 && this.str.length() != 9 && this.str.length() != 10));
	    dados.add(this.str);
	    
	    //NOME DO PAI 
	    System.out.print("Nome do pai: ");
	    dados.add(ferramenta.scanNextLine());

	    //NOME DA MAE
	    System.out.print("Nome da mae: ");
	    dados.add(ferramenta.scanNextLine());
	    
	    //ESCOLARIDADE
	    System.out.print("Escolaridade: ");
	    dados.add(ferramenta.scanNextLine());
	    
	    //SEXO
	    do {
	    	System.out.print("Sexo (M/F): ");
	    	this.str = ferramenta.scanNextLine().toUpperCase();
	    } while (!this.str.equals("M") && !this.str.equals("F"));
	    switch (this.str) {
		    case "M":
		    	dados.add("Masculino");
		    	break;
		    case "F":
		    	dados.add("Feminino");
	    }
	    
	    //ESTADO CIVIL
	    System.out.print("Estado Civil: ");
	    dados.add(ferramenta.scanNextLine());
	    
	    //NATURALIDADE
	    System.out.print("Naturalidade: ");
	    dados.add(ferramenta.scanNextLine());
	    
	    //ENDERECO
	    System.out.print("Endereco: ");
	    dados.add(ferramenta.scanNextLine());
	    
	    //TELEFONE
	    do {
	    	System.out.print("Telefone (DDD + Numero, somente numeros): ");
		    this.str = ferramenta.scanNextLine();
	    } while (this.str.length() < 10 || this.str.length() > 11); 
	    dados.add(this.str);
	    
	    //EMAIL
	    do {
	    	System.out.print("email: ");
	    	this.str = ferramenta.scanNextLine();
	    } while (!str.contains("@"));
    	dados.add(str);
    	
    	//SENHA
    	do {
    		System.out.print("senha (minimo 4 digitos): ");
    		this.str = ferramenta.scanNextLine();
    	} while (this.str.length() < 4);
	    dados.add(this.str);
	}
	
	//----------------------------------------------------------------------------
	//CLIENTE LOGADO
	/**
	 * <div id="menu-principal-cliente">Menu principal no caso de um cliente logado estar usando a livraria</div>
	 * @return a opcao escolhida pelo cliente, onde: <p id="compra-cliente">(1) ir para o menu de compra reservado ao cliente<p>(2) verificar pedidos anteriores<p>(3) deslogar da conta, sem fechar o programa<p>(4) sai da livraria, fechando o programa
	 */
	public int textoClienteLogado() {
		do {
			System.out.print("\n\t****************************\n\t* 1 - Comprar              *\n\t* 2 - Verificar pedidos    *\n\t* 3 - Logout               *\n\t* 4 - Sair                 *\n\t****************************\n>> ");
			this.entrada = ferramenta.scanInt();
		} while (this.entrada < 1 || this.entrada > 4);
		
		return this.entrada;
	}
	
	/**
	 * <div id="menu-cliente-compra">Menu referente a opcao de <a href="#compra-cliente">compra</a></div>
	 * @return a opcao escolhida pelo cliente, onde: <p>(1) Verifica o catalogo<p>(2) Ve como esta o estado do carrinho<p>(3) Ve como esta o carrinho, dispondo da opcao de remover itens<p id="cliente-prosseguir-pagamento">(4) Prosseguir para a parte de pagamento<p>(5) Voltar para o <a href="#menu-principal-cliente">menu principal</a>, abandonando qualquer item que estava no carrinho
	 */
	public int textoCompra() {
		do {
			System.out.print("\n\t1 - Verificar catalogo\n\t2 - Verificar carrinho\n\t3 - Editar carrinho\n\t4 - Realizar o pagamento\n\t5 - Voltar ao menu anterior\n>> ");
			this.entrada = ferramenta.scanInt();
		} while (this.entrada < 1 || this.entrada > 5);
		
		return this.entrada;
	}
	
	//COMPRA
	/**
	 * Apos verificar o catalogo (seja por nome ou categoria) o cliente e apresentado a opcao de adicionar, ou nao, algum dos livros encontrados ao carrinho
	 * @return a opcao escolhida pelo cliente, onde: <p>(1) ira adicionar algum dos itens encontrados ao carrinho<p>(2) ira voltar para o <a href="#menu-cliente-compra">menu anterior</a>
	 */
	public int textoAdicionar() {
		do {
			System.out.print("\n\t1 - Adicionar livro ao carrinho\n\t2 - Retornar ao menu anterior\n>> ");
			this.entrada = ferramenta.scanInt();
		} while (this.entrada < 1 || this.entrada > 2);
		
		return this.entrada;
	}
	
	/**
	 * O cliente escolhe qual dos livros encontrados ira para o seu carrinho
	 * @param tamanho referente a quantos livros existem no catalogo. O cliente nao ira poder selecionar um ID maior do que o maior ID encontrado no catalogo
	 * @return o ID escolhido pelo cliente
	 */
	public int textoObterID(int tamanho) {
		do {
			System.out.print("ID do livro: ");
			this.entrada = ferramenta.scanInt();
		} while (this.entrada < 0 || this.entrada > tamanho);
		
		return this.entrada;
	}
	
	/**
	 * O cliente escolhe quantos livros deseja adicionar
	 * @param quantidadeTotal referente a quantidade de livros disponivel em estoque
	 * @return a quantidade escolhida pelo cliente
	 */
	public int textoObterQuantidade(int quantidadeTotal) {
		do {
			System.out.print("Quantidade: ");
			this.entrada = ferramenta.scanInt();
		} while (quantidadeTotal < this.entrada);
		
		return this.entrada;
	}
	
	/**
	 * Caso o cliente deseje remover itens do seu carrinho 
	 * @param tamanhoSacola a quantidade de itens no carrinho do cliente
	 * @return o index referente ao livro removido, caso o cliente assim deseja, ou entao o numero 0 para auxiliar no controle
	 */
	public int textoRemoverItem(int tamanhoSacola){
		do {
			System.out.print("\nDeseja remover algum item? (S/SIM/N/NAO): ");
			this.str = ferramenta.scanNextLine().toLowerCase();
		} while (!this.str.equals("sim") && !this.str.equals("nao") && !this.str.equals("s") && !this.str.equals("n"));
		
		if (this.str.equals("sim") || this.str.equals("s")) {
			do {
				System.out.print("Qual item deseja remover? (selecione a posicao do item no carrinho, 0 para voltar): ");
				this.entrada = ferramenta.scanInt();
			} while (this.entrada < 0 || this.entrada > tamanhoSacola);
			
			if (this.entrada != 0) {
				return this.entrada;
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}
	
	/**
	 * Busca verificar se o ID escolhido pelo usuario realmente corresponde ao livro desejado
	 * @param livro informacoes do livro escolhido pelo cliente a partir do ID colocado
	 * @return a opcao escolhida pelo cliente, onde: <p>(1) Confirma a escolha e prossegue para a proxima etapa<p>(2) Cancela a adicao e volta ao <a href="menu-cliente-compra">menu de compra</a>
	 */
	public int textoConfirmarItem(Livro livro) {
		System.out.println("\nLivro selecionado: " + livro.getBookName());
		do {
			System.out.print("\t1 - Confirmar escolha\n\t2 - Cancelar escolha\n>> ");
			this.entrada = ferramenta.scanInt();
		} while (this.entrada < 1 || this.entrada > 2);
		
		return this.entrada;
		
	}
	
	/**
	 * Menu caso o cliente prossiga com o pagamento
	 * @return a opcao escolhida pelo cliente, onde: <p>(1) Prosseguir com o pagamento, confirmando os atuais itens presentes no carrinho<p>(2) caso o cliente deseje ele pode editar o carrinho<p>(3) Volta ao <a href="menu-cliente-compra">menu de compra</a>
	 */
	public int textoProsseguirPagamento() {
		do {
			System.out.print("\n\t1 - Continuar com pagamento\n\t2 - Editar carrinho\n\t3 - Voltar ao menu anterior\n>> ");
			this.entrada = ferramenta.scanInt();
		} while (this.entrada < 1 || this.entrada > 3);
		
		return this.entrada;
	}
	
	/**
	 * Obter o endereco completo do cliente para o pedido em questao
	 * @return o endereco do cliente
	 */
	public String textoObterEndereco() {
		System.out.print("Insira o endereco completo: ");
		this.str = ferramenta.scanNextLine();
		
		return this.str;
	}
	
	/**
	 * O cliente fornece o CEP (sequencia numerica com 8 digitos), e assim e calculado o frete. E mostrado o do frete, e entao o cliente tem a opcao de trocar seu CEP
	 * @param total soma dos itens do carrinho
	 * @return apos o calculo e a confirmacao do cliente, o frete e retornado
	 */
	public int textoObterFrete(double total) {
		int frete;

		do {
			do {
				System.out.print("CEP para entrega (somente numeros): ");
				this.str = ferramenta.scanNextLine();
			} while (this.str.length() != 8);
			
			if (this.str.charAt(0) == '6') {
				frete = 35;
			} else if (this.str.charAt(0) == '4') {
				frete = 25;
			} else {
				frete = 10;
			}
			
			System.out.println("\nLivros:...........: " + ferramenta.obterValorFormatado(total) + "R$");
			System.out.println("Frete:............: " + ferramenta.obterValorFormatado(frete) + "R$");
			System.out.println("Total:............: " + ferramenta.obterValorFormatado(total + frete) + "R$");
			
			do {
				System.out.print("\n\t1 - Confirmar CEP inserido\n\t2 - Mudar CEP\n>> ");
				this.entrada = ferramenta.scanInt();
			} while (this.entrada < 1 || this.entrada > 2);	
		} while (this.entrada != 1);
		
		return frete;
	}
	
	/**
	 * Forma de pagamento a ser escolhida
	 * @return a opcao escolhida pelo cliente, onde: <p>(1) Cartao de credito<p>(2) Boleto bancario
	 */
	public int textoFormaPagamento() {
		do {
			System.out.print("\nForma de pagamento: \n\t1 - Cartao (Visa ou MasterCard)\n\t2 - Boleto\n>> ");
			entrada = ferramenta.scanInt();
		} while (entrada < 1 || entrada > 2);
		
		return this.entrada;
	}
	
	/**
	 * No caso do cliente optar pelo pagamento com cartao de credito ele sera requerido para inserir os dados do cartao, assim como os 3 digitos de seguranca
	 */
	public void textoDadosCartao() {
		do {
			System.out.print("Insira os numeros do cartao de credito (16 digitos): ");
			this.str = ferramenta.scanNextLine();
		} while (this.str.length() != 16);
		
		do {
			System.out.print("Insira os digitos de seguranca: ");
			this.str = ferramenta.scanNextLine();
		} while (this.str.length() != 3);
	}
	
	//----------------------------------------------------------------------------
	//FUNCIONARIOS
	/**
	 * Menu principal no caso de um funcionario logado estar usando a livraria
	 * @return a opcao escolhida pelo funcionario, onde: <p>(1) gerencia os pedidos feitos<p>(2) estatisticas dos clientes<p>(3) realizar o logout da conta<p>(4) sair do programa 
	 */
	public int textoFuncionarioLogado() {
		do {
			System.out.print("\n\t****************************\n\t* 1 - Gerenciar pedidos    *\n\t* 2 - INFO                 *\n\t* 3 - Cadastrar novo livro *\n\t* 4 - Logout               *\n\t* 5 - Sair                 *\n\t****************************\n>> ");
			this.entrada = ferramenta.scanInt();
		} while (this.entrada < 1 || this.entrada > 5);
		
		return this.entrada;
	}
	
	/**
	 * Menu referente as estatisticas
	 * @return a opcao escolhida pelo funcionario, onde: <p>(1) situacao de estoque dos livros<p>(2) lista de compras de todos os clientes<p>(3) lista de todos os clientes cadastrados<p>(4) os status de pagamento dos pedidos<p>(5) perfil de compra de cada cliente
	 */
	public int textoInfo() {
		do {
			System.out.print("\n\t1 - Relacao de livros em estoque\n\t2 - Relacao de lista de compras\n\t3 - Relacao de clientes cadastrados\n\t4 - Pagamentos efetuados\n\t5 - Perfil de cada cliente\n>> ");
			this.entrada = ferramenta.scanInt();
		} while (entrada < 1 || entrada > 5);
		
		return this.entrada;
	}

	/**
	 * Obtem do funcionario o codigo do pedido que foi encaminhado 
	 * @return o codigo do pedido que foi encaminhado
	 */
	public String textoEncaminhamentoDePedido() {
		do {
			System.out.print("Qual pedido deseja encaminhar (somente o codigo de 6 digitos. 0 para voltar): ");
			this.str = ferramenta.scanNextLine();			
		} while (str.length() != 6 && !str.equals("0"));
		
		return this.str.toUpperCase();
	}
	
	public Livro textoCadastrarLivro(int livroID) {
		Livro livro = new Livro();
		
		
		do {
			System.out.print("Nome do livro (max. 80 letras): ");
			this.str = ferramenta.scanNextLine();
		} while (this.str.length() > 80);
		livro.setBookName(ferramenta.scanNextLine());
		
		System.out.print("Nome do autor: ");
		livro.setAuthorName(ferramenta.scanNextLine());
		
		do {
			System.out.print("Categoria do livro: ");
			this.str = ferramenta.scanNextLine().toUpperCase();
		} while (!this.str.equals("INFANTIL") && !this.str.equals("TECNICO") && !this.str.equals("FICCAO") && !this.str.equals("NAO FICCAO"));
		livro.setCategoria(this.str);
		
		System.out.print("Editora: ");
		livro.setEditora(ferramenta.scanNextLine());
		
		livro.setID(livroID);
		
		System.out.print("ISBN: ");
		livro.setISBN(ferramenta.scanNextLine());
		
		System.out.print("Preco: ");
		livro.setPreco(ferramenta.scanDouble());
		
		System.out.print("Quantidade de livros em estoque: ");
		livro.setQuantidadeEstoque(ferramenta.scanInt());
		
		System.out.print("Idioma: ");
		livro.setIdioma(ferramenta.scanNextLine());
		
		System.out.print("Quantidade de paginas: ");
		livro.setQuantidadePaginas(ferramenta.scanInt());
		
		return livro;
	}
}
