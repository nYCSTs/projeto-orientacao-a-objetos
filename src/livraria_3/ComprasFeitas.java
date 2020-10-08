package livraria_3;
import java.util.ArrayList;

public class ComprasFeitas {
	public ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
	private int comprasFeitas = 0;
	private int livrosComprados = 0; 
	private double totalPago;
	
	public void adicionarCompra() {
		this.comprasFeitas++;
	}
	
	public void totalCompras(int itens) {
		this.comprasFeitas += itens;
	}
	
	public void totalPago(double totalPago) {
		this.totalPago += totalPago;
	}
	
	public void mostrarCompras(int id, String nome) {
		System.out.println(nome + " (ID: " + id + "):");
		if (pedidos.size() == 0) {
			System.out.println("Nenhuma compra feita ainda.");
		} else {
			for (int i = 0; i < pedidos.size(); i++) {
				System.out.println("\tLivro...............: " + pedidos.get(i).getNomeLivro());
				System.out.println("\tID..................: " + pedidos.get(i).getIDLivro());
				System.out.println("\tPreco...............: " + pedidos.get(i).getPreco());
				System.out.println("\tCategoria...........: " + pedidos.get(i).getCategoria());
				System.out.println("\tForma de pagamento..: " + pedidos.get(i).getFormaPagamento());
				System.out.println("\tPago:...............: " + pedidos.get(i).getStatusPagamento() + "\n\n");
			} 
			System.out.println("\tCompras feitas: " + this.comprasFeitas);
			System.out.println("\tTotal de livros comprados: " + this.livrosComprados);
		}
		System.out.println("\t----------------------------------");
	}
	
}
