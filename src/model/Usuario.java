package model;

/**
 * Serve de modelo para as classes Cliente e Funcionario
 * @author lucas
 * @version 1.0 (Nov 2020)
 */
public abstract class Usuario {	
	private int ID;
	private String nome;
	private String CPF;
	private String identidade;
	private String pai;
	private String mae;
	private String sexo;
	private String estadoCivil;
	private String naturalidade;
	private String escolaridade;
	private String endereco;
	private String cargo;
	private String telefone;
	private String email;
	private String senha;
	
	/**
	 * Possui caracteristicas que sao comum para o Cliente e para o Funcionario, a classe Usuario funciona portanto como uma classe pai para os dois.
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
	public Usuario(int id, String nome, String cpf, String identidade, String pai, String mae, String escolaridade, String sexo, String estadoCivil, String naturalidade, String endereco, String cargo, String telefone, String email, String senha) {	
		this.ID = id;
		this.nome = nome;
		this.CPF = cpf;
		this.identidade = identidade;
		this.pai = pai;
		this.mae = mae;
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
	public String getPai() {
		return this.pai;
	}
	public String getMae() {
		return this.mae;
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
