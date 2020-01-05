package boj.n1449;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N1449 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int[] pipe = new int[n];

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<n; i++){
            pipe[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(pipe);

        int answer = 0;
        int current = 0;
        for(int p : pipe){
            if(current == 0){
                answer++;
                current = p + l - 1;
            }else{
                if(current < p){
                    answer++;
                    current = p + l - 1;
                }
            }
        }

        System.out.println(answer);
    }
}
