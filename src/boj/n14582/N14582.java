package boj.n14582;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N14582 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer score1 = new StringTokenizer(br.readLine());
        StringTokenizer score2 = new StringTokenizer(br.readLine());

        int t1 = 0;
        int t2 = 0;

        for(int i=0; i<9; i++){
            t1 += Integer.parseInt(score1.nextToken());

            if(t1 > t2){
                System.out.println("Yes");
                return;
            }

            t2 += Integer.parseInt(score2.nextToken());
        }

        System.out.println("No");

    }
}
