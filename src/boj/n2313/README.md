## 백준 2313 - [보석 구매하기](https://www.acmicpc.net/problem/2313)

### 풀이법

1. [N1912](../n1912) 연속합과 비슷한 문제 -> DP 이용
2. 연속합 -> 구간 크기 -> 구간 첫번째 순으로 비교가능한 클래스 구현

```JAVA
static class Case implements Comparable<Case> {
    int value;
    int start;
    int end;

    Case(int value, int start, int end) {
        this.value = value;
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Case o) {
        if (this.value > o.value) {
            return 1;
        } else if (this.value < o.value) {
            return -1;
        } else {
            if (this.end - this.start > o.end - o.start) {
                return -1;
            } else if (this.end - this.start < o.end - o.start) {
                return 1;
            } else {
                if (this.start > o.start) {
                    return -1;
                } else if (this.start < o.start) {
                    return 1;
                }
            }
        }
        return 0;
    }
}
```

3. 입력이 주어졌을때, 그 이전의 memo와 입력의 합이 입력보다 크면 합을 선택, 반대의 경우 입력을 선택
~~~JAVA
List<Case> list = new ArrayList<>();
for (int t = 0; t < T; t++) {
    int n = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());
    Case max = new Case(Integer.MIN_VALUE, 0, 0);
    Case memo = new Case(Integer.MIN_VALUE, 0, 0);
    for (int i = 0; i < n; i++) {
        int value = Integer.parseInt(st.nextToken());
        if (i == 0) {
            memo.value = value;
        } else {
            if (memo.value + value > value) {
                memo.value = memo.value + value;
                memo.end = i;
            } else {
                memo.value = value;
                memo.start = i;
                memo.end = i;
            }
        }

        if (max.compareTo(memo) < 0) {
            max.value = memo.value;
            max.start = memo.start;
            max.end = memo.end;
        }
    }

    total += max.value;
    list.add(max);
}
~~~

