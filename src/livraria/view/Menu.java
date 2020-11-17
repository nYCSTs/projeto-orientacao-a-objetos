package livraria.view;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import livraria.controller.Ferramenta;

public class Menu {
	private int entrada;
	private String str;
	private Ferramenta ferramenta = new Ferramenta();
	
	//----------------------------------------------------------------------------
	//GERAL
	public String textoEscolherGenero() {
		Map<Integer,String> categoria = new HashMap<Integer,String>();
		categoria.put(1, "INFANTIL");
		categoria.put(2, "TECNICO");
		categoria.put(3, "FICCAO");
		categoria.put(4, "NAO FICCAO");
		
		do {
			System.out.print("\t1 - Infantil\n\t2 - Tecnico\n\t3 - Ficcao\n\t4 - Nao Ficcao\n>> ");
			this.entrada = ferramenta.scanInt();
		} while (this.entrada < 1 || this.entrada > 4);
		
		
		return categoria.get(this.entrada);
	}	
	
	//----------------------------------------------------------------------------
	//DESLOGADO
	public int textoDeslogado() {
		do { 
			System.out.print("\n\t****************************\n\t* 1 - Realizar login       *\n\t* 2 - Realizar cadastro    *\n\t* 3 - Verificar catalogo   *\n\t* 4 - Sair                 *\n\t****************************\n>> ");	
			this.entrada = ferramenta.scanInt();
		} while (this.entrada < 1 || this.entrada > 4);
		
		return this.entrada;
	}
	
	public int textoLoginCadastro(String tipo) {
		do {
			System.out.print("\t1 - " + tipo + " de cliente\n\t2 - " + tipo + " de funcionario\n>> ");
			this.entrada = ferramenta.scanInt();
		} while (this.entrada < 1 || this.entrada > 2);
		
		return this.entrada;
	}
	
	public void textoRealizarCadastro(ArrayList<String> dados, String cargo) {
		Ferramenta ferramenta = new Ferramenta();
		
		//NOME
		System.out.print("Nome: ");
	    dados.add(ferramenta.scanNextLine());
	    
	    //CPF
	    do {
	    	System.out.print("CPF (11 digitos, somente numeros): ");
		    this.str = ferramenta.scan();
	    } while (!str.matches("-?\\d+(\\.\\d+)?") || str.length() != 11);
	    dados.add(str);
	    
	    //IDENTIDADE
	    do {
	    	System.out.print("Identidade (somente numeros): ");
		    this.str = ferramenta.scan();
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
	    	str = ferramenta.scan().toUpperCase();
	    } while (!this.str.equals("M") && !this.str.equals("F"));
	    dados.add(str);
	    
	    //ESTADO CIVIL
	    System.out.print("Estado Civil: ");
	    dados.add(ferramenta.scan());
	    
	    //NATURALIDADE
	    System.out.print("Naturalidade: ");
	    dados.add(ferramenta.scan());
	    
	    //ENDERECO
	    System.out.print("Endereco: ");
	    dados.add(ferramenta.scanNextLine());
	    
	    //TELEFONE
	    do {
	    	System.out.print("Telefone (DDD + Numero, somente numeros): ");
		    this.str = ferramenta.scan();
	    } while (this.str.length() < 10 || this.str.length() > 11); 
	    dados.add(this.str);
	    
	    //EMAIL
	    do {
	    	System.out.print("email: ");
	    	this.str = ferramenta.scan();
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
	public int textoClienteLogado() {
		do {
			System.out.print("\t****************************\n\t* 1 - Comprar              *\n\t* 2 - Verificar pedidos    *\n\t* 3 - Logout               *\n\t* 4 - Sair                 *\n\t****************************\n>> ");
			this.entrada = ferramenta.scanInt();
		} while (this.entrada < 1 || this.entrada > 4);
		
		return this.entrada;
	}
	
	public int textoCompra() {
		do {
			System.out.print("\n\t1 - Verificar catalogo\n\t2 - Verificar carrinho\n\t3 - Editar carrinho\n\t4 - Realizar o pagamento\n\t5 - Voltar ao menu anterior\n>> ");
			this.entrada = ferramenta.scanInt();
		} while (this.entrada < 1 || this.entrada > 5);
		
		return this.entrada;
	}
	
	public int textoAdicionar() {
		do {
			System.out.print("\n\t1 - Adicionar livro ao carrinho\n\t2 - Retornar ao menu anterior\n>> ");
			this.entrada = ferramenta.scanInt();
		} while (this.entrada < 1 || this.entrada > 2);
		
		return this.entrada;
	}
	
	public int textoObterID(int tamanho) {
		do {
			System.out.println("ID do livro: ");
			this.entrada = ferramenta.scanInt();
		} while (this.entrada < 0 || this.entrada > tamanho);
		
		return this.entrada;
	}
	
	public int textoObterQuantidade(int quantidadeTotal) {
		do {
			System.out.println("Quantidade: ");
			this.entrada = ferramenta.scanInt();
		} while (quantidadeTotal < this.entrada);
		
		return this.entrada;
	}
	
	public int textoRemoverItem(int tamanhoSacola){
		do {
			System.out.println("Qual item deseja remover?");
			this.entrada = ferramenta.scanInt();
		} while (this.entrada < 1 || this.entrada > tamanhoSacola);
		
		return this.entrada;
	}
	
	public int textoProsseguirPagamento() {
		do {
			System.out.print("\n\t1 - Continuar com pagamento\n\t2 - Abandonar compra\n>> ");
			this.entrada = ferramenta.scanInt();
		} while (this.entrada < 1 || this.entrada > 2);
		
		return this.entrada;
	}
	
	public String textoObterEndereco() {
		System.out.println("Insira o endereco completo: ");
		this.str = ferramenta.scanNextLine();
		
		return this.str;
	}
	
	public String textoObterCep() {
		do {
			System.out.println("CEP para entrega: ");
			this.str = ferramenta.scan();
		} while (this.str.length() != 8);
		
		return this.str;
	}
	
	public int textoAlterarCep() {
		do {
			System.out.print("\n\t1 - Finalizar pedido\n\t2 - Mudar CEP\n>> ");
			this.entrada = ferramenta.scanInt();
		} while (this.entrada < 1 || this.entrada > 2);
		
		return this.entrada;
	}
	
	public int textoFormaPagamento() {
		do {
			System.out.print("\n\tForma de pagamento: \n1 - Cartao\n2 - Boleto\n>> ");
			entrada = ferramenta.scanInt();
		} while (entrada < 1 || entrada > 2);
		
		return this.entrada;
	}
	
	public void textoDadosCartao() {
		System.out.println("Insira os numeros do cartao de credito: ");
		ferramenta.scan();
		System.out.println("Insira os digitos de seguranca: ");
		ferramenta.scan();
	}
	
	//----------------------------------------------------------------------------
	//FUNCIONARIOS
	public int textoFuncionarioLogado() {
		do {
			System.out.print("\t****************************\n\t* 1 - Gerenciar pedidos    *\n\t* 2 - INFO                 *\n\t* 3 - Logout               *\n\t* 4 - Sair                 *\n\t****************************\n>> ");
			this.entrada = ferramenta.scanInt();
		} while (this.entrada < 1 || this.entrada > 4);
		
		return this.entrada;
	}
	
	public int textoInfo() {
		do {
			System.out.print("\n\t1 - Relacao de livros em estoque\n\t2 - Relacao de lista de compras\n\t3 - Relacao de clientes cadastrados\n\t4 - Pagamentos efetuados\n\t5 - Perfil de cada cliente\n>> ");
			this.entrada = ferramenta.scanInt();
		} while (entrada < 1 || entrada > 5);
		
		return this.entrada;
	}

	public String textoEncaminhamentoDePedido() {
		do {
			System.out.print("Qual pedido deseja encaminhar (somente o codigo de 6 digitos. 0 para voltar): ");
			this.str = ferramenta.scan().toUpperCase();			
		} while (str.length() != 6 && !str.equals("0"));
		
		return this.str;
	}
}
