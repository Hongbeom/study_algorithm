package boj.n2033;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N2033 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double n = Double.parseDouble(br.readLine());


        int exp = 1;
        double compare = Math.pow(10, exp);

        while (n > compare) {
            n = Math.round(n / compare) * compare;
            exp++;
            compare = Math.pow(10, exp);
        }

        System.out.println((long) n);
    }
}
