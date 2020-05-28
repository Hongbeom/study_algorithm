## 백준 1593 - [문자 해독](https://www.acmicpc.net/problem/1593)

### 풀이법

1. g개씩 글자르 끊어 탐색.

```JAVA
public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int g = Integer.parseInt(st.nextToken());
    int s = Integer.parseInt(st.nextToken());
    Set<Integer> set = new HashSet<>();
    int[] base = new int[60];

    for (char c : br.readLine().toCharArray()) {
        base[c - 'A']++;
        set.add(c - 'A');
    }

    char[] words = br.readLine().toCharArray();
    int answer = 0;

    int[] compare = new int[60];

    for (int i = 0; i < g; i++) {
        compare[words[i] - 'A']++;
    }

    if (compareWords(base, compare, set)) {
        answer++;
    }

    for (int i = g; i < s; i++) {

        compare[words[i - g] - 'A']--;
        compare[words[i] - 'A']++;
        if (compareWords(base, compare, set)) {
            answer++;
        }
    }

    System.out.println(answer);
}

static boolean compareWords(int[] base, int[] compare, Set<Integer> set) {

    for (int id : set) {
        if (base[id] != compare[id]) {
            return false;
        }
    }
    return true;
}
```