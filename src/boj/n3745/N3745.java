package boj.n3745;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class N3745 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String in;
        StringTokenizer st;
        while ((in = br.readLine()) != null) {

            int n = Integer.parseInt(in.trim());
            st = new StringTokenizer(br.readLine().trim());
            int[] price = new int[n];
            List<Integer> lis = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                price[i] = Integer.parseInt(st.nextToken());
                if (i == 0) {
                    lis.add(price[i]);
                } else {
                    if (lis.get(lis.size() - 1) < price[i]) {
                        lis.add(price[i]);
                    } else {
                        int id = getLowerBound(lis, 0, lis.size() - 1, price[i]);
                        lis.set(id, price[i]);
                    }

                }
            }
            System.out.println(lis.size());
        }
    }


    static int getLowerBound(List<Integer> list, int start, int end, int key) {

        int mid = (start + end) / 2;
        int current = list.get(mid);

        if (current >= key) {
            if (mid == 0 || list.get(mid - 1) < key) {
                return mid;
            }
            return getLowerBound(list, start, mid, key);
        } else {
            return getLowerBound(list, mid + 1, end, key);
        }
    }

}
