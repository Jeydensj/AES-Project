public class MixColumns {
    byte[][] mBox = {
        {0x02, 0x03, 0x01, 0x01},
        {0x01, 0x02, 0x03, 0x01},
        {0x01, 0x01, 0x02, 0x03},
        {0x03, 0x01, 0x01, 0x02}
    };

    byte [][] inverseMBox={
            {0x0E, 0x0B, 0x0D, 0x09},
            {0x09, 0x0E, 0x0B, 0x0D},
            {0x0D, 0x09, 0x0E, 0x0B},
            {0x0B, 0x0D, 0x09, 0x0E}
    };

    private byte gfMultiply(byte a, byte b) {
        int p = 0;
        int a_int = a & 0xFF; // Treat as unsigned
        int b_int = b & 0xFF; // Treat as unsigned

        for (int i = 0; i < 8; i++) {
            if ((b_int & 1) != 0) {
                p ^= a_int;
            }
            boolean hiBitSet = (a_int & 0x80) != 0;
            a_int <<= 1;
            if (hiBitSet) {
                a_int ^= 0x1B; // The AES polynomial
            }
            b_int >>= 1;
        }
        return (byte) (p & 0xFF);
    }

    public byte[][] mixingColumns(byte[][] shiftRowsTable){
        byte[][] sPrimeBox = new byte[4][4];
        int SRTrow = 4;
        int mBoxRow = 4;
        int mBoxCol = 4;
        for(int i = 0;i<SRTrow;i++){
            for(int j =0; j<mBoxCol;j++){
                byte num = 0;
                for (int k = 0; k<mBoxRow;k++) {
                    num ^= gfMultiply(mBox[j][k], shiftRowsTable[k][i]);
                }
                sPrimeBox[i][j] = num;
            }
        }
        return sPrimeBox;
    }

    public byte[][] inverseMixingColumns(byte[][] shiftRowsTable){
        byte[][] inverseSPrimeBox = new byte[4][4];
        for(int i = 0;i<4;i++){
            for(int j =0; j<4;j++){
                byte num = 0;
                for (int k = 0; k<4;k++) {
                    num ^= gfMultiply(inverseMBox[j][k], shiftRowsTable[k][i]);
                }
                inverseSPrimeBox[i][j] = num;
            }
        }
        return inverseSPrimeBox;
    }

}
