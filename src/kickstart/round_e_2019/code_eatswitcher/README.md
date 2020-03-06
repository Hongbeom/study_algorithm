## kick start 2019 round E - [Code-Eat Switcher](https://codingcompetitions.withgoogle.com/kickstart/round/0000000000050edb?show=progress)

### 초기 잘못된 접근법.

1. 슬롯에서의 C(coding)의 역행렬을 구할 수 있으면 된다고 생각.
- 하지만 바보 같은 방법... 그런건 존재하지 않았다...


### 풀이법

1. 먼저 슬롯에 대한 정보를 코딩/이팅 으로 정렬을 한다.
- 코딩/이팅 은 이팅 1 unit을 얻기 위해 포기해야하는 코딩 unit이다.
- 코딩/이팅을 기준으로 오름차순으로 정렬하면 먼저 이팅 선택해야하는 슬롯들을 알 수 있다.

~~~JAVA
Arrays.sort(slots, (o1, o2) -> {
    if (o1[0] / (double) o1[1] < o2[0] / (double) o2[1]) {
        return -1;
    } else if (o1[0] / (double) o1[1] > o2[0] / (double) o2[1]) {
        return 1;
    }
    return 0;
});
~~~

2. 정렬된 배열을 탐색해 각 슬롯당 cumlative한 코딩과 이팅을 표시해 준다.
- 코딩 cumulative - 배열 끝에서 배열 첫번째로 cumulative
- 이팅 cumulative - 배열 첫번째에서 끝으로 cumulative 

~~~JAVA
for (int i = 0; i < s; i++) {
    if (i == 0) {
        slots[i][3] = slots[i][1];
        slots[s - 1 - i][2] = slots[s - 1 - i][0];
    } else {
        slots[i][3] = slots[i][1] + slots[i - 1][3];
        slots[s - 1 - i][2] = slots[s - 1 - i][0] + slots[s - i][2];
    }
}
~~~

3. 이팅 cumulative를 이진탐색하여 어느 슬롯부터 코딩에 더 할애해야 하는지 알 수 있다.
- 나머지는 그 다음의 코딩 cumulative를 이용하여 전체 코딩 할당량을 채울 수 있는지 판별 가능.

~~~JAVA
static int bs(int[][] slots, int left, int right, int target) {
    int mid = (left + right) / 2;
    int compare = slots[mid][3];

    if (compare > target) {
        if (mid == 0 || slots[mid - 1][3] <= target) {
            return mid;
        }
        return bs(slots, left, mid, target);
    } else {
        return bs(slots, mid + 1, right, target);
    }
}
~~~

~~~JAVA
for (int i = 0; i < d; i++) {
    st = new StringTokenizer(br.readLine());
    int coding = Integer.parseInt(st.nextToken());
    int eating = Integer.parseInt(st.nextToken());

    if (eating > slots[s - 1][3] || coding > slots[0][2]) {
        sb.append("N");
        continue;
    } else if (eating == slots[s - 1][3]) {
        if (coding == 0) {
            sb.append("Y");
        } else {
            sb.append("N");
        }
        continue;
    }

    int id;

    if (eating < slots[0][3]) {
        id = 0;
    } else if (eating == slots[0][3]) {
        id = 1;
        eating = 0;
    } else {
        id = bs(slots, 0, s - 1, eating);
        eating -= slots[id - 1][3];
    }

    double f;
    if (eating != 0) {
        f = 1d - (eating / (double) slots[id][1]);
    } else {
        f = 1;
    }

    if (id == s - 1) {
        if (f * slots[id][0] >= coding) {
            sb.append("Y");
        } else {
            sb.append("N");
        }

    } else {
        if (f * slots[id][0] + slots[id + 1][2] >= coding) {
            sb.append("Y");
        } else {
            sb.append("N");
        }
    }
}
~~~