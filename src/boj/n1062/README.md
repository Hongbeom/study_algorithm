## 백준 1062 - [가르침](https://www.acmicpc.net/problem/1062)

### 풀이법

1. 모든 단어는 'anta', 'tica'가 앞, 뒤에 들어가기 때문에 a, n, t, i, c의 케이스를 제외
2. 인풋 받은 단어에서 배울 수 있는 후보단어 count
3. 그 중에서 k-5개의 단어 후보를 만들어 각각 판단.
 

```JAVA
List<boolean[]> candidates = new ArrayList<>();

    if (k - 5 < 0) {
        System.out.println(0);
        return;
    } else if (cnt > k - 5) {
        getCandidates(candidates, new boolean[26], alpha.clone(), 0, k - 5);
    } else {
        System.out.println(n);
        return;
    }

    int max = 0;

    for (boolean[] can : candidates) {
        int count = 0;

        loop1:
        for (char[] word : words) {

            for (char c : word) {
                if (c == 'a' || c == 'n' || c == 't' || c == 'i' || c == 'c') {
                    continue;
                }
                if (!can[(int) c - 97]) {
                    continue loop1;
                }
            }

            count++;
        }

        max = Math.max(max, count);
    }

    System.out.println(max);


}

static void getCandidates(List<boolean[]> list, boolean[] tm, boolean[] alpha, int start, int end) {

    if (start == end) {
        list.add(tm);
        return;
    }

    for (int i = 0; i < alpha.length; i++) {
        if (alpha[i]) {
            alpha[i] = false;
            tm[i] = true;
            getCandidates(list, tm.clone(), alpha.clone(), start + 1, end);
            tm[i] = false;
        }

    }

}
```