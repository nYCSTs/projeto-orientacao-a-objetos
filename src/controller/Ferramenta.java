package controller;
import java.util.Scanner;

@SuppressWarnings("resource")
public class Ferramenta {	
    public String scan() {     
		Scanner scanner = new Scanner(System.in);
    	return scanner.next(); 
    }
    
    public String scanNextLine() {
    	Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    
    public int scanInt() {
		Scanner scanner = new Scanner(System.in);
    	return scanner.nextInt();
    }
    
    public String gerarPedido() {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789"; 
        StringBuilder sb = new StringBuilder(6); 
  
        for (int i = 0; i < 6; i++) { 
            int index = (int)(AlphaNumericString.length() * Math.random()); 
  
            sb.append(AlphaNumericString.charAt(index)); 
        } 
  
        return sb.toString(); 
    }
}