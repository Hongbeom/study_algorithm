## 백준 2831 - [댄스 파티](https://www.acmicpc.net/problem/2831)

### 풀이법

1. 큰키 여자 - 작은키 남자 / 작은키 여자 - 큰기 남자 로 구분하여 정렬 후 짝을 맞춰준다.
```JAVA
List<Integer> tallBoy = new ArrayList<>();
List<Integer> shortBoy = new ArrayList<>();

List<Integer> tallGirl = new ArrayList<>();
List<Integer> shortGirl = new ArrayList<>();

StringTokenizer st = new StringTokenizer(br.readLine());

for (int i = 0; i < n; i++) {
    int height = Integer.parseInt(st.nextToken());
    if (height < 0) {
        shortBoy.add(height * -1);
    } else {
        tallBoy.add(height);
    }
}

st = new StringTokenizer(br.readLine());
for (int i = 0; i < n; i++) {
    int height = Integer.parseInt(st.nextToken());
    if (height < 0) {
        shortGirl.add(height * -1);
    } else {
        tallGirl.add(height);
    }
}
int answer = 0;
// tall boy - short Girl
Collections.sort(tallBoy);
Collections.sort(shortGirl);
int bid = 0;
int gid = 0;

while (bid < tallBoy.size() && gid < shortGirl.size()) {
    int boy = tallBoy.get(bid);
    int girl = shortGirl.get(gid);

    if (boy < girl) {
        bid++;
        gid++;
        answer++;
    } else {
        gid++;
    }
}

// shortBoy - tall girl

// tall boy - short Girl
Collections.sort(tallGirl);
Collections.sort(shortBoy);
bid = 0;
gid = 0;

while (bid < shortBoy.size() && gid < tallGirl.size()) {
    int boy = shortBoy.get(bid);
    int girl = tallGirl.get(gid);

    if (girl < boy) {
        bid++;
        gid++;
        answer++;
    } else {
        bid++;
    }
}

System.out.println(answer);
```

