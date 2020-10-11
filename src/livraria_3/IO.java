package livraria_3;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class IO {	
	private int userID = 1;
	
	public File fileRead(String fileName) {
		File file = new File(fileName);
		return file;
	}

	
	public Usuario criarUsuario(ArrayList<String> dados) {
		Usuario usuario = new Usuario(Integer.parseInt(dados.get(0)), dados.get(1), dados.get(2), dados.get(3), dados.get(4), dados.get(5), dados.get(6), dados.get(7), dados.get(8), dados.get(9), dados.get(10), dados.get(11), dados.get(12), dados.get(13));
		return usuario;
	}
	
	//LEITURA
	public ArrayList<Usuario> leituraUsuarios(String fileName) {
		File file;
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		ArrayList<String> dados = new ArrayList<String>();

		try {
			for (; fileRead(fileName.concat(Integer.toString(userID))).exists(); userID++) {
				file = fileRead(fileName.concat(Integer.toString(userID)));
				FileReader fr= new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				while (br.ready()) {
					dados.add(br.readLine());
				}
				
				usuarios.add(criarUsuario(dados));
				dados.clear();
				br.close();
			}
		} catch (IOException ex)  {
			ex.getStackTrace();
		}	
		
		return usuarios;
	}
	
	public Livro criarLivro() {
		Livro livro = new Livro();
		return livro;
	}

	public ArrayList<Livro> leituraLivros() {
		String line;
		File file;
		Livro livro;
		ArrayList<Livro> livros = new ArrayList<Livro>();
		
		try {
			for (int i = 1; fileRead("livros/livro-".concat(Integer.toString(i))).exists(); i++) {
				file = fileRead("livros/livro-".concat(Integer.toString(i)));
				livro = criarLivro();
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				
				//categoria
				line = br.readLine().trim();
				livro.setCategoria(line);
				//id
				line = br.readLine().trim();
				livro.setID(Integer.parseInt(line));
				//bookName
				line = br.readLine().trim();
				livro.setBookName(line);
				//preco
				line = br.readLine().trim();
				livro.setPreco(Double.parseDouble(line));
				//authorName
				line = br.readLine().trim();
				livro.setAuthorName(line);
				//editora
				line = br.readLine().trim();
				livro.setEditora(line);
				//quantidade
				line = br.readLine().trim();
				livro.setQuantidadeEstoque(Integer.parseInt(line));				
				//ISBN
				line = br.readLine().trim();
				livro.setISBN(line);
				
				livros.add(livro);
				br.close();
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		
		return livros;
	}

	//REGISTRO
	public void registrarUsuario(Usuario usuario, int userID) {
		try {
			File file = new File("clientes/cliente-".concat(Integer.toString(userID)));
			FileWriter writer = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(writer);

			bw.write(Integer.toString(userID));
			bw.newLine();
			bw.write(usuario.getNome());
			bw.newLine();
			bw.write(usuario.getCPF());
			bw.newLine();
			bw.write(usuario.getIdentidade());
			bw.newLine();
			bw.write(usuario.getFiliacao());
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
}
