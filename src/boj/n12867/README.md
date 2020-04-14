## 백준 12867 - [N차원 여행](https://www.acmicpc.net/problem/12867)

### 풀이법

1. n차원의 좌표상에서 중복 여부만 체크하면 되는 문제.
2. n이 최대 10억이므로, 10억개의 차원을 생성하면 당연히 메모리 초과.
3. m이 최대 50밖에 안된다. 50 * 50도 상당히 적은수.
4. HashMap에 좌표를 저장 -> 0 이면 원소가 없는것.

~~~JAVA
public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int m = Integer.parseInt(br.readLine());
    List<HashMap<Integer, Integer>> check = new ArrayList<>();
    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] spots = new int[m];
    for (int i = 0; i < m; i++) {
        spots[i] = Integer.parseInt(st.nextToken());
    }

    char[] move = br.readLine().toCharArray();
    HashMap<Integer, Integer> current = new HashMap<>();
    if (move[0] == '+') {
        current.put(spots[0], 1);
    } else {
        current.put(spots[0], -1);
    }
    check.add(current);
    for (int i = 1; i < move.length; i++) {
        HashMap<Integer, Integer> next = new HashMap<>(check.get(check.size() - 1));
        int key = spots[i];
        if (next.containsKey(key)) {
            int value = next.get(key);
            if (move[i] == '+') {
                value++;
            } else {
                value--;
            }
            if (value == 0) {
                next.remove(key);
            } else {
                next.put(key, value);
            }
        } else {
            if (move[i] == '+') {
                next.put(key, 1);
            } else {
                next.put(key, -1);
            }
        }
        if (next.isEmpty() || checkDuplicate(check, next)) {
            System.out.println(0);
            return;
        }
        check.add(next);
    }

    System.out.println(1);
}


static boolean checkDuplicate(List<HashMap<Integer, Integer>> check, HashMap<Integer, Integer> next) {
    for (HashMap<Integer, Integer> map1 : check) {
        if (spotEquals(map1, next)) {
            return true;
        }
    }
    return false;
}

static boolean spotEquals(HashMap<Integer, Integer> map1, HashMap<Integer, Integer> map2) {
    if (map1.size() != map2.size()) {
        return false;
    }
    for (int key1 : map1.keySet()) {
        if (!map2.containsKey(key1)) {
            return false;
        }
        if (map1.get(key1).intValue() != map2.get(key1).intValue()) {
            return false;
        }
    }
    return true;
}
~~~
