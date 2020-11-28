package controller;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Metodos usados para realizar certas operacoes, como para obter a entradas do usuario, gerar codigo de pedido e fazer verificacoes
 * @author lucas
 * @version 1.0 (Nov 2020)
 */
public class Ferramenta {	
	private DecimalFormat format = new DecimalFormat("#0.00");
	/**
	 * Obtem uma entrada no formato String do usuario
	 * @return entrada do usuario (String)
	 */
    public String scanNextLine() {
    	@SuppressWarnings("resource")
    	Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    
    /**
     * Obtem uma entrada no formato Int do usuario
     * @return entrada do usuario (Int)
     */
    public int scanInt() {
    	@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
    	return scanner.nextInt();
    }
    
    public double scanDouble() {
    	@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
    	return scanner.nextDouble();
    }
    
    /**
     * Gera um codigo para o pedido. O codigo pode ter letras de A-Z e numeros de 0-9
     * @return o codigo gerado na forma de um String
     */
    public String gerarPedido() {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789"; 
        StringBuilder sb = new StringBuilder(6); 
  
        for (int i = 0; i < 6; i++) { 
            int index = (int)(AlphaNumericString.length() * Math.random()); 
  
            sb.append(AlphaNumericString.charAt(index)); 
        } 
  
        return sb.toString(); 
    }
    
    /**
     * Verifica a validade de um CPF 
     * @param cpf recebe um CPF passado como String
     * @return verdadeiro caso o CPF seja valido e falso caso não seja
     */
    public boolean verificaCPF(String cpf) {
    	int soma = 0, primeiroNumero, segundoNumero;
		
		if (!cpf.equals("11111111111") && !cpf.equals("22222222222") && !cpf.equals("33333333333") && !cpf.equals("44444444444") && !cpf.equals("55555555555") && !cpf.equals("66666666666") && !cpf.equals("77777777777") && !cpf.equals("88888888888") && !cpf.equals("99999999999") && !cpf.equals("00000000000") && cpf.length() == 11 && cpf.matches("-?\\d+(\\.\\d+)?")) {
			for (int i = 0, k = 10; i < 9; i++, k--) {
				soma += (Integer.parseInt(String.valueOf(cpf.charAt(i))) * k);
			}
			primeiroNumero = (soma * 10) % 11;
			soma = 0;
			for (int i = 0, k = 11; i < 10; i++, k--) {
				soma += (Integer.parseInt(String.valueOf(cpf.charAt(i))) * k);
			}
			segundoNumero = (soma * 10) % 11;
			
			if (Integer.parseInt(String.valueOf(cpf.charAt(9))) == primeiroNumero && Integer.parseInt(String.valueOf(cpf.charAt(10))) == segundoNumero) {
				return true;
			} else {
				System.out.println("CPF invalido, tente novamente.");
				return false;
			}
		} else {
			System.out.println("CPF invalido, tente novamente.");
			return false;
		}
    }
    
    /**
     * Transforma um numero em um valor formatado, evitando um excesso de casas decimais 
     * @param valor um valor a ser formatado
     * @return uma string com 2 casas decimais
     */
    public String obterValorFormatado(double valor) {
    	return format.format(valor);
    }
}

