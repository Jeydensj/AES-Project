import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HexFormat;
import java.util.Scanner;

public class Main {
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

}
