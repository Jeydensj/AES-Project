
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        int[][] test1 = {
                {0x00, 0x00, 0x01, 0x01},
                {0x03, 0x03, 0x07, 0x07},
                {0x0f, 0x0f, 0x1f, 0x1f},
                {0x3f, 0x3f, 0x7f, 0x7f}
        };
        int[][] output1 = Substitution.substitute_math(test1, true);
        int[][] output2 = Substitution.substitute_table(test1, true);

        for (int[] row : output1) {
            for (int b : row) {
                System.out.print(Integer.toHexString(b) + ", ");
            }
            System.out.println();
        }
        System.out.println();
        for (int[] row : output2) {
            for (int b : row) {
                System.out.print(Integer.toHexString(b) + ", ");
            }
            System.out.println();
        }
    }
}