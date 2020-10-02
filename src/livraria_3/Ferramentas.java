package livraria_3;
import java.util.Scanner;

public class Ferramentas {
    
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
}
