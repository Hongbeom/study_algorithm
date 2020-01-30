## 백준 8595 - [히든 넘버](https://www.acmicpc.net/problem/8595)
 
 
### 풀이법
 
1. character로 숫자를 판별하면서 계속 더해준다.
 
 ```JAVA
static int getNum(List<Character> list) {
    int num = 0;
    for (int i = 0; i < list.size(); i++) {
        num += (int) ((list.get(i) - '0') * Math.pow(10, list.size() - i - 1));
    }
    return num;
}
```

~~~JAVA
List<Character> list = new ArrayList<>();
long answer = 0;
for (char c : input) {
    int id = (int) c;
    if (48 <= id && id <= 57) {
        list.add(c);
    } else {
        if (list.size() != 0) {
            if (list.size() > 6) {
                list.clear();
            } else {
                answer += getNum(list);
                list.clear();
            }
        }
    }
}

if (list.size() != 0) {
    if (list.size() > 6) {
        list.clear();
    } else {
        answer += getNum(list);
        list.clear();
    }
}
~~~