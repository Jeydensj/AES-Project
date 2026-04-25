import java.util.HexFormat;
import java.nio.file.*;
import java.util.Scanner;

void main() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Copy and paste your file path");
    String uInput = sc.nextLine();
    Path myAesFile = Paths.get(uInput);
    System.out.println(myAesFile);
    try {
        byte [] fileToBytes = Files.readAllBytes(myAesFile);
        HexFormat hex = HexFormat.ofDelimiter(" ");
        String plaintext = hex.formatHex(fileToBytes);
        System.out.println("The hex values of our plaintext is: "+plaintext+"\n");

    }catch (IOException e){
        System.out.println(e.getMessage());
    }




}
