package livraria;

public class Menu {
	private int input;
	Ferramentas ferramenta = new Ferramentas();
	
	public int loggedOffText() {
		do {
			System.out.println("\t1 - Realizar login\n\t2 - Realizar cadastro\n\t3 - Verificar catalogo\n\t4 - Sair\n>> ");	
			this.input = ferramenta.scanInt();
		} while (this.input < 1 && this.input > 4);
		
		return this.input;
	}
	
	public int loggedInClienteText() {
		do {
			System.out.println("\t1 - Comprar\n\t2 - Verificar pedidos\n\t3 - Logout\n\t4 - Sair\n>> ");
			this.input = ferramenta.scanInt();
		} while (this.input < 1 && this.input > 4);
		
		return this.input;
	}
	
	public int realizarLoginCadastro(String tipo) {
		do {
			System.out.println("\t1 - " + tipo + " de cliente\n\t2 - " + tipo + " de funcionario");
			this.input = ferramenta.scanInt();
		} while (this.input < 1 && this.input > 2);
		
		return this.input;
	}
	
	public int loggedInFuncionarioText() {
		do {
			System.out.println("\t1 - Gerenciar pedidos\n\t2 - INFO\n\t3 - Logout\n>> ");
			this.input = ferramenta.scanInt();
		} while (this.input < 1 && this.input > 3);
		
		return this.input;
	}
	
	public int loggedInTextCatalogo() {
		do {
			System.out.println("\n\t1 - Verificar catalogo\n\t2 - Verificar carrinho\n\t3 - Editar Carrinho\n\t4 - Finalizar Compra\n\t5 - Voltar ao menu anterior\n>> ");
			this.input = ferramenta.scanInt();
		} while (input < 1 && input > 5);
		
		return this.input;
	}
	
	public int infoText() {
		do {
			System.out.println("\n\t1 - Relacao de livros em estoque\n\t2 - Relacao de lista de compras\n\t3 - Relacao de clientes cadastrados\n\t4 - Pagamentos efetuados\n\t5 - Perfil de cada cliente\n>> ");
			this.input = ferramenta.scanInt();
		} while (input < 1 && input > 5);
		
		return this.input;
	}

}
