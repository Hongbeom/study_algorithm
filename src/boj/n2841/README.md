## 백준 2841 - [외계인의 기타 연주](https://www.acmicpc.net/problem/2841)

### 초기 잘못된 접근

1. 이분탐색으로 떼야하는 플렛의 수를 구함.
- 시간초과: 시간초과가 나는 이유는 list에서 떼어야 해주는 플렛을 제거해주므로 그 로직에서 발생하는거 같다.

### 풀이법

1. 스택이용. 음을 저장하고 스택에서 꺼낸것만큼 떼고 다시 더해준다.
```JAVA
int answer = 0;
for (int i = 0; i < 6; i++) {
    if (melody[i] == null) {
        continue;
    }
    Stack<Integer> frets = new Stack<>();

    melody:
    for (int fret : melody[i]) {

        if (frets.isEmpty()) {
            frets.add(fret);
            answer++;
        } else {
            while (!frets.isEmpty()) {
                if (frets.peek() > fret) {
                    answer++;
                    frets.pop();
                } else {
                    if (frets.peek() != fret) {
                        answer++;
                        frets.add(fret);
                    }
                    continue melody;
                }
            }
            answer++;
            frets.add(fret);
        }

    }

}
```

