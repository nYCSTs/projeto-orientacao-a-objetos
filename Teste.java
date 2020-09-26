import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;
import java.util.Scanner;
public class Teste {
    public static void main(String[] args) {
        File file = new File("./Teste.txt");
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            if (br.readLine() == "") {
                System.out.println("ABC");
            }
        } catch (IOException ex) {
            System.out.println("EDEDE");
        } 
    }
}
