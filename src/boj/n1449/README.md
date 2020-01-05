## 백준 1449 - [수리공 항승](https://www.acmicpc.net/problem/1449)

간단한 greedy 문제

### 풀이법

1. 물이 새는 위치를 오름차순으로 정렬 후, 앞에서부터 테이프가 커버 가능한 지역의 갯수.

```JAVA
Arrays.sort(pipe);

int answer = 0;
int current = 0;
for(int p : pipe){
    if(current == 0){
        answer++;
        current = p + l - 1;
    }else{
        if(current < p){
            answer++;
            current = p + l - 1;
        }
    }
}
```
