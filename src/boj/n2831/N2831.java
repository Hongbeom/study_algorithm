package boj.n2831;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class N2831 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

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
    }
}
