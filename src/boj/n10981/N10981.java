package boj.n10981;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class N10981 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
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

        for(int i = 0; i < k; i++){
            System.out.println(list.get(i).name);
        }
    }


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
}
