public class MixColumns {
    byte[][] mBox = {
        {0x02, 0x03, 0x01, 0x01},
        {0x01, 0x02, 0x03, 0x01},
        {0x01, 0x01, 0x02, 0x03},
        {0x03, 0x01, 0x01, 0x02}
    };

    public static int gfMultiply(byte[][] a, byte[][] b) {
        int p = 0;
        for (int i = 0; i < 8; i++) {
            if ((b & 1) != 0) {
                p ^= a;
            }
            boolean hiBitSet = (a & 0x80) != 0;
            a <<= 1;
            if (hiBitSet) {
                a ^= 0x11B; // AES irreducible polynomial
            }
            b >>= 1;
        }
        return p & 0xFF;
    }

    public byte[][] mixingColumns(byte[][] shiftRowsTable){
        byte[][] temp = new byte[4][4];
        for(int i = 0;i<4;i++){
            for(int j =0; j<4;j++){
                temp[i][j] =
            }
        }
    }
}
