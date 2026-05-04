
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HexFormat;
public class Main {
    public static byte[][] addRoundKey(int round, byte[][] roundKey, byte[][] plainBox){
        int coef=4*round;
        byte[][] resultBox = new byte[4][4];
        for(int c=coef; c<=coef+3;c++){
            for(int r=0; r<4;r++){
                resultBox[r][c-coef]=(byte)(plainBox[r][c-coef] ^ roundKey[r][c]); // roundKey being keyRound.keyArray
            }
        }
        return resultBox;
    }
    public static byte[][] xor(byte[]key, byte[]input){
        byte[][] key2D = keyRound.into2DArray(key);
        byte[][] input2D = keyRound.into2DArray(input);
        byte[][] initState = new byte[4][4];
        for(int r=0;r<4;r++){
            for(int c=0;c<4;c++){
                initState[r][c]=(byte)(input2D[r][c] ^ key2D[r][c]);
            }
        }
        return initState;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean run = true;
        do{
            System.out.println("AES Algorithm Run-Through" +
                    "\n1-) Select for AES run-through" +
                    "\n2-) Select to exit");
            int selection = sc.nextInt();
            sc.nextLine();

            switch(selection){

                case 1:
                    System.out.println("Copy and paste your file path");
                    String uInput = sc.nextLine();

                    System.out.println("User should input key: ");
                    String keyString = sc.nextLine();

                    byte[] keyArray = keyString.getBytes();
                    if(keyArray.length<16){ // if key size < 16
                        byte[] padded = new byte[16]; // new byte[] with padding
                        for(int i=0;i<16;i++){
                            if(i< keyArray.length){
                                padded[i]=keyArray[i]; // storing key until its last byte
                            }
                            else{
                                padded[i]=0x20; // padding with <space> after last byte
                            }
                        }
                        keyArray=padded; // updating the keyArray[]
                    }

                    Path myAesFile = Paths.get(uInput);
                    System.out.println(myAesFile);
                    byte[] fileToBytes=null;
                    try {
                        fileToBytes = Files.readAllBytes(myAesFile);
                        HexFormat hex = HexFormat.ofDelimiter(" ");
                        String plaintext = hex.formatHex(fileToBytes);
                        System.out.println("The hex values of our plaintext is: " + plaintext + "\n");
                    } catch (IOException var7) {
                        IOException e = var7;
                        System.out.println(e.getMessage());
                    }

                    keyRound cipher = new keyRound(keyArray);

                    byte[][] initState = xor(keyArray,fileToBytes); // AES INITIAL ROUND
                    // initState must go over 4 operations

                    byte[][] cipherBox=null;
                    for(int i=1;i<=10;i++){ // AES ROUND 1-10
                        initState = Substitution.substitute_table(initState, true);
                        ShiftRows sr = new ShiftRows();
                        initState = sr.shiftingRows(initState);
                        if(i<10){
                            MixColumns mc = new MixColumns();
                            initState = mc.inverseMixingColumns(initState);
                        }
                        initState = addRoundKey(i,cipher.keyArray, initState);
                        if(i==10) {
                            for (int j = 0; j < 4; j++) {
                                for (int k = 0; k < 4; k++) {
                                    System.out.print(String.format("%02X  ", cipherBox[j][k] & 0xFF));
                                }
                                System.out.println();
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.println("System's exiting");
                    run=false;
                    break;
                default:
                    System.out.println("Invalid input, try again");
                    break;
            }
        }
        while(run);
    }
}