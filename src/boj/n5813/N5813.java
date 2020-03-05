package boj.n5813;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class N5813 {

    static long answer = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] spot = new int[n][2];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            spot[i][0] = Integer.parseInt(st.nextToken());
            spot[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(spot, (o1, o2) -> {
            if (o1[0] > o2[0]) {
                return 1;
            } else if (o1[0] < o2[0]) {
                return -1;
            } else {
                if (o1[1] > o2[1]) {
                    return 1;
                } else if (o1[1] < o2[1]) {
                    return -1;
                }
            }
            return 0;
        });



        int compare = -1;
        int cid = -1;

        List<int[]> ranges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (spot[i][0] != compare) {
                compare = spot[i][0];
                if (i != 0) {
                    ranges.add(new int[]{cid, i - 1});
                }
                cid = i;
            }
            if (i == n - 1) {
                ranges.add(new int[]{cid, i});
            }
        }

        int tid = 0;
        int lastStart = 0;
        int lastEnd = 0;


        List<Integer>[] graph = new ArrayList[n];
        List<Node> nodeList = new ArrayList<>();
        for (int[] range : ranges) {
            int w = 0;
            int size = nodeList.size();
            int count = 0;

            for (int i = range[0]; i <= range[1]; i++) {

                int[] s = spot[i];


                if (range[0] == range[1]) {
                    Node node = new Node(tid, s[1], s[1], 1);
                    nodeList.add(node);
                    for (int id = lastStart; id < lastEnd; id++) {
                        if (nodeList.get(id).check(node)) {
                            if (graph[id] == null) {
                                graph[id] = new ArrayList<>();
                            }
                            if (graph[tid] == null) {
                                graph[tid] = new ArrayList<>();
                            }
                            graph[id].add(tid);
                            graph[tid].add(id);
                        }
                    }
                    tid++;
                    break;
                }


                if (i == range[0]) {
                    w = s[1];
                    count = 1;
                } else {

                    if (s[1] - w == 1) {
                        count++;
                    } else {
                        Node node = new Node(tid, w - count + 1, w, count);
                        nodeList.add(node);
                        for (int id = lastStart; id < lastEnd; id++) {
                            if (nodeList.get(id).check(node)) {

                                if (graph[id] == null) {
                                    graph[id] = new ArrayList<>();
                                }
                                if (graph[tid] == null) {
                                    graph[tid] = new ArrayList<>();
                                }
                                graph[id].add(tid);
                                graph[tid].add(id);
                            }
                        }
                        tid++;
                        count = 1;
                    }

                    w = s[1];

                    if (range[1] == i) {

                        Node node = new Node(tid, w - count + 1, w, count);
                        for (int id = lastStart; id < lastEnd; id++) {
                            if (nodeList.get(id).check(node)) {

                                if (graph[id] == null) {
                                    graph[id] = new ArrayList<>();
                                }
                                if (graph[tid] == null) {
                                    graph[tid] = new ArrayList<>();
                                }

                                graph[id].add(tid);
                                graph[tid].add(id);
                            }

                        }
                        nodeList.add(node);
                        tid++;
                        count = 1;

                    }
                }
            }
            lastStart = tid - (nodeList.size() - size);
            lastEnd = tid;
        }

        dfs(nodeList, graph, 0, n, new boolean[n]);

        Arrays.sort(spot, (o1, o2) -> {
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
        });

        compare = -1;
        cid = -1;

        ranges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (spot[i][1] != compare) {
                compare = spot[i][1];
                if (i != 0) {
                    ranges.add(new int[]{cid, i - 1});
                }
                cid = i;
            }
            if (i == n - 1) {
                ranges.add(new int[]{cid, i});
            }
        }

        tid = 0;
        lastStart = 0;
        lastEnd = 0;

        graph = new ArrayList[n];
        nodeList = new ArrayList<>();
        for (int[] range : ranges) {
            int h = 0;
            int size = nodeList.size();
            int count = 0;

            for (int i = range[0]; i <= range[1]; i++) {

                int[] s = spot[i];

                if (range[0] == range[1]) {
                    Node node = new Node(tid, s[0], s[0], 1);
                    nodeList.add(node);
                    for (int id = lastStart; id < lastEnd; id++) {
                        if (nodeList.get(id).check(node)) {
                            if (graph[id] == null) {
                                graph[id] = new ArrayList<>();
                            }
                            if (graph[tid] == null) {
                                graph[tid] = new ArrayList<>();
                            }
                            graph[id].add(tid);
                            graph[tid].add(id);
                        }
                    }
                    tid++;
                    break;
                }

                if (i == range[0]) {
                    h = s[0];
                    count = 1;
                } else {

                    if (s[0] - h == 1) {
                        count++;
                    } else {
                        Node node = new Node(tid, h - count + 1, h, count);

                        for (int id = lastStart; id < lastEnd; id++) {
                            if (nodeList.get(id).check(node)) {
                                if (graph[id] == null) {
                                    graph[id] = new ArrayList<>();
                                }
                                if (graph[tid] == null) {
                                    graph[tid] = new ArrayList<>();
                                }
                                graph[id].add(tid);
                                graph[tid].add(id);
                            }
                        }

                        nodeList.add(node);
                        tid++;
                        count = 1;
                    }
                    h = s[0];

                    if (range[1] == i) {

                        Node node = new Node(tid, h - count + 1, h, count);
                        for (int id = lastStart; id < lastEnd; id++) {
                            if (nodeList.get(id).check(node)) {

                                if (graph[id] == null) {
                                    graph[id] = new ArrayList<>();
                                }
                                if (graph[tid] == null) {
                                    graph[tid] = new ArrayList<>();
                                }

                                graph[id].add(tid);
                                graph[tid].add(id);
                            }

                        }
                        nodeList.add(node);
                        tid++;
                        count = 1;

                    }
                }
            }
            lastStart = tid - (nodeList.size() - size);
            lastEnd = tid;
        }

        dfs(nodeList, graph, 0, n, new boolean[n]);
        System.out.println(answer);

    }


    static class Node {
        int id;
        int from;
        int to;
        long count;

        public Node(int id, int from, int to, long count) {
            this.id = id;
            this.from = from;
            this.to = to;
            this.count = count;
        }

        boolean check(Node node) {
            return this.from <= node.to && node.from <= this.to;
        }
    }


    static long dfs(List<Node> nodeList, List<Integer>[] graph, int current, int n, boolean[] visited) {

        visited[current] = true;

        int countSum = 0;
        if (graph[current] != null) {

            for (int next : graph[current]) {

                if (visited[next]) {
                    continue;
                }

                long a = dfs(nodeList, graph, next, n, visited);
                answer =  (answer + ((a * (n - a))% 1000000000)) % 1000000000;
                countSum += a;

            }

        }

        if (countSum == 0) {
            return nodeList.get(current).count;
        } else {
            return countSum + nodeList.get(current).count;
        }


    }


}
