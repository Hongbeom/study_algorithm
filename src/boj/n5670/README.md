## 백준 5670 - [휴대폰 자판](https://www.acmicpc.net/problem/5670)

### 풀이법

1. Trie 이용 풀이.

```JAVA
static class Trie {

    char c;
    boolean isRoot;
    int childKind;

    Trie[] children;


    Trie(char c, boolean isRoot) {
        this.c = c;
        this.isRoot = isRoot;
        this.childKind = 0;
        children = new Trie[26];
    }

    void addWord(char[] word) {
        Trie t = this;

        for (int i = 0; i < word.length; i++) {
            int id = ((int) word[i]) - 97;

            if (t.children[id] == null) {
                t.childKind++;
                t.children[id] = new Trie(c, false);
            }

            t = t.children[id];

            if (i == word.length - 1) {
                t.childKind++;
            }
        }
    }

    int check(char[] word) {
        int cnt = 1;
        Trie t = this.children[((int) word[0]) - 97];

        for (int i = 1; i < word.length; i++) {

            int id = ((int) word[i]) - 97;

            if (t.childKind != 1) {
                cnt++;
            }
            t = t.children[id];
        }

        return cnt;
    }
}
```

- 멤버
    - 영어 소문자 character
    - 자식이 최대 26개 있으므로 크기가 26인 Trie 배열
    - root를 판별하는 boolean
    - 자식의 종류의 갯수인 childKind int 변수


2. 단어들을 Trie에 모두 저장해둔 후, 다시 그 단어를 검색할때 leafKind 변수를 이용해 사용자 입력이 필요한지 판단.

```JAVA
int check(char[] word) {
    int cnt = 1;
    Trie t = this.children[((int) word[0]) - 97];

    for (int i = 1; i < word.length; i++) {

        int id = ((int) word[i]) - 97;

        if (t.childKind != 1) {
            cnt++;
        }
        t = t.children[id];
    }

    return cnt;
}
```

3. 주의할점: 단어를 root에 add할때, 각 단어의 마지막 문자 Trie에 leaf Kind를 1 더해주어야함
~~~JAVA
if (i == word.length - 1) {
    t.childKind++;
}
~~~

- hell, hello 의 경우와 같이 4번째 l이 입력으로 들어갈 경우, hell 과 hello 를 구별할수 없기 때문에 유저 입력이 필요.