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
        this.keyArray=into2DArray(keyArray);
    }

    public byte[][] into2DArray(byte[] key){
        byte[][] key2D = new byte[4][4];
        for(int i=0;i<key.length;i++){
            int col= i/4; //AES is column-major, so first fill the cols
            int row= i%4;
            key2D[row][col]=key[i];
        }
        return key2D;
    }
    /*public List<byte[]> getWord(byte[][] key2D){ // key --> from 2D to 4-Words
        List<byte[]> words = new ArrayList<>();

        for(int c=0;c<key2D[0].length;c++){
            byte[] word = new byte[4];
            for(int r=0;r<key2D.length;r++){
                word[r]=key2D[r][c];
            }
            words.add(word); // each word is stored in List of "words" as 1D-byte arrays
        }
        return words; // it returns the words as list of byte arrays,
        // in order to access directly to the specific word, use "words.get(0,...,3)"
    } */
    public void keySchedule(byte[] key){
        for(int i=0;i<key.length;i++){ // FIRST, key --> from 1D to 2D array
            int col= i/4; //AES is column-major, so first fill the cols
            int row= i%4;
            keyArray[row][col]=key[i];
        }

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
