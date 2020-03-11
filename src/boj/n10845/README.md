## 백준 10845 - [큐](https://www.acmicpc.net/problem/10845)


### 풀이법

1. LinkedList 이용

~~~JAVA
String query = st.nextToken();

if (query.equals("push")) {
    queue.add(Integer.parseInt(st.nextToken()));
} else {
    int ans = -1;
    if (query.equals("pop")) {
        if (!queue.isEmpty()) {
            ans = queue.poll();
        }

    } else if (query.equals("size")) {
        ans = queue.size();
    } else if (query.equals("empty")) {
        if (queue.isEmpty()) {
            ans = 1;
        } else {
            ans = 0;
        }
    } else if (query.equals("front")) {
        if (!queue.isEmpty()) {
            ans = queue.peekFirst();
        }
    } else if (query.equals("back")) {
        if (!queue.isEmpty()) {
            ans = queue.peekLast();
        }
    }

    System.out.println(ans);

}
~~~
