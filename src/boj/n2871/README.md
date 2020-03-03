## 백준 2871 - [아름다운 단어](https://www.acmicpc.net/problem/2871)


### 풀이법

1. 인풋으로 받는 단어를 사전순으로 정렬된 heap에 저장.
2. selected 배열을 이용해 상근이는 word의 뒤부터, 희원이는 우선순위가 가장 높은거부터 글자를 선택.


```JAVA
int sid = n - 1;
int wid = 0;
while (wid < n / 2) {
    // first, sang
    while (selected[sid]) {
        sid--;
    }
    ssb.append(word[sid]);
    selected[sid] = true;
    sid--;

    // second, hee
    while (!pq.isEmpty()) {
        Word w = pq.poll();
        if (!selected[w.index]) {
            hsb.append(w.w);
            selected[w.index] = true;
            break;
        }
    }
    wid++;
}
```

