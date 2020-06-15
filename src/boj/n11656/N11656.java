package boj.n11656;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class N11656 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] word = br.readLine().toCharArray();
        StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<>();
        for (int i = word.length - 1; i >= 0; i--) {
            sb.append(word[i]);
            list.add(sb.reverse().toString());
            sb.reverse();
        }
        Collections.sort(list);
        for(String s : list){
            System.out.println(s);
        }

    }
}
