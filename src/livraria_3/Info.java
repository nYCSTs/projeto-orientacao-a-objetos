package livraria_3;
import java.util.ArrayList;
public class Info {
	public void quantidadeLivrosComprado() {
		
	}
	public void dadosUsuario(ArrayList<Usuario> listaUsuarios) {
		for (int i = 0; i < listaUsuarios.size(); i++) {
			this.dadosUsuario(listaUsuarios.get(i));
		}
	}
	public void dadosUsuario(Usuario usuario) {
		System.out.println("Nome:............................ " + usuario.getNome());
		System.out.println("Email:........................... " + usuario.getEmail());
		System.out.println("Quantidade de livros comprados:.. " + usuario.getLivrosComprados());
		System.out.println("Quantidade de compras feitas:.... " + usuario.getComprasFeitas());
	}
}
