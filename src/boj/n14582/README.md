## 백준 14582 - [오늘도 졌다](https://www.acmicpc.net/problem/14582)


### 풀이법

1. 야구의 규칙대로 점수를 세어주기만 하면 됨.

~~~JAVA
StringTokenizer score1 = new StringTokenizer(br.readLine());
StringTokenizer score2 = new StringTokenizer(br.readLine());

int t1 = 0;
int t2 = 0;

for(int i=0; i<9; i++){
    t1 += Integer.parseInt(score1.nextToken());

    if(t1 > t2){
        System.out.println("Yes");
        return;
    }

    t2 += Integer.parseInt(score2.nextToken());
}

System.out.println("No");
~~~
