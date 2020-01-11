package boj.n11053;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class N11053 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] numbers = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }


//        System.out.println(checkN2(numbers));
        System.out.println(checkNlogN(numbers));

    }


    static int checkN2(int[] numbers) {

        int[] memo = new int[numbers.length];
        Arrays.fill(memo, 1);

        int answer = 1;

        for (int i = 1; i < numbers.length; i++) {
            int current = numbers[i];
            int max = memo[i];
            for (int j = 0; j < i; j++) {
                if (current > numbers[j]) {
                    max = Math.max(max, memo[j] + 1);
                }
            }
            memo[i] = max;
            answer = Math.max(answer, max);
        }


        return answer;
    }

    static int checkNlogN(int[] numbers) {

        List<Integer> lis = new ArrayList<>();
        lis.add(numbers[0]);

        for (int i = 1; i < numbers.length; i++) {
            int last = lis.get(lis.size() - 1);
            if (numbers[i] > last) {
                lis.add(numbers[i]);
            } else {
                lis.set(getLowerBound(lis, 0, lis.size() - 1, numbers[i]), numbers[i]);
            }
        }

        return lis.size();
    }


    static int getLowerBound(List<Integer> list, int start, int end, int target) {

        int middle = (start + end) / 2;
        int current = list.get(middle);

        if (target > current) {
            return getLowerBound(list, middle + 1, end, target);
        } else {
            if (middle == 0 || list.get(middle - 1) < target) {
                return middle;
            } else {
                return getLowerBound(list, start, middle, target);
            }
        }
    }
}
