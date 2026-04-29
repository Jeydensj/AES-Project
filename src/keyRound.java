import java.util.List;
import java.util.ArrayList;
public class keyRound {

    byte[][] keyArray= new byte[4][44];
    byte[][] Rcon = {
            {(byte)0x01,(byte)0x02,(byte)0x04,(byte)0x08,(byte)0x10,(byte)0x20,(byte)0x40,(byte)0x80,(byte)0x1B,(byte)0x36},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0}
    };
    public keyRound(byte[] keyArray){ // Object takes given key
        keySchedule(keyArray);
    }

    public void into2DArray(byte[] key){
        for(int i=0;i<key.length;i++){
            int col= i/4; //AES is column-major, so first fill the cols
            int row= i%4;
            keyArray[row][col]=key[i];
        }
    }
    public void keySchedule(byte[] key){
        into2DArray(key); // FIRST, key --> from 1D to 2D array

        for(int i=4;i<44;i++){ // SECOND, now generating rest of the columns
            byte[] temp=new byte[4]; // representing Wi-1

            for(int r=0;r<keyArray.length;r++){ // 2.1, determining Wi-1
                temp[r]=keyArray[r][i-1];
            }

            if(i % 4 == 0){
                temp=gFunc(temp, i/4); // 2.2, WILL WORK ON IT
            }
            for(int r=0;r<keyArray.length;r++){ // THIRD, Wi= Wi-4 XOR temp(Wi-1)
                keyArray[r][i]=(byte)(keyArray[r][i-4] ^ temp[r]);
            }
        }

    }
    public byte[] gFunc(byte[] word, int round){
        byte[] cloneWord = word.clone();
        // FIRST, RotWord
        byte temp = cloneWord[0];
        for(int i=0;i<3;i++){
            cloneWord[i]=cloneWord[i+1];
        }
        cloneWord[3]=temp;

        // SECOND, SubWord
        byte[] subWord=cloneWord;
        // THIRD, XOR w/ Rcon[round]
        byte[] newWord=new byte[4];
        for(int r=0; r< subWord.length;r++){
            newWord[r]= (byte)(subWord[r] ^ Rcon[r][round]);
        }
        return newWord;
    }




}
