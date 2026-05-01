import java.util.List;
import java.util.ArrayList;
public class keyRound {

    protected byte[][] keyArray= new byte[4][44];
    protected byte[][] Rcon = {
            {(byte)0x01,(byte)0x02,(byte)0x04,(byte)0x08,(byte)0x10,(byte)0x20,(byte)0x40,(byte)0x80,(byte)0x1B,(byte)0x36},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0}
    };
    public keyRound(byte[] keyArray){ // Object takes given key
        keySchedule(keyArray);
    }

    public static byte[][] into2DArray(byte[] key){
        byte[][] arr= new byte[4][4];
        for(int i=0;i<key.length;i++){
            int col= i/4; //AES is column-major, so first fill the cols
            int row= i%4;
            arr[row][col]=key[i];
        }
        return arr;
    }
    public void keySchedule(byte[] key){
        byte[][] init = into2DArray(key); // FIRST, key --> from 1D to 2D array
        for(int i=0;i<init.length;i++){
            for(int j=0;j<init[0].length;j++){
                keyArray[i][j]=init[i][j];
            }
        }
        for(int i=4;i<44;i++){ // SECOND, now generating rest of the columns
            byte[] temp=new byte[4]; // representing Wi-1

            for(int r=0;r<keyArray.length;r++){ // 2.1, determining Wi-1
                temp[r]=keyArray[r][i-1];
            }

            if(i % 4 == 0){
                temp=gFunc(temp, i/4, key); // 2.2, determining Wi-1, if (i mod 4=0)
            }
            for(int r=0;r<keyArray.length;r++){ // THIRD, Wi= Wi-4 XOR temp(Wi-1)
                keyArray[r][i]=(byte)(keyArray[r][i-4] ^ temp[r]);
            }
        }

    }
    public byte[] gFunc(byte[] word, int round, byte[] key){
        byte[] cloneWord = word.clone();
        // FIRST, RotWord
        byte temp = cloneWord[0];
        for(int i=0;i<3;i++){
            cloneWord[i]=cloneWord[i+1];
        }
        cloneWord[3]=temp;

        // SECOND, SubWord
        byte[][] byteKey = new byte[4][4]; // key --> from 1D to 2D
        for(int i=0;i<key.length;i++){
            int col= i/4;
            int row= i%4;
            byteKey[row][col]=key[i];
        }
        int[][] intKey = new int[byteKey.length][byteKey[0].length]; // byte[][]-->int[][]
        for(int i=0;i<byteKey.length;i++){
            for(int j=0; j<byteKey[0].length;j++){
                intKey[i][j]=byteKey[i][j] & 0xFF; // bytes are getting converted to ints
            }
        }
        int[][] SBOX = Substitution.substitute_math(intKey,true);
        byte[] subWord=cloneWord;
        for(int i=0; i< subWord.length;i++){
            int val = cloneWord[i] & 0xFF;
            int row = (val>>4) & 0x0F;
            int col = val & 0x0F;
            subWord[i]=(byte)SBOX[row][col];
        }
        // THIRD, XOR w/ Rcon[round]
        byte[] newWord=new byte[4];
        for(int r=0; r< subWord.length;r++){
            newWord[r]= (byte)(subWord[r] ^ Rcon[r][round]);
        }
        return newWord;
    }




}
