## 백준 6986 - [절사평균](https://www.acmicpc.net/problem/6986)

### 풀이법

1. 인풋되는 수를 받아서 소팅 후 각각 절사평균과 보정평균을 구해준다.

```JAVA
double[] numbers = new double[n];
    double min = Double.MAX_VALUE;
    for (int i = 0; i < n; i++) {
        numbers[i] = Double.parseDouble(br.readLine());
        min = Math.min(min, numbers[i]);
    }

    Arrays.sort(numbers);
    double sum = 0d;

    for (int i = k; i < numbers.length - k; i++) {
        sum += numbers[i];
    }

    System.out.println(String.format("%.2f", sum / (n - 2 * k)));
    System.out.println(String.format("%.2f", (sum + numbers[k] * k + numbers[n - k - 1] * k) / n));
```
