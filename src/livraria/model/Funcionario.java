package livraria.model;
import java.util.ArrayList;
import livraria.view.Menu;

public class Funcionario extends Usuario {
	public Funcionario(int id, String nome, String cpf, String identidade, String pai, String mae, String escolaridade, String sexo, String estadoCivil, String naturalidade, String endereco, String cargo, String telefone, String email, String senha) {
		super(id, nome, cpf, identidade, pai, mae, escolaridade, sexo, estadoCivil, naturalidade, endereco, cargo, telefone,
				email, senha);
	}
	
	public void relacaoEstoque(ArrayList<Livro> catalogo) {
		int qntInfantil = 0, qntTecnico = 0, qntFiccao = 0, qntNFiccao = 0;
		for (int i = 0; i < catalogo.size(); i++) {
			if (catalogo.get(i).getQuantidadeEstoque() > 0) {
				System.out.println("\t" + catalogo.get(i).getBookName() + " (" + catalogo.get(i).getAuthorName() + ") " + catalogo.get(i).getQuantidadeEstoque() + "x");
				switch (catalogo.get(i).getCategoria()) {
				case "INFANTIL":
					qntInfantil += 1;
					break;
				case "TECNICO":
					qntTecnico += 1;
					break;
				case "FICCAO":
					qntFiccao += 1;
					break;
				case "NAO FICCAO":
					qntNFiccao += 1;
				}
			}			
		}
		System.out.println("\n\tLivros infantis: " + qntInfantil + "\n\tLivros tecnicos: " + qntTecnico + "\n\tLivros Ficcao: " + qntFiccao + "\n\tLivros de nao ficcao: " + qntNFiccao + "\n");
	}
	
	public void mostrarCompras(Cliente cliente) {
		System.out.println(cliente.getNome() + "(" + cliente.getID() + ")");
		for (int i = 0; i < cliente.getPedidos().size(); i++) {
			cliente.getPedidos().get(i).mostrarItens();
		}
	}
	
	public void listaCompras(ArrayList<Cliente> cliente) {
		for (int i = 0; i < cliente.size(); i++) {
			if (cliente.get(i).getPedidos().size() > 0) {
				mostrarCompras(cliente.get(i));
			} 
		}
	}
	
	
	public void clientesCadastrados(ArrayList<Cliente> clientes) {
		for (int i = 0; i < clientes.size(); i++) {
			System.out.println("\tID:............: " + clientes.get(i).getID());
			System.out.println("\tNome:..........: " + clientes.get(i).getNome());
			System.out.println("\tCargo:.........: " + clientes.get(i).getCargo());
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
		String code;
		Menu menu = new Menu();
		
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getPedidos().size() > 0) {
				mostrarCompras(clientes.get(i));
			}
		}
		
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
			System.out.println("Codigo nao encontrado.");
		}
	}
	
	public void verificarPagamentos(ArrayList<Cliente> clientes) {
		for (int i = 0; i < clientes.size(); i++) {
			System.out.println(clientes.get(i).getNome() + " (" + clientes.get(i).getID() + "):");
			for (int j = 0; j < clientes.get(i).getPedidos().size(); j++) {
				System.out.println("\tPedido:...........: #" + clientes.get(i).getPedidos().get(j).getOrderCode());
				System.out.println("\tNome:.............: " + clientes.get(i).getPedidos().get(j).getFormaPagamento());
				System.out.println("\tPagamento:........: " + clientes.get(i).getPedidos().get(j).getStatusPagamento() + "\n\n");
			}
		}
 	}
}
