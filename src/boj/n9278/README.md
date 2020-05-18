## 백준 9278 - [절망적인 줄](https://www.acmicpc.net/problem/9278)

풀이를 떠올리기 정말 힘들었던 문제... 결국 솔루션을 보고 해결하였다... 
 
### 풀이법
 
1. 50원을 가진 사람을 열린 괄호, 100원을 가진 사람을 닫힌 괄호라고 생각.
  - 괄호가 정삭적으로 닫히는 케이스가 정답이라고 생각하면 편함.
 
2. 줄을 탐색하며 각각 열린괄호, 점, 닫힌 괄호의 케이스로 생각해 줌.
  - 초기의 start에는 아무것도 없는 경우이며 이 경우는 성립하므로 1이 된다.
  - '(' 가 나왔을 경우, 배열의 모든 값을 오른쪽으로 이동시시킨다. -> start - 1 을 해줌.
  - ')' 가 나왔을 경우, 배열의 모든 값을 왼쪽으로 이동시킨다. -> start + 1 을 해줌.
  - '.' 이 나왔을 경우, 두가지 모두 해주므로 새로운 배열에 값을 양쪽으로 펴뜨려 준다.
  - 위 과정을 반복했을때, 경우의 수는 num[start]에 존재한다.
  
~~~JAVA
static int ADJ = 1000000;
static int MAX = 1000;

public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String in;

    while ((in = br.readLine()) != null) {

        char[] line = in.toCharArray();

        if (line[0] == ')') {
            System.out.println(0);
            continue;
        }

        System.out.println(calculate(line));
    }
}

static int calculate(char[] line) {

    int[] num = new int[MAX * 2 + 1];
    int[] num2 = new int[MAX * 2 + 1];
    int[] change;

    int start = MAX;
    int end = MAX;

    num[start] = 1;

    for (char c : line) {

        if (c == '(') {
            num[--start] = 0;
        } else if (c == ')') {
            start++;
        } else {
            num2[end + 1] = num[end];

            if (start < end) {
                num2[start] = num[start + 1];
                num2[end] = num[end - 1];
            } else {
                num2[start] = 0;
                num2[end] = 0;
            }

            for (int i = start + 1; i < end; i++) {
                num2[i] = (num[i - 1] + num[i + 1]) % ADJ;
            }
            end++;
            change = num;
            num = num2;
            num2 = change;
        }

        if (start > end) {
            num[start] = 0;
            break;
        }
    }

    return num[start];
}
~~~
 