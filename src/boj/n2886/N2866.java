package boj.n2886;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class N2866 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        char[][] train = new char[r][c];

        List<int[]> seats = new ArrayList<>();
        List<int[]> people = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < c; j++) {
                train[i][j] = tmp[j];

                if (train[i][j] == 'L') {
                    seats.add(new int[]{i, j});
                } else if (train[i][j] == 'X') {
                    people.add(new int[]{i, j});
                }
            }
        }

        boolean[] personCheck = new boolean[people.size()];
        boolean[] seatCheck = new boolean[seats.size()];

        int[] distance = new int[seats.size()];
        Arrays.fill(distance, Integer.MAX_VALUE);

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (int i = 0; i < seats.size(); i++) {
            int[] seat = seats.get(i);
            for (int j = 0; j < people.size(); j++) {
                int[] person = people.get(j);
                pq.add(new Edge(i, j, getDistance(seat, person)));
            }
        }
        int answer = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            if(personCheck[edge.person]){
                continue;
            }

            if(distance[edge.seat] > edge.distance){
                distance[edge.seat] = edge.distance;
                personCheck[edge.person] = true;
            }else if(distance[edge.seat] == edge.distance){
                personCheck[edge.person] = true;
                if(!seatCheck[edge.seat]){
                    seatCheck[edge.seat] = true;
                    answer++;
                }

            }
        }
        System.out.println(answer);

    }

    static int getDistance(int a, int b, int[] init) {
        return (int) (Math.pow(a - init[0], 2) + Math.pow(b - init[1], 2));
    }

    static int getDistance(int[] seat, int[] person) {
        return (int) (Math.pow(seat[0] - person[0], 2) + Math.pow(seat[1] - person[1], 2));
    }

    static class Edge implements Comparable<Edge> {
        int seat;
        int person;
        int distance;

        Edge(int seat, int person, int distance) {
            this.seat = seat;
            this.person = person;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge o) {
            if (this.distance > o.distance) {
                return 1;
            } else if (this.distance < o.distance) {
                return -1;
            }
            return 0;
        }
    }
}
