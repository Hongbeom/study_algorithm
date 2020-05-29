package boj.n17140;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class N17140 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;
        int k = Integer.parseInt(st.nextToken());

        int[][] matrix = new int[3][3];

        for (int i = 0; i < matrix.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;

        while (time <= 100) {


            if (matrix.length > r && matrix[0].length > c && matrix[r][c] == k) {
                break;
            }

            if (time == 100) {
                time = -1;
                break;
            }

            if (matrix.length >= matrix[0].length) {
                matrix = rCal(matrix);
            } else {
                matrix = cCal(matrix);
            }

            time++;
        }

        System.out.println(time);
    }


    static int[][] cCal(int[][] matrix) {
        List<Integer>[] list = new ArrayList[matrix[0].length];
        int h = -1;

        for (int j = 0; j < matrix[0].length; j++) {
            List<int[]> tmpList = new ArrayList<>();
            HashMap<Integer, Integer> map = new HashMap<>();

            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                map.put(matrix[i][j], map.getOrDefault(matrix[i][j], 0) + 1);
            }
            for (int key : map.keySet()) {
                tmpList.add(new int[]{key, map.get(key)});
            }
            tmpList.sort(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if (o1[1] > o2[1]) {
                        return 1;
                    } else if (o1[1] < o2[1]) {
                        return -1;
                    } else {
                        if (o1[0] > o2[0]) {
                            return 1;
                        } else if (o1[0] < o2[0]) {
                            return -1;
                        }
                    }
                    return 0;
                }
            });
            list[j] = new ArrayList<>();
            for (int[] tmp : tmpList) {
                list[j].add(tmp[0]);
                list[j].add(tmp[1]);
            }
            h = Math.max(h, list[j].size());
        }

        int[][] newMatrix = new int[Math.min(h, 100)][matrix[0].length];

        for (int j = 0; j < newMatrix[0].length; j++) {
            for (int i = 0; i < Math.min(list[j].size(), 100); i++) {
                newMatrix[i][j] = list[j].get(i);
            }
        }

        return newMatrix;

    }

    static int[][] rCal(int[][] matrix) {
        List<Integer>[] list = new ArrayList[matrix.length];
        int w = -1;
        for (int i = 0; i < matrix.length; i++) {
            List<int[]> tmpList = new ArrayList<>();
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                map.put(matrix[i][j], map.getOrDefault(matrix[i][j], 0) + 1);
            }
            for (int key : map.keySet()) {
                tmpList.add(new int[]{key, map.get(key)});
            }
            tmpList.sort(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if (o1[1] > o2[1]) {
                        return 1;
                    } else if (o1[1] < o2[1]) {
                        return -1;
                    } else {
                        if (o1[0] > o2[0]) {
                            return 1;
                        } else if (o1[0] < o2[0]) {
                            return -1;
                        }
                    }
                    return 0;
                }
            });

            list[i] = new ArrayList<>();
            for (int[] tmp : tmpList) {
                list[i].add(tmp[0]);
                list[i].add(tmp[1]);
            }
            w = Math.max(w, list[i].size());
        }

        int[][] newMatrix = new int[matrix.length][Math.min(w, 100)];


        for (int i = 0; i < newMatrix.length; i++) {
            for (int j = 0; j < Math.min(100, list[i].size()); j++) {
                newMatrix[i][j] = list[i].get(j);
            }
        }

        return newMatrix;
    }
}
