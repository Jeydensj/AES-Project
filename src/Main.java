//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HexFormat;
import java.util.Scanner;

final class Main {
    Main() {
    }

    void main() {
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
