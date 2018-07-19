package codeforce.CodeforcesRound498Div3;


import java.util.Scanner;

public class AAdjacentReplacements {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int x;
        for (int i = 0; i < n; i++) {
            x = scan.nextInt();
            if ((x & 1) == 0) {
                System.out.print(x - 1 + " ");
            } else {
                System.out.print(x + " ");
            }
        }
    }

}
