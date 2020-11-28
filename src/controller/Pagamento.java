package controller;
import java.util.ArrayList;
import model.*;
import view.*;

/**
 * Realizar o pagamento dos itens
 * @author lucas
 * @version 1.0 (Nov 2020)
 */
public class Pagamento {
	
	/**
	 * Etapa responsavel por obter o valor do frete, obter forma de pagamento, obter endereco e finalmente finalizar o pedido, registrando devidamente
	 * @param sacolaDeItens todos os itens adicionados e mantidos, o carrinho
	 * @param cliente referente ao cliente que esta fazendo a compra
	 * @param total soma dos preco dos itens do carrinho
 	 */
	public void realizarPagamento(ArrayList<Item> sacolaDeItens, Cliente cliente, double total) {
		int frete;
		String enderecoCompleto, formaPagamento = "";
		Pedido pedido = new Pedido();
		Menu menu = new Menu();
		Ferramenta ferramenta = new Ferramenta();
		
		frete = menu.textoObterFrete(total);
		
		switch (menu.textoFormaPagamento()) {
			case 1:
				formaPagamento = "cartao de credito";
				menu.textoDadosCartao();
				break;
			case 2:
				System.out.println("O boleto foi enviado para seu email.\n");
				formaPagamento = "boleto bancario";
		}
		
		enderecoCompleto = menu.textoObterEndereco();
		
		System.out.println("\nPagamento feito com sucesso, obrigado!");
		
		pedido.adicionarPedido(sacolaDeItens, formaPagamento, enderecoCompleto, total, frete, total + frete);
		pedido.setCodigoDePedido(ferramenta.gerarPedido());
		cliente.adicionarCompra(pedido);
		cliente.atualizarQuantidadeComprada(sacolaDeItens);
	}
}
