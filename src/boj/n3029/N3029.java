package boj.n3029;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N3029 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String wt = br.readLine();
        String bt = br.readLine();

        if(wt.equals(bt)){
            System.out.println("24:00:00");
            return;
        }

        int[] start = new int[3];
        int[] bomb = new int[3];



        StringTokenizer sst = new StringTokenizer(wt, ":");
        StringTokenizer bst = new StringTokenizer(bt, ":");
        for (int i = 0; i < 3; i++) {
            start[i] = Integer.parseInt(sst.nextToken());
            bomb[i] = Integer.parseInt(bst.nextToken());
        }

        int h = bomb[0] - start[0];
        int m = bomb[1] - start[1];
        int s = bomb[2] - start[2];

        String hour;
        String minute;
        String second;

        if (s < 0) {
            s += 60;
            m--;
        }
        if (m < 0) {
            m += 60;
            h--;
        }
        if (h < 0) {
            h += 24;
        }

        if (s < 10) {
            second = "0" + s;
        } else {
            second = "" + s;
        }

        if (m < 10) {
            minute = "0" + m;
        } else {
            minute = "" + m;
        }

        if (h < 10) {
            hour = "0" + h;
        } else {
            hour = "" + h;
        }



        System.out.println(hour + ":" + minute + ":" + second);


    }
}
