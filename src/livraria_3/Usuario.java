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
	private ArrayList<Compra> listaPedidos = new ArrayList<Compra>();
	private int comprasFeitas = 0;
	private int livrosComprados = 0;
	
	public void addCompra(int totalLivros, Compra compras) {
		this.comprasFeitas++;
		this.livrosComprados += totalLivros;
		listaPedidos.add(compras);
	}
	
	public void setValues(int ID, String nome, String cpf, String identidade, String filiacao, String escolaridade, String sexo, String estadoCivil, String naturalidade, String endereco, String cargo, String telefone, String email, String senha) {	
		this.ID = ID;
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
	
	public Usuario criarConta(int userID) {
		Ferramentas scan = new Ferramentas();
		this.ID = userID;
	
		System.out.print("Nome: ");
	    nome = scan.scanNextLine();
	    
	    do {
	    	System.out.print("CPF (somente numeros): ");
		    this.CPF = scan.scan();
	    } while (!this.CPF.matches("-?\\d+(\\.\\d+)?") || this.CPF.length() != 11);
	    
	    System.out.print("Identidade (somente numeros): ");
	    this.identidade = scan.scan();
	    
	    System.out.print("Filiação: ");
	    this.filiacao = scan.scanNextLine();
	    
	    System.out.print("Escolaridade: ");
	    this.escolaridade = scan.scanNextLine();
	    
	    System.out.print("Sexo (M/F): ");
	    this.sexo = scan.scan();
	    
	    System.out.print("Estado Civil: ");
	    this.estadoCivil = scan.scan();
	    
	    System.out.print("Naturalidade: ");
	    this.naturalidade = scan.scan();
	    
	    System.out.print("Endereço: ");
	    this.endereco = scan.scanNextLine();
	    
	    System.out.print("Cargo: ");
	    this.cargo = scan.scanNextLine();
	    
	    System.out.print("Telefone: ");
	    this.telefone = scan.scan();
	    
	    System.out.print("email: ");
	    this.email = scan.scan();
	    
	    System.out.print("senha: ");
	    this.senha = scan.scanNextLine();
	    
	    return this;
	}
	
	//GET
	public ArrayList<Compra> getListaPedidos() {
		return this.listaPedidos;
	}
	
	public int getComprasFeitas() {
		return this.comprasFeitas;
	}
	
	public int getLivrosComprados() {
		return this.livrosComprados;
	}
	
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
}
