## 백준 12945 - [재미있는 박스 정리](https://www.acmicpc.net/problem/12945)

### 풀이법

1. 받은 박스 값들을 오름차순으로 정렬.
2. 배열을 반으로 나누어서 생각.
 - 오른쪽 절반, 왼쪽 절반.
 - 오른쪽 절반 끝에서 부터, 이분탐색을 이용하여 왼쪽 절반에서 자신의 절반보다 작거나 같은 최대 수의 인덱스를 뽑는다.
 - 오른쪽 절반의 다음 값은 0 부터 이전 이분탐색으로 뽑은 인덱스까지 이분탐색을 진행.
 - 만약 있다면 상자에 넣어준것.
~~~JAVA
static int[] BOX;

public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine());
    BOX = new int[n];
    for (int i = 0; i < n; i++) {
        BOX[i] = Integer.parseInt(br.readLine());
    }
    Arrays.sort(BOX);
    int answer = n;

    int mid = n / 2;
    int right = mid - 1;

    for (int i = BOX.length - 1; i >= mid; i--) {
        if(BOX[i] / 2 < BOX[0]){
            break;
        }
        int id = bs(0, right, BOX[i] / 2);
        answer--;
        right = id - 1;
        if(right < 0){
            break;
        }
    }
    System.out.println(answer);
}


static int bs(int left, int right, int target) {
    if (left == right) {
        return left;
    }
    int mid = (left + right) / 2;
    int current = BOX[mid];

    if (current <= target) {
        if (BOX[mid + 1] > target) {
            return mid;
        }
        return bs(mid + 1, right, target);
    } else {
        return bs(left, mid, target);
    }
}
~~~
