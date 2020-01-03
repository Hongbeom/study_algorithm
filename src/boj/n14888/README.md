## 백준 14888 - [연산자 끼워넣기](https://www.acmicpc.net/problem/14888)

### 풀이법

1. 배열로 연산자 갯수, 현재 수, step을 기억하고 BFS로 풀이

```JAVA
while (!queue.isEmpty()) {
    int[] current = queue.poll();

    int number = current[4];
    int step = current[5];

    if (step == numbers.length-1) {

        max = Math.max(max, number);
        min = Math.min(min, number);

        continue;
    }

    for (int i = 0; i < 4; i++) {
        int op = current[i];

        if (op > 0) {
            int[] next = current.clone();
            next[i]--;

            switch (i) {
                case 0:
                    next[4] += numbers[step + 1];
                    break;
                case 1:
                    next[4] -= numbers[step + 1];
                    break;
                case 2:
                    next[4] *= numbers[step + 1];
                    break;
                case 3:
                    next[4] /= numbers[step + 1];
                    break;
            }
            next[5] = step + 1;
            queue.add(next);

        }
    }
}
```
