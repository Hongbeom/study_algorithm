## 백준 14670 - [병약한 영정](https://www.acmicpc.net/problem/14670)


### 풀이법

1. 배열을 이용하여 약과 증상을 표시.
```JAVA
int[] drug = new int[101];
Arrays.fill(drug, -1);
for (int i = 0; i < n; i++) {
    st = new StringTokenizer(br.readLine());
    int effect = Integer.parseInt(st.nextToken());
    int name = Integer.parseInt(st.nextToken());
    drug[effect] = name;
}

int r = Integer.parseInt(br.readLine());
effects:
for (int i = 0; i < r; i++) {
    StringBuilder sb = new StringBuilder();
    st = new StringTokenizer(br.readLine());
    int l = Integer.parseInt(st.nextToken());
    for (int j = 0; j < l; j++) {
        int effect = Integer.parseInt(st.nextToken());
        if (drug[effect] == -1) {
            System.out.println("YOU DIED");
            continue effects;
        } else {
            sb.append(drug[effect]);
            sb.append(" ");
        }
    }
    System.out.println(sb.toString());
}
```