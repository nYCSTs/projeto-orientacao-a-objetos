package livraria;
import java.util.ArrayList;

public class Gerenciar {
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
	
	public void mostrarCompras(Usuario usuario) {
		System.out.println(usuario.getNome() + "(" + usuario.getID() + ")");
		for (int i = 0; i < usuario.getPedidos().size(); i++) {
			usuario.getPedidos().get(i).mostrarItens();
		}
	}
	
	public void listaCompras(ArrayList<Usuario> cliente) {
		for (int i = 0; i < cliente.size(); i++) {
			if (cliente.get(i).getPedidos().size() > 0) {
				mostrarCompras(cliente.get(i));
			} 
		}
	}
	
	
	public void clientesCadastrados(ArrayList<Usuario> usuarios) {
		for (int i = 0; i < usuarios.size(); i++) {
			System.out.println("\tID:............: " + usuarios.get(i).getID());
			System.out.println("\tNome:..........: " + usuarios.get(i).getNome());
			System.out.println("\tCargo:.........: " + usuarios.get(i).getCargo());
			System.out.println("\tSexo:..........: " + usuarios.get(i).getSexo());
			System.out.println("\tEmail:.........: " + usuarios.get(i).getEmail());
			System.out.println("\t----------------------------------");
		}
	}
	
	public void gerenciarCompras(ArrayList<Usuario> cliente) {
		String code;
		Ferramentas ferramenta = new Ferramentas();
		
		for (int i = 0; i < cliente.size(); i++) {
			if (cliente.get(i).getPedidos().size() > 0) {
				mostrarCompras(cliente.get(i));
			}
		}
		
		do {
			System.out.println("Qual pedido deseja encaminhar (somente o codigo, 6 digitos): ");
			code = ferramenta.scan().toUpperCase();			
		} while (code.length() != 6);

		for (int i = 0; i < cliente.size(); i++) {
			for (int j = 0; j < cliente.get(i).getPedidos().size(); j++){
				if (cliente.get(i).getPedidos().get(j).getOrderCode().equals(code)) {
					cliente.get(i).getPedidos().get(j).setStatusEnvio(true);
					System.out.println("Pedido #" + code + " encaminhado com sucesso.");
					return;
				}
			}
		}
		System.out.println("Codigo nao encontrado.");
	}
	
	public void verificarPagamentos(ArrayList<Usuario> clientes) {
		for (int i = 0; i < clientes.size(); i++) {
			System.out.println(clientes.get(i).getNome() + " (" + clientes.get(i).getID() + "):");
			for (int j = 0; j < clientes.get(i).getPedidos().size(); j++) {
				System.out.println("\tPedido:...........: #" + clientes.get(i).getPedidos().get(j).getOrderCode());
				System.out.println("\tNome:.............: " + clientes.get(i).getPedidos().get(j).getFormaPagamento());
				System.out.println("\tPagamento:........: " + clientes.get(i).getPedidos().get(j).getStatusPagamento() + "\n");
			}
			System.out.println("-------------------");
		}
 	}
}
