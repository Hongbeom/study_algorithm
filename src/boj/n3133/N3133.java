package boj.n3133;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class N3133 {

    static long[] count;
    static int[][] plants;
    static List<List<DP>> memo;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        memo = new ArrayList<>();
        memo.add(new ArrayList<>());
        count = new long[n];
        plants = new int[n][2];
        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            plants[i][0] = Integer.parseInt(st.nextToken());
            plants[i][1] = Integer.parseInt(st.nextToken());
        }

        // x에 대해 오름차순, y에 대해 내림차순으로 정렬
        Arrays.sort(plants, (o1, o2) -> {
            if (o1[0] > o2[0]) {
                return 1;
            } else if (o1[0] < o2[0]) {
                return -1;
            } else {
                if (o1[1] > o2[1]) {
                    return -1;
                } else if (o1[1] < o2[1]) {
                    return 1;
                }
            }
            return 0;
        });

        int compare = -1;
        int cid = -1;

        // x 에 대해 range 를 생성, 배열 접근 순서는 range의 반대로 접근.
        List<int[]> ranges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (plants[i][0] != compare) {
                compare = plants[i][0];
                if (i != 0) {
                    ranges.add(new int[]{cid, i - 1});
                }
                cid = i;
            }
            if (i == n - 1) {
                ranges.add(new int[]{cid, i});
            }
        }

        int[] firstRange = ranges.get(0);
        int zid = 0;
        List<DP> zeroList = memo.get(0);
        for (int i = firstRange[0]; i <= firstRange[1]; i++) {
            count[i] = 1;
            if (zid == 0) {
                zeroList.add(new DP(i, count[i]));
            } else {
                zeroList.add(new DP(i, (zeroList.get(zid - 1).cv + count[i]) % 1000000007));
            }
            zid++;
        }

        for (int i = 1; i < ranges.size(); i++) {
            int[] range = ranges.get(i);

            for (int id = range[0]; id <= range[1]; id++) {
                // memo(list)의 끝값의 y 좌표료 binary search 를 수행

                int jump = bs(0, memo.size() - 1, plants[id][1]);

                // 새롭게 max값을 갱신하는 경우.
                if (jump > memo.size() - 1) {
                    memo.add(new ArrayList<>());
                }

                List<DP> current = memo.get(jump);
                long add = 0;

                if (current.size() != 0) {
                    add = current.get(current.size() - 1).cv;
                }

                if (jump == 0) {
                    count[id] = 1;
                } else {
                    count[id] = count[id] + bsCount(jump - 1, 0, memo.get(jump - 1).size(), plants[id][1]);
                }

                current.add(new DP(id, (count[id] + add) % 1000000007));


            }
        }

        System.out.println(memo.size());
        long cnt = 0;
        for (DP dp : memo.get(memo.size() - 1)) {
            cnt = (cnt + count[dp.index]) % 1000000007;
        }
        System.out.println(cnt);

    }

    static long bsCount(int jump, int left, int right, int target) {

        if (getY(jump, 0) < target) {
            return memo.get(jump).get(memo.get(jump).size() - 1).cv;
        }

        if (getY(jump, memo.get(jump).size() - 1) >= target) {
            return 0;
        }

        int mid = (left + right) / 2;
        int compare = getY(jump, mid);

        if (compare >= target) {
            if (mid == memo.get(jump).size() - 1) {
                return 0;
            } else if (getY(jump, mid + 1) < target) {

                long re = memo.get(jump).get(memo.get(jump).size() - 1).cv
                        - memo.get(jump).get(mid).cv;

                if (re < 0) {
                    re += 1000000007;
                }

                return re;
            }
            return bsCount(jump, mid + 1, right, target);
        } else {
            return bsCount(jump, left, mid, target);
        }


    }


    static int bs(int left, int right, int target) {

        if (getEndY(memo.size() - 1) < target) {
            return memo.size();
        }

        if (getEndY(0) >= target) {
            return 0;
        }

        int mid = (left + right) / 2;
        int compare = getEndY(mid);
        // find upper bound
        if (compare >= target) {
            if (mid == 0 || target > getEndY(mid - 1)) {
                return mid;
            }
            return bs(left, mid, target);
        } else {
            return bs(mid + 1, right, target);
        }


    }

    static int getY(int jump, int id) {
        List<DP> list = memo.get(jump);
        return plants[list.get(id).index][1];
    }


    static int getEndY(int id) {
        List<DP> list = memo.get(id);
        return plants[list.get(list.size() - 1).index][1];
    }

    static class DP {
        int index;
        long cv;

        public DP(int index, long cv) {
            this.index = index;
            this.cv = cv;
        }
    }

}
