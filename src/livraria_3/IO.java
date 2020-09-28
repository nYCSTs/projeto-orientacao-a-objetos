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
		Usuario usuario = new Usuario();
		usuario.setValues(Integer.parseInt(dados.get(0)), dados.get(1), dados.get(2), dados.get(3), dados.get(4), dados.get(5), dados.get(6), dados.get(7), dados.get(8), dados.get(9), dados.get(10), dados.get(11), dados.get(12), dados.get(13));
		return usuario;
	}
	
	//LEITURA
	public ArrayList<Usuario> leituraUsuarios() {
		File file;
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		ArrayList<String> dados = new ArrayList<String>();

		try {
			for (; fileRead("usuario-".concat(Integer.toString(userID))).exists(); userID++) {
				file = fileRead("usuario-".concat(Integer.toString(userID)));
				FileReader fr= new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				while (br.ready()) {
					dados.add(br.readLine());
				}
				
				usuarios.add(criarUsuario(dados));
				dados.clear();
			}
		} catch (IOException ex)  {
			ex.getStackTrace();
		}	
		
		return usuarios;
	}

	public Livro leituraLivros(String fileName) {
		File file = new File(fileName);
		Livro livro = new Livro(); 
		String line;

		try {
			if (file.exists()) {
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				//categoria
				line = br.readLine();
				livro.setCategoria(line);
				//id
				line = br.readLine();
				livro.setID(Integer.parseInt(line));
				//quantidade
				line = br.readLine();
				livro.setQuantidadeEstoque(Integer.parseInt(line));
				//bookName
				line = br.readLine();
				livro.setBookName(line);
				//authorName
				line = br.readLine();
				livro.setAuthorName(line);
				//ISBN
				line = br.readLine();
				livro.setISBN(line);

			} else {
				livro.setCategoria("END");
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return livro;
	}

	//REGISTRO
	public void registrarUsuario(Usuario usuario, int userID) {
		try {
			File file = new File("./usuario-".concat(Integer.toString(userID)));
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
	
	public int getUserID() {
		return this.userID;
	}
}
