package boj.n16637;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class N16637 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer> numbers = new ArrayList<>();
        List<Character> op = new ArrayList<>();
        char[] formula = br.readLine().toCharArray();

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                numbers.add(formula[i] - '0');
            } else {
                op.add(formula[i]);
            }
        }

        List<boolean[]> candidates = new ArrayList<>();

        for (int i = 1; i <= numbers.size() / 2; i++) {
            getCandidates(candidates, new boolean[op.size()], 0, 0, i);
        }
        candidates.add(0, new boolean[op.size()]);

        int answer = Integer.MIN_VALUE;

        for (boolean[] can : candidates) {
            answer = Math.max(answer, calculate(numbers, op, can));
        }

        System.out.println(answer);


    }

    static int calculate(List<Integer> inNums, List<Character> inOps, boolean[] bracket) {

        List<Integer> numbers = new ArrayList<>(inNums);
        List<Character> op = new ArrayList<>(inOps);

        for (int i = bracket.length - 1; i >= 0; i--) {
            if (bracket[i]) {
                int left = i;
                int right = i + 1;
                int value = operation(numbers.get(left), numbers.get(right), op.get(i));
                numbers.set(left, value);
                numbers.remove(right);
                op.remove(i);
            }
        }

        int ret = numbers.get(0);

        for (int i = 1; i < numbers.size(); i++) {

            ret = operation(ret, numbers.get(i), op.get(i - 1));
        }

        return ret;
    }

    static int operation(int left, int right, char op) {

        switch (op) {
            case '+':
                return left + right;
            case '-':
                return left - right;
            case '*':
                return left * right;
        }
        return -1;

    }

    static void getCandidates(List<boolean[]> list, boolean[] tm, int start, int count, int target) {

        if (count == target) {
            list.add(tm);
            return;
        }

        if (start >= tm.length) {
            return;
        }


        getCandidates(list, tm.clone(), start + 1, count, target);
        tm[start] = true;
        getCandidates(list, tm.clone(), start + 2, count + 1, target);

    }
}
