package boj.n12945;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class N12945 {

    static int[] BOX;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        BOX = new int[n];
        for (int i = 0; i < n; i++) {
            BOX[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(BOX);
        int answer = n;

        int mid = n / 2;
        int right = mid - 1;

        for (int i = BOX.length - 1; i >= mid; i--) {
            if(BOX[i] / 2 < BOX[0]){
                break;
            }
            int id = bs(0, right, BOX[i] / 2);
            answer--;
            right = id - 1;
            if(right < 0){
                break;
            }
        }
        System.out.println(answer);
    }


    static int bs(int left, int right, int target) {
        if (left == right) {
            return left;
        }
        int mid = (left + right) / 2;
        int current = BOX[mid];

        if (current <= target) {
            if (BOX[mid + 1] > target) {
                return mid;
            }
            return bs(mid + 1, right, target);
        } else {
            return bs(left, mid, target);
        }
    }
}
