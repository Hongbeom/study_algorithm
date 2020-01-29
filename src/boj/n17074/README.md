## 백준 17074 - [정렬](https://www.acmicpc.net/problem/17074)

### 풀이법

1. 한 수를 빼서 정렬된 배열을 만들고 싶다.
- 그렇다면, 배열을 한번 탐색해 ai > ai+1 만족하는 지점이 1개여야 한다.
2. 이 지점이 한곳이라면, 총 2가지 경우가 나올 수 있다.
- 자기 자신을 빼는 경우.
- 자기보다 앞으 원소를 빼는경우.
- 2가지를 평가하여 답을 도출

~~~JAVA
if (list.size() == 0) {
    System.out.println(n);
} else if (list.size() >= 2) {
    System.out.println(0);
} else {

    int answer = 0;
    int id = list.get(0);

    // 1. 자기를 빼는것 -- 자기 앞, 뒤 를 비교
    if (id == n - 1 || input[id - 1] <= input[id + 1]) {
        answer++;
    }


    // 2. 자기 앞에를 빼는것 -- 자기 -2 와 자기를 비교
    if (id == 1 || input[id - 2] <= input[id]) {
        answer++;
    }

    System.out.println(answer);
}
~~~