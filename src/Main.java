<<<<<<< HEAD

=======
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HexFormat;
>>>>>>> origin/Jeyden-Sandbox
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HexFormat;
public class Main {
<<<<<<< HEAD
    public static void main(String[] args) {
=======
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Copy and paste your file path:");
        String uInput = sc.nextLine();
// 1. Convert the input string into a Path
        Path path = Paths.get(uInput);
        try {
            // 2. Read all bytes from the file
            byte[] fileBytes = Files.readAllBytes(path);
            // 3. Convert those bytes to Hex
            HexFormat hex = HexFormat.ofDelimiter(" ");
            String hexOutput = hex.formatHex(fileBytes);

            System.out.println("Hex Output: " + hexOutput);
        } catch (IOException e) {
            System.out.println("Could not read the file. Check the path!");
        }
    }
>>>>>>> origin/Jeyden-Sandbox

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