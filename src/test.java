import java.util.Scanner;

public class test{
    public static void main(String[] args) {
        ShiftRows sr = new ShiftRows();
        MixColumns mc = new MixColumns();
        Scanner sc = new Scanner(System.in);
        byte[][] test1 = {
                {0x00, 0x00, 0x01, 0x01},
                {0x03, 0x03, 0x07, 0x07},
                {0x0f, 0x0f, 0x1f, 0x1f},
                {0x3f, 0x3f, 0x7f, 0x7f}
        };

        byte[][] output1 = sr.shiftingRows(test1);
        byte[][] output2 = mc.mixingColumns(test1);

        for (byte[] row : output1) {
            for (int b : row) {
                System.out.print(Integer.toHexString(b) + ", ");
            }
            System.out.println();
        }
        System.out.println();
        for (byte[] row : output2) {
            for (int b : row) {
                System.out.print(Integer.toHexString(b) + ", ");
            }
            System.out.println();
        }
    }
}