package livraria_3;
import java.util.ArrayList;

public class Usuario {	
	//dados
	private int ID;
	private String nome;
	private String CPF;
	private String identidade;
	private String filiacao;
	private String sexo;
	private String estadoCivil;
	private String naturalidade;
	private String escolaridade;
	private String endereco;
	private String cargo;
	private String telefone;
	private String email;
	private String senha;
	//status
	private ComprasFeitas historico = new ComprasFeitas();
	
	public Usuario(int id, String nome, String cpf, String identidade, String filiacao, String escolaridade, String sexo, String estadoCivil, String naturalidade, String endereco, String cargo, String telefone, String email, String senha) {	
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
	}
	
	public void adicionarCompra(ArrayList<Pedido> sacola, String pagamento) {
		int totalCompras;
		double totalPago = 0;
		boolean statusPagamento = false;
		
		switch (pagamento) {
		case "cartao de credito":
			statusPagamento = true;
			break;
		case "boleto bancario":
			statusPagamento = false;
		}
		
		for (totalCompras = 0; totalCompras < sacola.size(); totalCompras++) {
			historico.pedidos.add(sacola.get(totalCompras));
			totalPago += sacola.get(totalCompras).getPreco();
			sacola.get(totalCompras).setPagamento(statusPagamento, pagamento);
		}
		
		historico.adicionarCompra();
		historico.totalCompras(totalCompras);
		historico.totalPago(totalPago);	
	}
	
	public void mostrarCompras() {
		historico.mostrarCompras(this.ID, this.nome);
	}

	//GET	
	public int getID() {
		return this.ID;
	}
	public String getNome() {
		return this.nome;
	}
	public String getCPF() {
		return this.CPF;
	}
	public String getIdentidade() {
		return this.identidade;
	}
	public String getFiliacao() {
		return this.filiacao;
	}
	public String getSexo() {
		return this.sexo;
	}
	public String getEstadoCivil() {
		return this.estadoCivil;
	}
	public String getEscolaridade() {
		return this.escolaridade;
	}
	public String getNaturalidade() {
		return this.naturalidade;
	}
	public String getEndereco() {
		return this.endereco;
	}
	public String getCargo() {
		return this.cargo;
	}
	public String getTelefone() {
		return this.telefone;
	}
	public String getEmail() {
		return this.email;
	}
	public String getSenha() {
		return this.senha;
	}
	
	public Usuario(int id) {
		this.ID = id;
	}
}
