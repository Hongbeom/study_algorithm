## 백준 5052 - [전화번호 목록](https://www.acmicpc.net/problem/5052)

인풋을 받다가 다음 케이스로 넘어갈때, 아직 인풋이 남아있는지 모르고 넘어가서 오래걸린 문제...


### 풀이법

1. Trie 이용
~~~JAVA
static class HTrie {
    char number;
    boolean isRoot;
    boolean isEnd;
    HTrie[] children;

    HTrie(char number, boolean isRoot) {
        this.number = number;
        this.isRoot = isRoot;
        children = new HTrie[10];
    }

    boolean insert(char[] numbers) {
        HTrie t = this;

        for (int i = 0; i < numbers.length; i++) {
            char c = numbers[i];
            int id = c - '0';

            if (t.children[id] == null) {
                t.children[id] = new HTrie(c, false);
            } else {
                if (t.children[id].isEnd && i != numbers.length - 1) {
                    return false;
                }

                if (i == numbers.length - 1) {
                    return false;
                }
            }

            t = t.children[id];

            if (i == numbers.length - 1) {
                t.isEnd = true;
            }
        }

        return true;
    }
}
~~~

- root에서 번호 하나씩 입력.
- 판별은 2가지 방법으로
    - 자신이 들어갈게 마지막인데 이미 그자리에 원소가 있는 경우
    - 자신을 들어갈게 마지막이 아닌데 이미 그 자리에 마지막 원소가 있는 경우
