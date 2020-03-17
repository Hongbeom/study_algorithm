## 프로그래머스 - [뉴스 클러스터링](https://programmers.co.kr/learn/courses/30/lessons/17677)

### 풀이법

1. check 배열을 이용하여 원소들을 구하면서 공통 원소의 갯수만 구하면 된다.

```JAVA
public static int solution(String str1, String str2) {
    int answer = 0;
    int[][] check = new int[26][26];

    char[] word = str1.toCharArray();

    int fCnt = 0;

    for (int i = 0; i < word.length - 1; i++) {
        char c1 = word[i];
        char c2 = word[i + 1];

        if (c1 < 65 || c1 > 122 || (c1 > 90 && c1 < 97)) {
            continue;
        }
        if (c2 < 65 || c2 > 122 || (c2 > 90 && c2 < 97)) {
            continue;
        }
        int id1;
        if (c1 <= 90) {
            id1 = c1 - 'A';
        } else {
            id1 = c1 - 'a';
        }

        int id2;
        if (c2 <= 90) {
            id2 = c2 - 'A';
        } else {
            id2 = c2 - 'a';
        }
        fCnt++;
        check[id1][id2]++;
    }

    int common = 0;
    word = str2.toCharArray();
    int sCnt = 0;
    for (int i = 0; i < word.length - 1; i++) {
        char c1 = word[i];
        char c2 = word[i + 1];

        if (c1 < 65 || c1 > 122 || (c1 > 90 && c1 < 97)) {
            continue;
        }
        if (c2 < 65 || c2 > 122 || (c2 > 90 && c2 < 97)) {
            continue;
        }
        int id1;
        if (c1 <= 90) {
            id1 = c1 - 'A';
        } else {
            id1 = c1 - 'a';
        }

        int id2;
        if (c2 <= 90) {
            id2 = c2 - 'A';
        } else {
            id2 = c2 - 'a';
        }
        sCnt++;
        if(check[id1][id2] > 0){
            common++;
            check[id1][id2]--;
        }
    }
    double sim;
    if(fCnt == 0 && sCnt == 0){
        sim = 1;
    }else{
        sim = (double) common / (fCnt + sCnt - common);
    }

    answer = (int) (sim * 65536);

    return answer;
}
```