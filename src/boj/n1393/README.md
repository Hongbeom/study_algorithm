## 백준 1393 - [음하철도 구구팔](https://www.acmicpc.net/problem/1393)

### 풀이법 

1. 이동 가능한 정수 좌표들 사이에서 가장 최소 거리의 좌표를 뽑는다.
```JAVA
static int ANS = 0;
public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    HashMap<Character, Integer> wordMap = new HashMap<>();
    char[] words = br.readLine().toCharArray();
    int size = words.length;
    for (char w : words) {
        int c = wordMap.getOrDefault(w, 0);
        wordMap.put(w, c + 1);
    }
    dfs(wordMap, size, '\u0000');
    System.out.println(ANS);
}

static void dfs(HashMap<Character, Integer> wordMap, int size, char before) {
    if (size == 0) {
        ANS++;
        return;
    }
    for (char key : wordMap.keySet()) {
        if (before == key) {
            continue;
        }
        HashMap<Character, Integer> tmp = new HashMap<>(wordMap);
        int a = tmp.get(key);

        if (a - 1 == 0) {
            tmp.remove(key);
        } else {
            tmp.put(key, a - 1);
        }
        dfs(tmp, size - 1, key);
    }
}
```
