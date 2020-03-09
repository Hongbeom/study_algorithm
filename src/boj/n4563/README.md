## 백준 4563 - [리벤지 오브 피타고라스](https://www.acmicpc.net/problem/4563)

### 풀이법 

1. (C+B)(C-B) = A^2 과 B > A를 만족하는 자연수 쌍의 갯수를 구한다.
2. C+B, C-B는 A^2의 약수 이므로 1<= x < A 까지의 케이스를 본다.


~~~JAVA
static int check(long a) {
    int answer = 0;
    List<Long> left = new ArrayList<>();
    List<Long> right = new ArrayList<>();
    long target = (long) Math.pow(a, 2);
    for (int i = 1; i < a; i++) {
        if (target % i == 0) {
            left.add((long) i);
            right.add(target / i);
        }
    }

    for (int i = 0; i < left.size(); i++) {
        long l = left.get(i);
        long r = right.get(i);

        long plus = l + r;
        long minus = r - l;

        if (plus % 2 == 0 && minus % 2 == 0) {
            long b = minus / 2;

            if(b > a){
                answer++;
            }
        }
    }

    return answer;
}
~~~