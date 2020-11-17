package livraria.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import livraria.controller.Pedido;

public class Cliente extends Usuario {
	private ArrayList<Pedido> comprasFeitas = new ArrayList<Pedido>();
	private Map<String,Integer> quantidadeCategoria = new HashMap<String,Integer>();	
	
	public Cliente(int id, String nome, String cpf, String identidade, String pai, String mae, String escolaridade, String sexo, String estadoCivil, String naturalidade, String endereco, String cargo, String telefone, String email, String senha) {
		super(id, nome, cpf, identidade, pai, mae, escolaridade, sexo, estadoCivil, naturalidade, endereco, cargo, telefone,email, senha);
		this.quantidadeCategoria.put("INFANTIL", 0);
		this.quantidadeCategoria.put("TECNICO", 0);
		this.quantidadeCategoria.put("FICCAO", 0);
		this.quantidadeCategoria.put("NAO FICCAO", 0);
	}
	
	public ArrayList<Pedido> getPedidos() {
		return this.comprasFeitas;
	}
	
	public boolean mostrarCompras() {
		if (comprasFeitas.size() == 0) {
			System.out.println("Nenhuma compra feita.\n");
			return false;
		} else {
			System.out.println(getNome() + "(" + getID() + ")");
			for (int i = 0; i < comprasFeitas.size(); i++) {
				comprasFeitas.get(i).mostrarItens();
			}
			return true;
		}
	}
	
	public void adicionarCompra(Pedido pedido) {
		comprasFeitas.add(pedido);
	}
	
	public Map<String, Integer> livrosCompradosPorCategoria() {
		return this.quantidadeCategoria;
	}
	
	public void atualizarQuantidadeComprada(ArrayList<Item> sacola) {
		for (int i = 0; i < sacola.size(); i++) {
			quantidadeCategoria.put(sacola.get(i).getCategoria(), quantidadeCategoria.get(sacola.get(i).getCategoria()) + 1);
		}
	}
}
