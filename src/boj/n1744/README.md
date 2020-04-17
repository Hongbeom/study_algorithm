## 백준 1744 - [수 묶기](https://www.acmicpc.net/problem/1744)

### 풀이법

1. 0을 포함한 음의 정수들과 양의 정수들을 따로 list에 저장.
2. 0을 포함한 음의 정수들의 리스트는 오름차순으로 정렬, 양의 정수들의 리스트는 내림차순으로 정렬.
3. 각 앞의 원소에서 2개씩 봐준다.
    - 양의 정수들의 리스트는 1보다 큰 원소가 나오기 전까지 곱해서 더해준다.
    - 0을 포함한 음의 정수들의 리스트는 그냥 곱해서 더해준다.

 
~~~JAVA
public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());

    List<Integer> positive = new ArrayList<>();
    List<Integer> negative = new ArrayList<>();
    for (int i = 0; i < n; i++) {
        int v = Integer.parseInt(br.readLine());
        if (v > 0) {
            positive.add(v);
        } else {
            negative.add(v);
        }
    }
    positive.sort(Collections.reverseOrder());
    Collections.sort(negative);
    int answer = 0;
    for (int i = 0; i <= positive.size() / 2; i++) {
        int id = i * 2;
        if (id >= positive.size()) {
            break;
        }
        if (id + 1 >= positive.size()) {
            answer += positive.get(id);
        } else {
            int f = positive.get(id);
            int s = positive.get(id + 1);
            if (s > 1) {
                answer += f * s;
            } else {
                answer += f + s;
            }
        }
    }

    for (int i = 0; i <= negative.size() / 2; i++) {
        int id = i * 2;
        if (id >= negative.size()) {
            break;
        }
        if (id + 1 >= negative.size()) {
            answer += negative.get(id);
        } else {
            int f = negative.get(id);
            int s = negative.get(id + 1);
            answer += f * s;
        }
    }
    System.out.println(answer);
}
~~~