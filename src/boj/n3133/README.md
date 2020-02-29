## 백준 3133 - [코끼리](https://www.acmicpc.net/problem/3133)

이틀동안 해맨 문제. 이분탐색을 통해 memoization.

### 초기 잘못된 접근

1. 여러가지 방법을 이용해봄.
- bfs : 당연히 시간, 메모리 초과
- bfs + memoization : 시간초과
- 정렬후 탐색 : 시간초과...
- 정렬 후 탐색 with memoization : 시간초과...


### 풀이법

1. 연못의 좌표를 x의 오름차순 -> y의 내림차순으로 정렬.
~~~JAVA
 // x에 대해 오름차순, y에 대해 내림차순으로 정렬
Arrays.sort(plants, (o1, o2) -> {
    if (o1[0] > o2[0]) {
        return 1;
    } else if (o1[0] < o2[0]) {
        return -1;
    } else {
        if (o1[1] > o2[1]) {
            return -1;
        } else if (o1[1] < o2[1]) {
            return 1;
        }
    }
    return 0;
});
~~~

- 이런 방향으로 정렬을 해주면 dp를 이용할 수 있다.

2. x의 range를 구한다.
~~~JAVA
int compare = -1;
int cid = -1;

// x 에 대해 range 를 생성, 배열 접근 순서는 range의 반대로 접근.
List<int[]> ranges = new ArrayList<>();
for (int i = 0; i < n; i++) {
    if (plants[i][0] != compare) {
        compare = plants[i][0];
        if (i != 0) {
            ranges.add(new int[]{cid, i - 1});
        }
        cid = i;
    }
    if (i == n - 1) {
        ranges.add(new int[]{cid, i});
    }
}
~~~
- range를 생성하는 이유는 plants 배열을 x의 기준으로 쉽게 접근하기 위해 만들었다.

3. dp를 이용하기 위해 List<List<DP> memo 형태의 memoization을 사용.
~~~JAVA
static long[] count;
static int[][] plants;
static List<List<DP>> memo;

static class DP {
    int index;
    long cv;

    public DP(int index, long cv) {
        this.index = index;
        this.cv = cv;
    }
}
~~~

- index는 정렬 후의 연못의 인덱스, cv는 count를 구하기 위한 변수.
- count배열은 i 식물로 도착했을때의 최장경로의 갯수이다.
- memo에는 List<DP>가 들어있다. 각각의 리스트는 식물의 인덱스와 cv를 담고있는 DP를 가지고 있다.
- DP의 인덱스가 가리키는 식물은 그 식물로 끝을 맺는 최장경로의 최대 식물의 갯수가 i+1인 식물이다.  

4. memoization.
- 첫번째 range는 x값이 최소이다. 그러므로, 첫번째 range에 존재하는 식물로 끝을 맺는 최장경로의 최대 식물의 갯수는 1개이다.
- memo의 0번째 list에 첫번째 range의 식물을 모두 넣어준다.
- range에서 식물들은 y에 대해 내림차순으로 정렬되어 있으므로 memo[0] 에 들어가는 식물(DP)도 똑같이 y에 대해 내림차순으로 정렬된다.
~~~JAVA
int[] firstRange = ranges.get(0);
int zid = 0;
List<DP> zeroList = memo.get(0);
for (int i = firstRange[0]; i <= firstRange[1]; i++) {
    count[i] = 1;
    if (zid == 0) {
        zeroList.add(new DP(i, count[i]));
    } else {
        zeroList.add(new DP(i, (zeroList.get(zid - 1).cv + count[i]) % 1000000007));
    }
    zid++;
}
~~~ 

5. memo를 통해 다른 range도 구해 나간다.

~~~JAVA
for (int i = 1; i < ranges.size(); i++) {
    int[] range = ranges.get(i);

    for (int id = range[0]; id <= range[1]; id++) {
        // memo(list)의 끝값의 y 좌표료 binary search 를 수행

        int jump = bs(0, memo.size() - 1, plants[id][1]);

        // 새롭게 max값을 갱신하는 경우.
        if (jump > memo.size() - 1) {
            memo.add(new ArrayList<>());
        }

        List<DP> current = memo.get(jump);
        long add = 0;

        if (current.size() != 0) {
            add = current.get(current.size() - 1).cv;
        }
        
        if (jump == 0) {
            count[id] = 1;
        } else {
            count[id] = count[id] + bsCount(jump - 1, 0, memo.get(jump - 1).size(), plants[id][1]);
        }
        current.add(new DP(id, (count[id] + add) % 1000000007));
    }
}
~~~

- 연못 i로 마지막으로 점프하는 최장경로의 식물의 갯수를 memo를 통해 구할 수 있다.
- memo에는 몇개의 리스트가 존재한다. 각각의 리스트의 DP는 인덱스가 가리키는 식물의 y값에 대해 내림차순으로 정렬되어 있다.
- memo의 각각의 리스트들의 끝값은 y의 오름차순으로 정렬되어 있다. 
  - (끝값은 y가 제일 작은 식물, 다음 식물로 뛸수 있다면 memo[i] 의 최소 y가 당연히 memo[i+1]의 최소 y보다 작다.) 
- plants 배열을 탐색할때, 초기화 해주려는 식물의 x값은 memo에 초기화된 식물의 x값 보다 항상 크거나 같다. (정렬되어 있기 때문에)
- range에 존재하는 식물들은 x값이 같고, y에 대해 내림차순으로 정렬되어 있다.
- 그러므로, memo[i]에 존재하는 식물(DP)의 끝 y값보다 현재 식물의 y값이 더 크다면 현재 식물은 i+2 를 최장경로로 가지게 된다.
- 이분탐색으로 memo의 각 리스트들의 끝값을 탐색하여 최장경로의 식물의 갯수를 구한 후, 그 식물또한 DP의 형태로 리스트에 저장한다. 
  
~~~JAVA
static int bs(int left, int right, int target) {

    if (getEndY(memo.size() - 1) < target) {
        return memo.size();
    }

    if (getEndY(0) >= target) {
        return 0;
    }

    int mid = (left + right) / 2;
    int compare = getEndY(mid);
    // find upper bound
    if (compare >= target) {
        if (mid == 0 || target > getEndY(mid - 1)) {
            return mid;
        }
        return bs(left, mid, target);
    } else {
        return bs(mid + 1, right, target);
    }

}

static int getEndY(int id) {
    List<DP> list = memo.get(id);
    return plants[list.get(list.size() - 1).index][1];
}
~~~

6. memoization을 통해 그 최장경로의 갯수도 구할 수 있다.
- DP에 존재하는 cv를 이용.
- 첫번째 존재하는 식물들의 count는 모두 1이다.
- 식물을 DP형태로 memo에 저장할때 리스트 제일 마지막 cv에 자신의 count를 더해서 저장한다.
- 첫번째 range가 아닐때 count를 구하는 방법은 만약 식물이 memo[i+1]의 리스트로 들어간다면, memo[i]의 리스트를 이용하여 구한다.
  - 들어가는 식물은 당연히 memo[i] 리스트의 맨 끝 식물(DP)의 y값보다 크다.
  - memo[i] 에 존재하는 식물(DP)의 y값이 memo[i+1]에 들어가는 식물의 y값보다 클 수 있다.
  - memo[i] 는 y에 대해 내림차순으로 정렬되어 있기 때문에, 현재 들어가는 식물보다 큰 y값이 시작되는 리스트의 인덱스를 이분탐색으로 구할 수 있다.
  - 그 지점의 cv와 맨끝의 cv의 차가 바로 그 식물의 최장경로의 인덱스의 count 이다. 

~~~JAVA

static int getY(int jump, int id) {
    List<DP> list = memo.get(jump);
    return plants[list.get(id).index][1];
}

static long bsCount(int jump, int left, int right, int target) {

    if (getY(jump, 0) < target) {
        return memo.get(jump).get(memo.get(jump).size() - 1).cv;
    }

    if (getY(jump, memo.get(jump).size() - 1) >= target) {
        return 0;
    }

    int mid = (left + right) / 2;
    int compare = getY(jump, mid);

    if (compare >= target) {
        if (mid == memo.get(jump).size() - 1) {
            return 0;
        } else if (getY(jump, mid + 1) < target) {

            long re = memo.get(jump).get(memo.get(jump).size() - 1).cv
                    - memo.get(jump).get(mid).cv;

            if (re < 0) {
                re += 1000000007;
            }

            return re;
        }
        return bsCount(jump, mid + 1, right, target);
    } else {
        return bsCount(jump, left, mid, target);
    }


}
~~~