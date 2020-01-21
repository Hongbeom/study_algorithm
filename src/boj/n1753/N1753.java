package boj.n1753;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class N1753 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        int start = Integer.parseInt(br.readLine()) - 1;

        List<int[]>[] edges = new ArrayList[v];

        for (int i = 0; i < v; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());

            edges[from].add(new int[]{to, weight});
        }

        int[] distance = dijkstra(edges, start);

        for(int d : distance){
            if(d == Integer.MAX_VALUE){
                System.out.println("INF");
            }else{
                System.out.println(d);
            }
        }


    }


    static int[] dijkstra(List<int[]>[] edges, int start) {
        int[] distance = new int[edges.length];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] > o2[1]) {
                return 1;
            } else if (o1[1] < o2[1]) {
                return -1;
            }
            return 0;
        });

        pq.add(new int[]{start, 0});

        while(!pq.isEmpty()){
            int[] current = pq.poll();

            int node = current[0];
            int d = current[1];

            if(distance[node] < d){
                continue;
            }

            for(int[] edge : edges[node]){
                int next = edge[0];
                int nextDist = edge[1] + d;

                if(nextDist < distance[next]){
                    distance[next] = nextDist;
                    pq.add(new int[]{next, nextDist});
                }
            }
        }

        return distance;
    }
}
