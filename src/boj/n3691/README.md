## 백준 3691 - [컴퓨터 조립](https://www.acmicpc.net/problem/3691)

많이 해맸던 문제... 힌트를 얻어 해결.

### 초기 잘못된 접근

1. 부품의 경우의 수를 다 판단하여 가장 높은 값을 얻으려고 하였다.
- 이렇게 하면 당연히 시간 + 메모리 초과. (부품의 종류가 3개, n 이 1000개가 들어올 수 있다.)

2. 힌트를 얻어 이분탐색이 가능하게 만들었다.
- 이분탐색 + 이분탐색의 방법으로 품

### 풀이법

1. HashMap을 이용하여 부폼의 종류당 리스트를 만들어 데이터를 저장.
- 부품의 정보를 price와 quality만 저장해 준다.

~~~JAVA
static class Component implements Comparable<Component> {
    int price;
    int quality;

    Component(int price, int quality) {

        this.price = price;
        this.quality = quality;
    }

    @Override
    public int compareTo(Component o) {
        if (this.quality < o.quality) {
            return -1;
        } else if (this.quality > o.quality) {
            return 1;
        }
        return 0;
    }
}
~~~

- 부품의 대소 비교는 quality를 이용해 비교.

~~~JAVA
for (int i = 0; i < n; i++) {
    st = new StringTokenizer(br.readLine());
    String type = st.nextToken();
    String name = st.nextToken();
    int price = Integer.parseInt(st.nextToken());
    int quality = Integer.parseInt(st.nextToken());

    l = Math.min(l, quality);
    r = Math.max(r, quality);

    List<Component> tmp = map.getOrDefault(type, null);
    if (tmp == null) {
        List<Component> c = new ArrayList<>();
        c.add(new Component(price, quality));
        map.put(type, c);
    } else {
        tmp.add(new Component(price, quality));
    }
}
~~~

2. 각 부폼 종류에 따라 전처리를 수행해 준다.
- 같은 부품에서 성능이 나쁘지만 더 비싼 부품을 선택해 줄 필요가 없다.
- 부품의 리스트를 성능에 따라 오름차순으로 정렬 후, i + 1의 가격보다 큰 인덱스들을 제거해 준다.
```JAVA
// 전처리 과정...
for (String type : map.keySet()) {
    List<Component> tmp = map.get(type);
    Collections.sort(tmp);
    int cp = 0;
    List<Integer> rm = new ArrayList<>();
    for (int i = tmp.size() - 1; i >= 0; i--) {
        Component c = tmp.get(i);
        if (i == tmp.size() - 1) {
            cp = c.price;
        } else {
            if (c.price >= cp) {
                rm.add(i);
            } else {
                cp = c.price;
            }
        }
    }
    for (int id : rm) {
        tmp.remove(id);
    }
}
```

3. 각 타켓 quality의 최적의 부품을 이분탐색으로 구할 수 있다.
~~~JAVA
static Component bs(List<Component> list, int l, int r, int target) {

    if (list.get(list.size() - 1).quality <= target) {
        return list.get(list.size() - 1);
    }

    int mid = (l + r) / 2;
    Component c = list.get(mid);

    if (target <= c.quality) {
        // mid가 0 이거나, mid의 바로 왼쪽 값이 타겟보다 적을때, 값을 반환.
        if (mid == 0 || list.get(mid - 1).quality < target) {
            return c;
        }
        return bs(list, l, mid, target);
    } else {
        // 타켓의 퀄리티보다 현재의 퀄리티가 낮으니 오른쪽으로 이동한다.
        return bs(list, mid + 1, r, target);
    }
}
~~~

4. 부품 quality의 최솟값과 최댓값 사이에서 가격을 맞출 수 있는 최대 퀄리티를 이분탐색을 이용하여 뽑는다.

~~~JAVA
static int bs(int l, int r, int target) {

    int mid = (l + r) / 2;
    int[] pq = getPriceQuality(mid);
    int price = pq[0];
    int quality = pq[1];

    if (l == r) {
        return quality;
    }

    if (price <= target) {
        int[] npq = getPriceQuality(mid + 1);
        if (npq[0] > target) {
            return quality;
        }
        return bs(mid + 1, r, target);
    } else {
        // 타켓보다 큰 경우이니 왼쪽으로 이동
        return bs(l, mid, target);
    }


}

static int[] getPriceQuality(int id) {
    int price = 0;
    int quality = Integer.MAX_VALUE;

    for (String key : map.keySet()) {
        List<Component> tmp = map.get(key);
        if (tmp.size() == 1) {
            Component c = tmp.get(0);
            price += c.price;
            quality = Math.min(quality, c.quality);
        } else {
            Component c = bs(tmp, 0, tmp.size() - 1, id);
            price += c.price;
            quality = Math.min(quality, c.quality);
        }
    }

    return new int[]{price, quality};
} 
~~~


