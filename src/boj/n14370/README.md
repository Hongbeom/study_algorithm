## 백준 14370 - [전화번호 수수께끼 (Large)](https://www.acmicpc.net/problem/14370)


### 풀이법

1. [N14369](../n14369) 의 문제와 다르게 인풋의 길이가 2000 까지.
  - 똑같은 코드로 수행하게 되면 당연히 시간초과.
2. 0 부터 9 까지의 알파벳을 살펴보면, 각자 유니크한 알파벳을 가지고 있는 숫자가 존재한다.
3. 이 유니크에 순위를 매겨, 제일 유니크한것부터 알파벳을 제외시켜준다.
- 순위는 0, 6, 4, 5, 8, 3, 2, 9, 7, 1

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
        tList.add(ID[i]);
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
    NUM.add(SIX);
    NUM.add(FOUR);
    NUM.add(FIVE);
    NUM.add(EIGHT);
    NUM.add(THREE);
    NUM.add(TWO);
    NUM.add(NINE);
    NUM.add(SEVEN);
    NUM.add(ONE);
    ID = new int[]{0, 6, 4, 5, 8, 3, 2, 9, 7, 1};
}
```
