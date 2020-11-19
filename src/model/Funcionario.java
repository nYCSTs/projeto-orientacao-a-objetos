package model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import view.Menu;

public class Funcionario extends Usuario {
	private int verificador;
	
	public Funcionario(int id, String nome, String cpf, String identidade, String pai, String mae, String escolaridade, String sexo, String estadoCivil, String naturalidade, String endereco, String cargo, String telefone, String email, String senha) {
		super(id, nome, cpf, identidade, pai, mae, escolaridade, sexo, estadoCivil, naturalidade, endereco, cargo, telefone,
				email, senha);
	}
	
	public void relacaoEstoque(ArrayList<Livro> catalogo) {
		String espacamento = " ";
		Map<String,Integer> quantidadeCategoria = new HashMap<String,Integer>();
		ArrayList<Livro> livrosForaDeEstoque = new ArrayList<Livro>();
		quantidadeCategoria.put("INFANTIL", 0);
		quantidadeCategoria.put("TECNICO", 0);
		quantidadeCategoria.put("FICCAO", 0);
		quantidadeCategoria.put("NAO FICCAO", 0);
		
		System.out.println("\tID                Titulo                         Autor                        Categoria        Quant.");
		for (int i = 0; i < catalogo.size(); i++) {
			if (catalogo.get(i).getQuantidadeEstoque() > 0) {
				System.out.println("\t" + catalogo.get(i).getID() + " | " + catalogo.get(i).getBookName() + espacamento.repeat(35 - catalogo.get(i).getBookName().length()) + "  " + catalogo.get(i).getAuthorName() + "  " + espacamento.repeat(35 - catalogo.get(i).getAuthorName().length()) + catalogo.get(i).getCategoria() + espacamento.repeat(18 - catalogo.get(i).getCategoria().length()) + catalogo.get(i).getQuantidadeEstoque() + "x");
				quantidadeCategoria.put(catalogo.get(i).getCategoria(), quantidadeCategoria.get(catalogo.get(i).getCategoria()) + 1);	
			} else {
				livrosForaDeEstoque.add(catalogo.get(i));
			}
		}
		System.out.println("\n\tLivros infantis: " + quantidadeCategoria.get("INFANTIL") + "\n\tLivros tecnicos: " + quantidadeCategoria.get("TECNICO") + "\n\tLivros Ficcao: " + quantidadeCategoria.get("FICCAO") + "\n\tLivros de nao ficcao: " + quantidadeCategoria.get("NAO FICCAO") + "\n");
		if (livrosForaDeEstoque.size() > 0) {
			System.out.println("\tLivros fora de estoque:");
			for (int i = 0; i < livrosForaDeEstoque.size(); i++) {
				System.out.println("\t" + livrosForaDeEstoque.get(i).getBookName() + "(" + livrosForaDeEstoque.get(i).getCategoria() + ")");
			}
		}
	}
	/*
	public void mostrarCompras(Cliente cliente) {
		System.out.println(cliente.getNome() + "(" + cliente.getID() + ")");
		for (int i = 0; i < cliente.getPedidos().size(); i++) {
			cliente.getPedidos().get(i).mostrarItens();
			this.verificador = 1;
		}
		if (this.verificador != 1) {
			System.out.println("Nenhuma compra efetuada");
		}
	}
	*/
	
	public void listaCompras(ArrayList<Cliente> cliente) {
		for (int i = 0; i < cliente.size(); i++) {
			System.out.println(cliente.get(i).getNome() + "(" + cliente.get(i).getID() + ")");
			if (cliente.get(i).getPedidos().size() > 0) {
				cliente.get(i).getPedidos().get(i).mostrarItens();
				this.verificador = 1;
			} 
		}
	}
	
	
	public void clientesCadastrados(ArrayList<Cliente> clientes) {
		for (int i = 0; i < clientes.size(); i++) {
			System.out.println("\tID:............: " + clientes.get(i).getID());
			System.out.println("\tNome:..........: " + clientes.get(i).getNome());
			System.out.println("\tSexo:..........: " + clientes.get(i).getSexo());
			System.out.println("\tEmail:.........: " + clientes.get(i).getEmail() + "\n\n");
		}
	}
	
	public void categoriasPorCliente(ArrayList<Cliente> clientes) {
		for (int i = 0; i < clientes.size(); i++) {
			System.out.println("\tNome: " + clientes.get(i).getNome() + "\n");
			System.out.println("\tInfantil: " + clientes.get(i).livrosCompradosPorCategoria().get("INFANTIL"));
			System.out.println("\tTecnico: " + clientes.get(i).livrosCompradosPorCategoria().get("TECNICO"));
			System.out.println("\tFiccao: " + clientes.get(i).livrosCompradosPorCategoria().get("FICCAO"));
			System.out.println("\tNao Ficcao: " + clientes.get(i).livrosCompradosPorCategoria().get("NAO FICCAO") + "\n");
		}
	}
	
	public void encaminharCompras(ArrayList<Cliente> clientes) {

		String code = "";
		Menu menu = new Menu();
		
		listaCompras(clientes);
		
		if (verificador != 0) {
			code = menu.textoEncaminhamentoDePedido();
			
			if (code != "0") {
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
			System.out.println("\nNao foram encontrados pedidos.");
		}
	}
	
	public void verificarPagamentos(ArrayList<Cliente> clientes) {
		for (int i = 0; i < clientes.size(); i++) {
			System.out.println(clientes.get(i).getNome() + " (ID: " + clientes.get(i).getID() + "):");
			if (clientes.get(i).getPedidos().size() == 0) {
				System.out.println("\tNenhum pedido efetuado.\n");
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
