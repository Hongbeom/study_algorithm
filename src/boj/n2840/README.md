## 백준 2840 - [행운의 바퀴](https://www.acmicpc.net/problem/2840)

### 풀이법

1. 시계방향으로 돌아가니, 배열의 뒤로 이동하면서 각 칸을 채워줌.
2. 바퀴에 한 문자씩만 들어가므로 visited 이용 판별
3. 바퀴에 해당하는 칸에 다른 문자가 존재하면 이상한 바퀴

~~~JAVA
for (int i = 0; i < k; i++) {
    st = new StringTokenizer(br.readLine());
    int r = (Integer.parseInt(st.nextToken())) % n;
    char c = st.nextToken().charAt(0);

    current = (current + (n - r)) % n;

    if (circle[current] != '?' && circle[current] != c) {
        System.out.println("!");
        return;
    }


    if (circle[current] == '?' && visited[(int) c - 65]) {
        System.out.println("!");
        return;
    }

    visited[(int) c - 65] = true;
    circle[current] = c;
}
~~~
