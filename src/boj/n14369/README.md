## 백준 14369 - [전화번호 수수께끼 (Small)](https://www.acmicpc.net/problem/14369)


### 풀이법

1. 스트링의 길이가 20으로 작으니, dfs를 이용해서 완전탐색.

```JAVA
static void dfs(int[] counts, int total, List<Integer> list) {
    if (exit) {
        return;
    }
    root:
    for (int i = 0; i < NUM.size(); i++) {
        HashMap<Character, Integer> map = NUM.get(i);
        int[] tmp = counts.clone();
        int tc = total;
        List<Integer> tList = new ArrayList<>(list);
        for (char key : map.keySet()) {
            tmp[key - 'A'] -= map.get(key);
            if (tmp[key - 'A'] < 0) {
                continue root;
            }
            tc -= map.get(key);
        }
        tList.add(i);
        if (tc == 0) {
            ANS = tList;
            exit = true;
            return;
        }
        dfs(tmp, tc, tList);
        if (exit) {
            return;
        }
    }

}

static void init() {
    ZERO.put('Z', 1);
    ZERO.put('E', 1);
    ZERO.put('R', 1);
    ZERO.put('O', 1);

    ONE.put('O', 1);
    ONE.put('N', 1);
    ONE.put('E', 1);

    TWO.put('T', 1);
    TWO.put('W', 1);
    TWO.put('O', 1);

    THREE.put('T', 1);
    THREE.put('H', 1);
    THREE.put('R', 1);
    THREE.put('E', 2);

    FOUR.put('F', 1);
    FOUR.put('O', 1);
    FOUR.put('U', 1);
    FOUR.put('R', 1);

    FIVE.put('F', 1);
    FIVE.put('I', 1);
    FIVE.put('V', 1);
    FIVE.put('E', 1);

    SIX.put('S', 1);
    SIX.put('I', 1);
    SIX.put('X', 1);

    SEVEN.put('S', 1);
    SEVEN.put('E', 2);
    SEVEN.put('V', 1);
    SEVEN.put('N', 1);


    EIGHT.put('E', 1);
    EIGHT.put('I', 1);
    EIGHT.put('G', 1);
    EIGHT.put('H', 1);
    EIGHT.put('T', 1);

    NINE.put('N', 2);
    NINE.put('I', 1);
    NINE.put('E', 1);

    NUM.add(ZERO);
    NUM.add(ONE);
    NUM.add(TWO);
    NUM.add(THREE);
    NUM.add(FOUR);
    NUM.add(FIVE);
    NUM.add(SIX);
    NUM.add(SEVEN);
    NUM.add(EIGHT);
    NUM.add(NINE);
}
```
