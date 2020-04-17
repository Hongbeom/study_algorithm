## 백준 13144 - [List of Unique Numbers](https://www.acmicpc.net/problem/13144)

### 풀이법

1. 입력으로 주어진 수열에서, 공통이 없는 연속되는 부분집합의 갯수를 구하는 문제.
2. 수열의 앞에서 부터 시작하고, 같은수가 나올때 마다 Map에 그 길이를 저장한다.
3. 공통된 부분만큼 답에서 빼주는 처리도 수행한다,
4. 1부터 길이의 합을 모든 케이스(길이)에 적용하여 답에 더해준다. 

~~~JAVA
public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    long answer = 0;
    int[] check = new int[100001];
    StringTokenizer st = new StringTokenizer(br.readLine());
    int current = 0;
    int lastId = 0;
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < n; i++) {
        int v = Integer.parseInt(st.nextToken());
        if (check[v] == 0 || check[v] - 1 < lastId) {
            current++;
            check[v] = i + 1;
        } else {
            map.put(current, map.getOrDefault(current, 0) + 1);
            int id = check[v] - 1;
            check[v] = i + 1;
            current = i - id;
            answer -= cal(current - 1);
            lastId = id;
        }

        if (i == n - 1) {
            map.put(current, map.getOrDefault(current, 0) + 1);
        }
    }

    for (int key : map.keySet()) {
        long h = map.get(key);
        answer += h * cal(key);
    }
    System.out.println(answer);
}

static long cal(int a) {
    long n = a;
    return n * (n + 1) / 2;
}
~~~