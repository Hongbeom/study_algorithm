## 백준 6996 - [애너그램](https://www.acmicpc.net/problem/6996)

### 풀이법 

1. 간단히 글자가 같은걸 비교
```JAVA
int cnt = 0;
st = new StringTokenizer(br.readLine());
String a = st.nextToken();
String b = st.nextToken();
if (a.equals(b)) {
    ans(a, b, false);
    continue;

}
int[] aChar = new int[26];
for (char c : a.toCharArray()) {
    aChar[c - 'a']++;
}

for (char c : b.toCharArray()) {
    int id = c - 'a';
    if (aChar[id] == 0) {
        ans(a, b, false);
        continue tc;
    }
    aChar[id]--;
    cnt++;
}
if (cnt == a.length()) {
    ans(a, b, true);
} else {
    ans(a, b, false);
}
```
