package livraria_3;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class IO {	
	private Vector<Usuario> usuarios = new Vector<Usuario>();
	private Vector<Livro> livros = new Vector<Livro>();
	private int userID = 1;
	private int bookID = 1;
	
	//LEITURA DE ARQUIVO
	private Usuario createUser(Vector<String> dados) {
		Usuario usuario = new Usuario(Integer.parseInt(dados.get(0)));
		usuario.setValues(dados.get(1), dados.get(2), dados.get(3), dados.get(4), dados.get(5), dados.get(6), dados.get(7), dados.get(8), dados.get(9), dados.get(10), dados.get(11), dados.get(12), dados.get(13));
		return usuario;
	}
	
	private Livro createLivro(Vector <String> dados) {
		Livro livro = new Livro(Integer.parseInt(dados.get(1)));
		livro.criarLivro(Integer.parseInt(dados.get(2)), dados.get(3), dados.get(4), dados.get(5));
		return livro;
	}

	public Vector<Usuario> leituraUsuarios() {
		//leitura
		String line = " ";
		File file = new File("./Dados.txt");
		Vector<String> dados = new Vector<String>();
		
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			
			if (file.exists()) {
				while (br.ready()) {
	            	line = br.readLine();
	            	if (!line.equals("")) {
	            		dados.add(line);
	            	} else {
	            		//id
						//usuario.setID(Integer.parseInt(dados.get(0)));
						
	            		usuarios.add(createUser(dados));
						dados.clear();
						this.userID++;
	            	}
				}	
			}
		} 
		catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return usuarios;
	}

	public Vector<Livro> leituraLivros(String categoria) {
		File file = new File("./Catalogo.txt");
		Vector<String> dados = new Vector<String>();
		String line;
		int count = 0;

		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			if (file.exists()) {
				while (br.ready()) {
					line = br.readLine();
					if (count == 0 && !line.equals(categoria) ) {
						for (int i = 0; i < 6; i++) {
							br.readLine();
						}
					} else {
						count = 1;
						if (!line.equals("")) {
							dados.add(line);

						} else {	
							count = 0;
							livros.add(createLivro(dados));
							dados.clear();
							this.bookID++;
						}						
					}	
				}	
			}	
		} 
		catch (IOException ex) {
			ex.printStackTrace();
		}	
		return livros;
	}

	//ESCRITA DE ARQUIVO
	
	//RETORNO IDs
	public int getLastUserID() {
		return userID;
	}
	
	public int getLastBookID() {
		return bookID;
	}
}
