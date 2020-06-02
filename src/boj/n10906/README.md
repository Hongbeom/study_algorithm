## 백준 10906 - [N-orthotope](https://www.acmicpc.net/problem/10906)

### 풀이법

1. 차원에 대해 물어보는 문제.
  - 각 차원의 범위가 주어진다고 생각.
  - 각 차원에서 안겹치는 차원이 하나라도 존재할 시 -1.
  - 각 차원에서 2개이상 겹치는 차원의 갯수가 정답.

~~~JAVA
public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());

    int[][] first = new int[n][2];
    int[][] second = new int[n][2];

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < first.length; i++) {
        first[i][0] = Integer.parseInt(st.nextToken());
        first[i][1] = Integer.parseInt(st.nextToken());

    }
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < second.length; i++) {
        second[i][0] = Integer.parseInt(st.nextToken());
        second[i][1] = Integer.parseInt(st.nextToken());
    }

    int fullCnt = 0;
    for (int i = 0; i < n; i++) {
        int common = getCommon(first[i][0], first[i][1], second[i][0], second[i][1]);

        if (common == -1) {
            System.out.println(-1);
            return;
        } else if (common > 0) {
            fullCnt++;
        }
    }

    System.out.println(fullCnt);

}

static int getCommon(int a, int b, int c, int d) {

    if (d < b) {
        int tmpA = a;
        a = c;
        b = d;
        c = tmpA;
    }

    if (c > b) {
        return -1;
    } else if (c <= a) {
        return b - a;
    } else {
        return b - c;
    }
}
~~~

