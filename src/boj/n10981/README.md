## 백준 10981 - [HEADING TO WORLD FINALS](https://www.acmicpc.net/problem/10981)

### 풀이법

1. HashMap에 팀 객체를 저장. 각 대학에서 가장 뛰어난 팀만을 저장.
~~~JAVA
static class Team {
    String university;
    String name;
    int problem;
    int penalty;
    int id;

    public Team(String university, String name, int problem, int penalty, int id) {
        this.university = university;
        this.name = name;
        this.problem = problem;
        this.penalty = penalty;
        this.id = id;
    }

    boolean compare(int problem, int penalty) {

        if (problem > this.problem) {
            return true;
        } else if (problem == this.problem) {
            if (penalty < this.penalty) {
                return true;
            }
        }
        return false;
    }
}
~~~



~~~JAVA
Map<String, Team> map = new HashMap<>();
List<Team> list = new ArrayList<>();
int uid = 0;
for (int i = 0; i < n; i++) {
    st = new StringTokenizer(br.readLine());
    String university = st.nextToken();
    String teamName = st.nextToken();
    int problem = Integer.parseInt(st.nextToken());
    int penalty = Integer.parseInt(st.nextToken());

    Team compareTeam = map.getOrDefault(university, null);

    if (compareTeam == null) {
        Team team = new Team(university, teamName, problem, penalty, uid);
        list.add(team);
        uid++;
        map.put(university, team);
    } else if (compareTeam.compare(problem, penalty)) {
        int id = compareTeam.id;
        Team team = new Team(university, teamName, problem, penalty, id);
        map.put(university, team);
        list.set(id, team);
    }
}

list.sort((o1, o2) -> {
    if (o1.problem > o2.problem) {
        return -1;
    } else if (o1.problem < o2.problem) {
        return 1;
    } else {
        if (o1.penalty < o2.penalty) {
            return -1;
        } else if (o1.penalty > o2.penalty) {
            return 1;
        }
    }
    return 0;
});
~~~

- 인풋으로 들어온 팀을 대학교를 기준으로 해쉬맵에 저장.
- 맵에 해당 대학이 존재한다면 어느 팀이 더 뛰어난 팀이지 판단 후 더욱 뛰어난 팀을 맵에 저장.
- 해쉬맵에 저장된 대학들은 똑같이 리스트에 저장되어 있음.
- 인풋을 다 받았다면 리스트를 정렬해 상위 k개의 팀 이름들을 출력해줌.

