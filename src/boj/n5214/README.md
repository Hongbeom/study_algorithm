## 백준 5214 - [환승](https://www.acmicpc.net/problem/5214)

### 풀이법 

1. 조건에 따라 그래프와 경로를 만들어 bfs.
```JAVA
public static void main(String[] args) throws IOException {
		
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    
    List<Integer>[] hyperTubes = new ArrayList[m];
    List<Integer>[] stations = new ArrayList[n];
    
    
    for(int i = 0; i < m; i++) {
        st = new StringTokenizer(br.readLine());
        hyperTubes[i] = new ArrayList<>();
        for(int j = 0; j < k; j++) {
            int station = Integer.parseInt(st.nextToken()) - 1;
            hyperTubes[i].add(station);
            
            if(stations[station] == null) {
                stations[station] = new ArrayList<>();
            }
            stations[station].add(i);
        }
    }
    
    if(n == 1) {
        System.out.println(1);
        return;
    }
    
    boolean[][] hyperVisit = new boolean[n + 1][m];
    boolean[] visited = new boolean[n];
    
    Queue<int[]> queue = new LinkedList<>();
    visited[0] = true;
    queue.add(new int[] {0, 1});
    int answer = -1;
    
    root:
    while(!queue.isEmpty()) {
        int[] current = queue.poll();
        int s = current[0];
        int c = current[1];
        
        if(stations[s] != null) {
            for(int hyper : stations[s]) {
                
                if(hyperVisit[c][hyper]) {
                    continue;
                }
                hyperVisit[c][hyper] = true;

                for(int next : hyperTubes[hyper]) {
                    
                    if(next == n-1) {
                        answer = c + 1;
                        break root;
                    }
                    
                    if(visited[next]) {
                        continue;
                    }
                    
                    visited[next] = true;
                    queue.add(new int[] {next, c + 1});
                }
            }
        }
    }
    
    System.out.println(answer);
}
```
