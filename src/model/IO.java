package model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Ira realizar todo o registro de contas e livros previamente ja criados. clientes: livraria\clientes<p>funcionarios: livraria\clientes<p>livros: livraria\livros
 * @author lucas
 * @version 1.0 (Nov 2020)
 */
public class IO {		
	public File fileRead(String fileName) {
		File file = new File(fileName);
		return file;
	}

	/**
	 * Sera chamada para cada arquivo lido referente a conta do tipo <b>cliente</b> 
	 * @param dados cada item presente no <a href="https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html">ArrayList</a> dados e passado para o construtor da classe Cliente
	 * @return a classe instanciada 
	 */
	public Cliente criarCliente(ArrayList<String> dados) {
		Cliente cliente = new Cliente(Integer.parseInt(dados.get(0)), dados.get(1), dados.get(2), dados.get(3), dados.get(4), dados.get(5), dados.get(6), dados.get(7), dados.get(8), dados.get(9), dados.get(10), dados.get(11), dados.get(12), dados.get(13), dados.get(14));
		return cliente;
	}
	
	/**
	 * Sera chamada para cada arquivos lido referente a conta do tipo <b>funcionario</b>
	 * @param dados cada item presente no <a href="https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html">ArrayList</a> dados e passado para o construtor da classe Funcionario
	 * @return a classe instanciada 
	 */
	public Funcionario criarFuncionario(ArrayList<String> dados) {
		Funcionario funcionario = new Funcionario(Integer.parseInt(dados.get(0)), dados.get(1), dados.get(2), dados.get(3), dados.get(4), dados.get(5), dados.get(6), dados.get(7), dados.get(8), dados.get(9), dados.get(10), dados.get(11), dados.get(12), dados.get(13), dados.get(14));
		return funcionario;
	}
	
	//LEITURA
	/**
	 * Realiza a leitura de contas previamente cadastradas. E responsavel por preencher os ArrayLists "clientes" e "funcionarios". Nao e necessario um retorno
	 * @param clientes lista de clientes cadastrados
	 * @param funcionarios lista de funcionarios cadastrados
	 */
	public void leituraUsuarios(ArrayList<Cliente> clientes, ArrayList<Funcionario> funcionarios) {
		File file;
		String[] paths = {"clientes/cliente-", "funcionarios/funcionario-"};
		ArrayList<String> dados = new ArrayList<String>();
		
		try {
			for (int i = 0; i < paths.length; i++) {
				for (int j = 1; fileRead(paths[i].concat(Integer.toString(j))).exists(); j++) {
					file = fileRead(paths[i].concat(Integer.toString(j)));
					FileReader fr = new FileReader(file);
					BufferedReader br = new BufferedReader(fr);
					while (br.ready()) {
						dados.add(br.readLine().trim());
					}
					
					switch (i) {
					case 0:
						clientes.add(criarCliente(dados));
						break;
					case 1:
						funcionarios.add(criarFuncionario(dados));
					}
					
					dados.clear();
					br.close();
				}
			}
		} catch (IOException ex)  {
			ex.getStackTrace();
		}	
	}
	
	public Livro criarLivro() {
		Livro livro = new Livro();
		return livro;
	}

	/**
	 * Realiza a leitura de livros cadastrados. E responsavel por preencher o ArrayList "livros". Nao e necessario um retorno
	 * @param livros lista de livros cadastrados
	 */
	public void leituraLivros(ArrayList<Livro> livros) {
		File file;
		Livro livro;
		
		try {
			for (int i = 1; fileRead("livros/livro-".concat(Integer.toString(i))).exists(); i++) {
				file = fileRead("livros/livro-".concat(Integer.toString(i)));
				livro = criarLivro();
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				
				//categoria
				livro.setCategoria(br.readLine().trim());
				//id
				livro.setID(Integer.parseInt(br.readLine().trim()));
				//idioma
				livro.setIdioma(br.readLine().trim());
				//bookName
				livro.setBookName(br.readLine().trim());
				//preco
				livro.setPreco(Double.parseDouble(br.readLine().trim()));
				//authorName
				livro.setAuthorName(br.readLine().trim());
				//editora
				livro.setEditora(br.readLine().trim());
				//quantidade de paginas
				livro.setQuantidadePaginas(Integer.parseInt(br.readLine().trim()));
				//quantidade em estoque
				livro.setQuantidadeEstoque(Integer.parseInt(br.readLine().trim()));				
				//ISBN
				livro.setISBN(br.readLine().trim());
				
				livros.add(livro);
				br.close();
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * No caso de um novo registro de um usuario essa nova conta criada e adicionada em um arquivo .txt para que a conta seja mantida apos o programa ser executado novamente
	 * @param usuario objeto da conta criada
	 * @param usuarioID id do novo usuario
	 * @param nomeDoArquivo nome do novo arquivo a ser criado
	 */
	public void registrarUsuario(Usuario usuario, int usuarioID, String nomeDoArquivo) {
		try {
			File file = new File(nomeDoArquivo.concat(Integer.toString(usuarioID)));
			FileWriter writer = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(writer);

			bw.write(Integer.toString(usuarioID));
			bw.newLine();
			bw.write(usuario.getNome());
			bw.newLine();
			bw.write(usuario.getCPF());
			bw.newLine();
			bw.write(usuario.getIdentidade());
			bw.newLine();
			bw.write(usuario.getPai());
			bw.newLine();
			bw.write(usuario.getMae());
			bw.newLine();
			bw.write(usuario.getEscolaridade());
			bw.newLine();
			bw.write(usuario.getSexo());
			bw.newLine();
			bw.write(usuario.getEstadoCivil());
			bw.newLine();
			bw.write(usuario.getNaturalidade());
			bw.newLine();
			bw.write(usuario.getEndereco());
			bw.newLine();
			bw.write(usuario.getCargo());
			bw.newLine();
			bw.write(usuario.getTelefone());
			bw.newLine();
			bw.write(usuario.getEmail());
			bw.newLine();
			bw.write(usuario.getSenha());
			bw.close();
		} catch (IOException ex) {
			ex.getStackTrace();
		}
	}
	
	public Livro registrarLivro(Livro livro, int livroID, String nomeDoArquivo) {
		DecimalFormat format = new DecimalFormat("#0.00");
		try {
			File file = new File(nomeDoArquivo.concat(Integer.toString(livroID)));
			FileWriter writer = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(writer);

			bw.write(livro.getCategoria());
			bw.newLine();
			
			bw.write(Integer.toString(livroID));
			bw.newLine();
			
			bw.write(livro.getIdioma());
			bw.newLine();
			
			bw.write(livro.getBookName());
			bw.newLine();
			
			bw.write(format.format(livro.getPreco()));
			bw.newLine();
			
			bw.write(livro.getAuthorName());
			bw.newLine();
			
			bw.write(livro.getEditora());
			bw.newLine();
			
			bw.write(Integer.toString(livro.getQuantidadePaginas()));
			bw.newLine();
			
			bw.write(Integer.toString(livro.getQuantidadeEstoque()));
			bw.newLine();
			
			bw.write(livro.getISBN());
			bw.close();
		} catch (IOException ex) {
			ex.getStackTrace();
		}
		
		return livro;
	}
}
