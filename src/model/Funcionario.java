/**
 * <div id="classe-funcionario"></div>
 */
package model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import view.Menu;


/**
 * Classe referente a conta do tipo FUNCIONARIO<p>embarca a disponibilizacao de estatisticas sobre os clientes registrados e da livraria, tambem permitindo o gerenciamento dos pedidos
 * @author lucas
 * @version 1.0 (Nov 2020)
 */
public class Funcionario extends Usuario {	
	
	/**
	 * Construtor da classe, com um super de Usuario
	 * @param id referente ao id do funcionario
	 * @param nome referente ao nome do funcionario
	 * @param cpf referente ao cpf do funcionario
	 * @param identidade referente a identidade do funcionario
	 * @param pai referente ao nome do pai do funcionario
	 * @param mae referente ao nome da mae do funcionario
	 * @param escolaridade referente a escolaridade do funcionario
	 * @param sexo referente ao sexo do funcionario
	 * @param estadoCivil referente ao estado civil do funcionario
	 * @param naturalidade referente ao naturalidade do funcionario
	 * @param endereco referente ao endereco do funcionario
	 * @param cargo referente ao cargo do funcionario
	 * @param telefone referente ao telefone do funcionario
	 * @param email referente ao email do funcionario
	 * @param senha referente ao senha do funcionario
	 */
	public Funcionario(int id, String nome, String cpf, String identidade, String pai, String mae, String escolaridade, String sexo, String estadoCivil, String naturalidade, String endereco, String cargo, String telefone, String email, String senha) {
		super(id, nome, cpf, identidade, pai, mae, escolaridade, sexo, estadoCivil, naturalidade, endereco, cargo, telefone,
				email, senha);
	}
	
	
	/**
	 * Verifica a situacao de estoque dos livros da livraria
	 * @param catalogo lista de <a href="./Livro.html#classe-livros">livros</a> cadastrados
	 */
	public void relacaoEstoque(ArrayList<Livro> catalogo) {
		String espacamento = " ";
		int idMaximo = Integer.toString(catalogo.get(catalogo.size() - 1).getID()).length();
		Map<String,Integer> quantidadeCategoria = new HashMap<String,Integer>();
		ArrayList<Livro> livrosForaDeEstoque = new ArrayList<Livro>();
		quantidadeCategoria.put("INFANTIL", 0);
		quantidadeCategoria.put("TECNICO", 0);
		quantidadeCategoria.put("FICCAO", 0);
		quantidadeCategoria.put("NAO FICCAO", 0);
		
		
		
		System.out.println("\tID" + espacamento.repeat(40) + "Titulo" + espacamento.repeat(50) + "Autor" + espacamento.repeat(76) + "Categoria" + espacamento.repeat(20) + "Quant.");
		for (int i = 0; i < catalogo.size(); i++) {
			if (catalogo.get(i).getQuantidadeEstoque() > 0) {
				System.out.println("\t" + catalogo.get(i).getID() + espacamento.repeat(idMaximo - Integer.toString(catalogo.get(i).getID()).length())  + " | " + catalogo.get(i).getBookName() + espacamento.repeat(85 - catalogo.get(i).getBookName().length()) + "  " + catalogo.get(i).getAuthorName() + "  " + espacamento.repeat(85 - catalogo.get(i).getAuthorName().length()) + catalogo.get(i).getCategoria() + espacamento.repeat(30 - catalogo.get(i).getCategoria().length()) + catalogo.get(i).getQuantidadeEstoque() + "x");
				quantidadeCategoria.put(catalogo.get(i).getCategoria(), quantidadeCategoria.get(catalogo.get(i).getCategoria()) + 1);	
			} else {
				livrosForaDeEstoque.add(catalogo.get(i));
			}
		}
		System.out.println("\n\tLivros infantis: " + quantidadeCategoria.get("INFANTIL") + "\n\tLivros tecnicos: " + quantidadeCategoria.get("TECNICO") + "\n\tLivros Ficcao: " + quantidadeCategoria.get("FICCAO") + "\n\tLivros de nao ficcao: " + quantidadeCategoria.get("NAO FICCAO") + "\n");
		if (livrosForaDeEstoque.size() > 0) {
			System.out.println("\tLivros fora de estoque:");
			for (int i = 0; i < livrosForaDeEstoque.size(); i++) {
				System.out.println("\t\t" + livrosForaDeEstoque.get(i).getBookName() + "(" + livrosForaDeEstoque.get(i).getCategoria() + ")");
			}
		}
	}
	
	/**
	 * Verifica a lista de pedidos feitos por cada cliente
	 * @param clientes lista de <a href="./Cliente.html#classe-cliente">clientes</a> cadastrados
	 */
	public int listaCompras(ArrayList<Cliente> clientes) {
		int flag = 0;
		for (int i = 0; i < clientes.size(); i++) {
			System.out.println(clientes.get(i).getNome() + " (" + clientes.get(i).getID() + "):");
			if (clientes.get(i).getPedidos().size() > 0) {
				flag = 1;
				for (int j = 0; j < clientes.get(i).getPedidos().size(); j++) {
					clientes.get(i).getPedidos().get(j).mostrarItens();
					if (clientes.get(i).getPedidos().get(j).getEnviado()) {
						System.out.println("\tResponsavel por encaminhar..: " + clientes.get(i).getPedidos().get(j).getFuncionarioResponsavel());
					}
				}
				System.out.println("\n");
			} else {
				System.out.println("\n\tNenhuma compra feita\n");
			}
		}
		return flag;
	}
	
	/**
	 * Lista de clientes cadastrados, com algumas informacoes significativas
	 * @param clientes lista de <a href="./Cliente.html#classe-cliente">clientes</a> cadastrados
	 */
	public void clientesCadastrados(ArrayList<Cliente> clientes) {
		for (int i = 0; i < clientes.size(); i++) {
			System.out.println("\tID...............: " + clientes.get(i).getID());
			System.out.println("\tNome.............: " + clientes.get(i).getNome());
			System.out.println("\tTelefone.........: " + clientes.get(i).getTelefone());
			System.out.println("\tEndereco.........: " + clientes.get(i).getEndereco());
			System.out.println("\tSexo.............: " + clientes.get(i).getSexo());
			System.out.println("\tEmail............: " + clientes.get(i).getEmail() + "\n");
		}
	}
	
	/**
	 * Quantidade de livros comprados de cada categoria por cada cliente
	 * @param clientes lista de <a href="./Cliente.html#classe-cliente">clientes</a> cadastrados
	 */
	public void categoriasPorCliente(ArrayList<Cliente> clientes) {
		for (int i = 0; i < clientes.size(); i++) {
			clientes.get(i).livrosCompradosPorCategoria();
		}
	}
	
	/**
	 * Permite gerenciar os pedidos ja feitos por clientes, marcando o nome do funcionario que foi responsavel pelo encaminhamento
	 * @param clientes lista de <a href="./Cliente.html#classe-cliente">clientes</a> cadastrados
	 */
	public void encaminharCompras(ArrayList<Cliente> clientes) {
		String code = "";
		Menu menu = new Menu();
		
		System.out.println("DADA");
		if (listaCompras(clientes) != 0) {
			code = menu.textoEncaminhamentoDePedido();
			if (!code.equals("0")) {
				for (int i = 0; i < clientes.size(); i++) {
					for (int j = 0; j < clientes.get(i).getPedidos().size(); j++){
						if (clientes.get(i).getPedidos().get(j).getOrderCode().equals(code)) {
							clientes.get(i).getPedidos().get(j).setStatusEnvio(true);
							clientes.get(i).getPedidos().get(j).setFuncionarioResponsavel(this.getNome());
							System.out.println("Pedido #" + code + " encaminhado com sucesso.");
							return;
						}
					}
				}
				System.out.println("\nCodigo nao encontrado.");
			}
		} else {
			System.out.println("Nenhuma compra efetuada");
		}
		
		
	}
	
	/**
	 * Status de pagamento de todos os pedidos feitos 
	 * @param clientes <a href="https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html">ArrayList</a> com todos os clientes cadastrados
	 */
	public void verificarPagamentos(ArrayList<Cliente> clientes) {
		for (int i = 0; i < clientes.size(); i++) {
			System.out.println(clientes.get(i).getNome() + " (ID: " + clientes.get(i).getID() + "):");
			if (clientes.get(i).getPedidos().size() == 0) {
				System.out.println("\tNenhum pedido feito.\n");
			} else {
				for (int j = 0; j < clientes.get(i).getPedidos().size(); j++) {
					System.out.println("\tCodigo do Pedido:...........: #" + clientes.get(i).getPedidos().get(j).getOrderCode());
					System.out.println("\tTipo de pagamento:..........: " + clientes.get(i).getPedidos().get(j).getFormaPagamento());
					System.out.println("\tStatus do pagamento:........: " + clientes.get(i).getPedidos().get(j).getStatusPagamento() + "\n");
				}
			}
			
		}
 	}
}
