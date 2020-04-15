## 백준 1342 - [행운의 문자열](https://www.acmicpc.net/problem/1342)

### 풀이법 

1. 입력된 문자열을 해쉬맵에 저장.
2. 모두 같지않은 순서를 조건으로, dfs로 탐색.

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
