## 백준 3076 - [상근이의 체스판](https://www.acmicpc.net/problem/3076)

### 풀이법

1. String을 만들어주고 조건에 맞게 출

~~~JAVA
int r = Integer.parseInt(st.nextToken());
int c = Integer.parseInt(st.nextToken());

st = new StringTokenizer(br.readLine());
int a = Integer.parseInt(st.nextToken());
int b = Integer.parseInt(st.nextToken());

StringBuilder fsb = new StringBuilder();
StringBuilder ssb = new StringBuilder();

for (int i = 0; i < c; i++) {
    if (i % 2 == 0) {
        for (int j = 0; j < b; j++) {
            fsb.append("X");
            ssb.append(".");
        }
    } else {
        for (int j = 0; j < b; j++) {
            fsb.append(".");
            ssb.append("X");
        }
    }
}

String first = fsb.toString();
String second = ssb.toString();

for (int i = 0; i < r; i++) {
    if (i % 2 == 0) {
        for (int j = 0; j < a; j++) {
            System.out.println(first);
        }
    } else {
        for (int j = 0; j < a; j++) {
            System.out.println(second);
        }
    }

}

}
~~~
