
public class ShiftRows {
    public byte[][] shiftingRows(byte[][] sBoxTable) {
        byte[][] temp = new byte[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                // modding by 4 helps with wrapping around and the first row is left alone
                temp[i][j] = sBoxTable[i][(j + i) % 4];
            }
        }
        return temp;
    }

    public byte[][] inverseShiftingRows(byte[][] sBoxTable) {
        byte[][] temp = new byte[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                temp[i][j] = sBoxTable[i][(j - i) % 4];
            }
        }
        return temp;
    }
}
