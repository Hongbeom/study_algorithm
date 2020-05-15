## SW Academy 4012 - [요리사](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeUtVakTMDFAVH&categoryId=AWIeUtVakTMDFAVH&categoryType=CODE#none)

### 풀이법

1. n가지중에서 n/2 를 뽑는 조합을 생성.
2. 케이스마다 모두 값을 구해줘서 찾는다.

```JAVA
public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int TC = Integer.parseInt(br.readLine());
    StringTokenizer st;

    for(int tc = 1; tc <= TC; tc++) {
        int n = Integer.parseInt(br.readLine());

        int[][] synergy = new int[n][n];
        for(int i = 0; i < synergy.length ; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < synergy[0].length; j++) {
                synergy[i][j] = Integer.parseInt(st.nextToken());
            }

        }

        List<int[]> candidates = new ArrayList<>();
        createCase(candidates, new int[n / 2], 0, new boolean[n]);
        int answer = Integer.MAX_VALUE;
        for(int i = 0; i < candidates.size() / 2; i++) {
            int s1 = 0;
            for(int a : candidates.get(i)) {
                for(int b : candidates.get(i)) {
                    if(a == b) {
                        continue;
                    }
                    s1 += synergy[a][b];
                }
            }
            int s2 = 0;
            for(int a : candidates.get(candidates.size() - 1 - i)) {
                for(int b : candidates.get(candidates.size() - 1 - i)) {
                    if(a == b) {
                        continue;
                    }
                    s2 += synergy[a][b];
                }
            }

            answer = Math.min(answer, Math.abs(s1 - s2));

        }

        System.out.println("#" + tc + " " + answer);
    }

}

static void createCase(List<int[]> candidates, int[] tm, int step, boolean[] check) {


    if(step == tm.length) {
        candidates.add(tm);
        return;
    }
    int start;

    if(step == 0) {
        start = 0;
    }else {
        start = tm[step - 1] + 1;
    }

    for(int i = start; i < check.length; i++) {
        if(!check[i]) {
            check[i] = true;
            tm[step] = i;
            createCase(candidates, tm.clone(), step + 1, check.clone());
            check[i] = false;
        }
    }

}
```