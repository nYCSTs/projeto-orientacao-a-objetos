package livraria_3;
import java.util.ArrayList;
public class Info {
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
	
	public void listaCompras(ArrayList<Usuario> usuarios) {
		for (int i = 0; i < usuarios.size(); i++) {
			usuarios.get(i).mostrarCompras();
		}
	}
	
	public void clientesCadastrados(ArrayList<Usuario> usuarios) {
		for (int i = 0; i < usuarios.size(); i++) {
			System.out.println("\tID............: " + usuarios.get(i).getID());
			System.out.println("\tNome..........: " + usuarios.get(i).getNome());
			System.out.println("\tCargo.........: " + usuarios.get(i).getCargo());
			System.out.println("\tSexo..........: " + usuarios.get(i).getSexo());
			System.out.println("\tEmail.........: " + usuarios.get(i).getEmail());
			System.out.println("\t----------------------------------");
		}
		/*
		 this.ID = id;
		this.nome = nome;
		this.CPF = cpf;
		this.identidade = identidade;
		this.filiacao = filiacao;
		this.escolaridade = escolaridade;
		this.sexo = sexo;
		this.estadoCivil = estadoCivil;
		this.naturalidade = naturalidade;
		this.endereco = endereco;
		this.cargo = cargo;
		this.telefone = telefone;
		this.email = email;
		this.senha = senha;
		 
		 
		*/
	}
}
