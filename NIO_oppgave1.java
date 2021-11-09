package Victor.Udnaes;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter string");
        int sum = 0;
        int i = 0;
        String str = scanner.nextLine();
        int N = Integer.parseInt(str.substring(0,1));
        int K = Integer.parseInt(str.substring(2,3));
        int X = Integer.parseInt(str.substring(4,6));
        boolean end = false;

        int[] a = new int[N];

        while (!end) {
            System.out.println("enter address");
            String input = scanner.nextLine();
            if (input.equals("Y")) {
                end = true;
            } else {
                int intInput = Integer.parseInt(input);
                a[i] = intInput;
            }
            i++;
        }
        int rest = 0;
        boolean retur = false;
        for (int n = 0; n<a.length; n++) {
            int l = a[n] - X;
            if (n >= K-1 && n!= a.length-1) {
                l*=2;
                retur = true;
            } else if (!retur) {
                l-= rest;
            }
            if (n == a.length-1) {
                sum += a[n] - X;
            }
            rest = a[n] - X;
            sum += Math.sqrt(l*l);
        }
        System.out.println(sum);
    }
}
