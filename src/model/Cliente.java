/**
 * <div id="classe-id"></div>
 */
package model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import controller.Pedido;

/**
 * Classe referente a conta do tipo CLIENTE
 * @author lucas
 * @version 1.0 (Nov 2020)
 */
public class Cliente extends Usuario {
	private ArrayList<Pedido> comprasFeitas = new ArrayList<Pedido>();
	private Map<String,Integer> quantidadeCategoria = new HashMap<String,Integer>();	
	
	/**
	 * Construtor da classe, com um super de Usuario. Tambem realiza a construcao dos itens do <a href="https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html">HashMap</a>
	 * @param id referente ao id do cliente
	 * @param nome referente ao nome do cliente
	 * @param cpf referente ao cpf do cliente
	 * @param identidade referente a identidade do cliente
	 * @param pai referente ao nome do pai do cliente 
	 * @param mae referente ao nome da mae do cliente 
	 * @param escolaridade referente a escolaridade do cliente 
	 * @param sexo referente ao sexo do cliente 
	 * @param estadoCivil referente ao estado civil do cliente 
	 * @param naturalidade referente ao naturalidade do cliente 
	 * @param endereco referente ao endereco do cliente 
	 * @param cargo referente ao cargo do cliente 
	 * @param telefone referente ao telefone do cliente 
	 * @param email referente ao email do cliente 
	 * @param senha referente ao senha do cliente 
	 */
	public Cliente(int id, String nome, String cpf, String identidade, String pai, String mae, String escolaridade, String sexo, String estadoCivil, String naturalidade, String endereco, String cargo, String telefone, String email, String senha) {
		super(id, nome, cpf, identidade, pai, mae, escolaridade, sexo, estadoCivil, naturalidade, endereco, cargo, telefone,email, senha);
		this.quantidadeCategoria.put("INFANTIL", 0);
		this.quantidadeCategoria.put("TECNICO", 0);
		this.quantidadeCategoria.put("FICCAO", 0);
		this.quantidadeCategoria.put("NAO FICCAO", 0);
	}
	
	/**
	 * Obtem os pedidos realizados pelo cliente
	 * @return a lista de pedidos feitos
	 */
	public ArrayList<Pedido> getPedidos() {
		return this.comprasFeitas;
	}
	
	/**
	 * Mostra a situacao das compras efetuadas pelo cliente
	 * @return a condicao da sacola corresponde a situacao da sacola. Estando ela vazia retorna um falso, possuindo itens retorna verdadeiro
	 */
	public boolean mostrarCompras() {
		if (comprasFeitas.size() == 0) {
			System.out.println("\nNenhuma compra feita.");
			return false;
		} else {
			System.out.println(getNome() + "(" + getID() + "): ");
			for (int i = 0; i < comprasFeitas.size(); i++) {
				comprasFeitas.get(i).mostrarItens();
			}
			return true;
		}
	}
	
	/**
	 * Registra um pedido feito na conta do cliente<p>Consiste somente em uma metodo para adicionar novos pedidos, nao possuindo retorno
	 * @param pedido o objeto da classe  
	 */
	public void adicionarCompra(Pedido pedido) {
		comprasFeitas.add(pedido);
	}
	
	/**
	 * Fornece a classe <a href="./Funcionario.html#classe-funcionario">Funcionario</a> a quantidade de livros comprados por categoria<p>Consiste somente em um metodo para mostrar as informacoes, nao possuindo um retorno
	 */
	public void livrosCompradosPorCategoria() {
		System.out.println(this.getNome() + " (ID: " + this.getID() + "):");
		System.out.println("\tInfantil: " + this.quantidadeCategoria.get("INFANTIL"));
		System.out.println("\tTecnico: " + this.quantidadeCategoria.get("TECNICO"));
		System.out.println("\tFiccao: " + this.quantidadeCategoria.get("FICCAO"));
		System.out.println("\tNao Ficcao: " + this.quantidadeCategoria.get("NAO FICCAO") + "\n");
	}
	
	/**
	 * Registra quantos livros de certa categoria o cliente comprou
	 * @param sacola contem os itens comprados
	 */
	public void atualizarQuantidadeComprada(ArrayList<Item> sacola) {
		for (int i = 0; i < sacola.size(); i++) {
			quantidadeCategoria.put(sacola.get(i).getCategoria(), quantidadeCategoria.get(sacola.get(i).getCategoria()) + 1);			
		}
	}
}
