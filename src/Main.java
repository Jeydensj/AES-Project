
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HexFormat;
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Copy and paste your file path");
        String uInput = sc.nextLine();
        Path myAesFile = Paths.get(uInput);
        System.out.println(myAesFile);

        try {
            byte[] fileToBytes = Files.readAllBytes(myAesFile);
            HexFormat hex = HexFormat.ofDelimiter(" ");
            String plaintext = hex.formatHex(fileToBytes);
            System.out.println("The hex values of our plaintext is: " + plaintext + "\n");
        } catch (IOException var7) {
            IOException e = var7;
            System.out.println(e.getMessage());
        }
    }
}