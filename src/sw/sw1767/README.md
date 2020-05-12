## SW Academy - [프로세서 연결하기](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV4suNtaXFEDFAUf)

### 풀이법

1. 상, 하, 좌, 우, 연결로 모두 탐색.

```JAVA
static final int[] H = new int[] {-1, 1, 0, 0};
static final int[] W = new int[] {0, 0, -1, 1};

static int MAX_ACTIVATION = 0;
static int MIN_LENGTH = Integer.MAX_VALUE;


public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int TC = Integer.parseInt(br.readLine());
    StringTokenizer st;
    for(int tc = 1; tc <= TC; tc++) {
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        
        List<int[]> cores = new ArrayList<>();			
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(!(i == 0 || j == 0 || i == n-1 || j == n-1) && map[i][j] == 1) {
                    cores.add(new int[] {i,j});
                }
            }			
        }
        
        dfs(cores, map, 0, 0, 0);
        
        if(MIN_LENGTH == Integer.MAX_VALUE) {
            ans(tc, 0);
        }else {
            ans(tc, MIN_LENGTH);
        }
        refresh();
        
    }
    
}


static void ans(int tc, int answer) {
    System.out.println("#" + tc + " " + answer);
}

static void refresh() {
    MAX_ACTIVATION = 0;
    MIN_LENGTH = Integer.MAX_VALUE;
}

static void dfs(List<int[]> cores, int[][] map, int length, int activation, int step) {
    
    if(step == cores.size()) {
        if(activation > MAX_ACTIVATION) {
            MAX_ACTIVATION = activation;
            MIN_LENGTH = length;
        }else if(activation == MAX_ACTIVATION) {
            MIN_LENGTH = Math.min(MIN_LENGTH, length);
        }
        return;
    }
    
    
    int[] current = cores.get(step);
    
    int h = current[0];
    int w = current[1];
    
    for(int d = 0; d< 4; d++) {
        Result result = check(clone(map), h, w, d);
        if(result != null) {
            dfs(cores, result.map, length + result.length, activation + 1, step + 1);
        }
    }
    dfs(cores, map, length, activation, step + 1);
    
}

static int[][] clone(int[][] src){
    int[][] dest = new int[src.length][src[0].length];
    
    for(int i=0; i<src.length; i++) {
        System.arraycopy(src[i], 0, dest[i], 0, src[i].length);
    }
    
    
    return dest;
}

static Result check(int[][] map, int h, int w, int d) {
    
    h += H[d];
    w += W[d];
    int length = 0;
    while(h >= 0 && w >=0 && h < map.length && w < map[0].length) {
        if(map[h][w] != 0) {
            return null;
        }else {
            map[h][w] = 1;
        }
        length++;
        h += H[d];
        w += W[d];
    }
    
    return new Result(map, length);
}

static class Result{
    int[][] map;
    int length;
    
    Result(int[][] map, int length){
        this.map = map;
        this.length = length;
    }
}
```