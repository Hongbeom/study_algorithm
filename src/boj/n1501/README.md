## 백준 1501 - [영어 읽기](https://www.acmicpc.net/problem/1501)

구현 문제 + 문자열 처리

### 풀이법

1. 단어는 알파벳 대소문자밖에 들어오지 않으므로, 사전을 ArrayList 2차원 배열로 만든다.
- 인덱스는 앞이 접두사, 뒤가 접미사
~~~JAVA
List<char[]>[][] dic = new ArrayList[52][52];

for (int i = 0; i < n; i++) {
    char[] init = br.readLine().toCharArray();

    int a = (int) init[0];
    int b = (int) init[init.length - 1];

    init[0] = (char) 64;
    init[init.length - 1] = (char) 64;

    if (a <= 90) {
        a -= 65;
    } else {
        a = a - 97 + 26;
    }

    if (b <= 90) {
        b -= 65;
    } else {
        b = b - 97 + 26;
    }

    if (dic[a][b] == null) {
        dic[a][b] = new ArrayList<>();
    }
    Arrays.sort(init);
    dic[a][b].add(init);
}
~~~

2. 문장이 들어오면 단어로 끊고, 앞, 뒤 문자로 그 사전을 찾아 해석할 수 있는지 비교
- 해석은 문자열을 정렬시켜 그 문자열이 같은지 판단.
~~~JAVA
origin:
for (int i = 0; i < m; i++) {
    String[] words = br.readLine().split(" ");
    int answer = 1;
    for (String w : words) {
        char[] compare = w.toCharArray();

        int a = (int) compare[0];
        int b = (int) compare[compare.length - 1];

        compare[0] = (char) 64;
        compare[compare.length - 1] = (char) 64;

        if (a <= 90) {
            a -= 65;
        } else {
            a = a - 97 + 26;
        }

        if (b <= 90) {
            b -= 65;
        } else {
            b = b - 97 + 26;
        }

        if (dic[a][b] == null) {
            // 해석할 경우가 없는 경우.
            System.out.println(0);
            continue origin;
        }
        int ca = 0;
        Arrays.sort(compare);
        for (char[] word : dic[a][b]) {
            if (word.length != compare.length) {
                continue;
            }
            if (Arrays.equals(word, compare)) {
                ca++;
            }
        }
        answer *= ca;
    }
~~~
