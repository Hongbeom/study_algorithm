## SW Academy 5658 - [보물상자 비밀번호](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRUN9KfZ8DFAUo&categoryId=AWXRUN9KfZ8DFAUo&categoryType=CODE#none)

### 풀이법

1. HashMap을 이용하여 중복체크. 모든 케이스 계산 후 정렬하고 k번째 큰 값을 뽑는다.

```JAVA
public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int TC = Integer.parseInt(br.readLine());
    StringTokenizer st;

    for (int tc = 1; tc <= TC; tc++) {
        st = new StringTokenizer(br.readLine());

        HashMap<Integer, Integer> map = new HashMap<>();

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] lock = new int[n];
        char[] lock16 = br.readLine().toCharArray();

        for (int i = 0; i < n; i++) {
            if (lock16[i] == 'A') {
                lock[i] = 10;
            } else if (lock16[i] == 'B') {
                lock[i] = 11;
            } else if (lock16[i] == 'C') {
                lock[i] = 12;
            } else if (lock16[i] == 'D') {
                lock[i] = 13;
            } else if (lock16[i] == 'E') {
                lock[i] = 14;
            } else if (lock16[i] == 'F') {
                lock[i] = 15;
            } else {
                lock[i] = lock16[i] - '0';
            }
        }

        List<Integer> candidates = new ArrayList<>();
        int r = n / 4;
        int sid = 0;
        for (int i = 0; i < r; i++) {
            int value = 0;
            int id = sid;
            int check = 0;
            for (int j = 0; j < n; j++) {
                value += Math.pow(16, r - 1 - check) * lock[id];
                id = (id + 1) % n;
                if (++check == r) {
                    check = 0;
                    if (!map.containsKey(value)) {
                        map.put(value, 0);
                        candidates.add(value);
                    }
                    value = 0;
                }
            }
            sid = (sid + n - 1) % n;
        }

        candidates.sort(Collections.reverseOrder());
        System.out.println("#" + tc + " " + candidates.get(k - 1));
    }
}
```