package boj.n3030;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N3030 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringTokenizer pst;

        String p1 = st.nextToken();
        String p2 = st.nextToken();

        int n = Integer.parseInt(br.readLine());
        boolean fed = false;
        int sp = 0;
        if (p1.equals("federer")) {
            fed = true;
            sp = 1;

        } else if (p2.equals("federer")) {
            fed = true;
            sp = 2;
        }

        set:
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int w1 = 0;
            int w2 = 0;
            int set = 1;
            while (st.hasMoreTokens()) {
                boolean changed = false;
                pst = new StringTokenizer(st.nextToken(), ":");
                int g1 = Integer.parseInt(pst.nextToken());
                int g2 = Integer.parseInt(pst.nextToken());

                if (fed) {
                    if (sp == 1) {
                        if (g1 <= g2) {
                            no();
                            continue set;
                        }
                    } else {
                        if (g2 <= g1) {
                            no();
                            continue set;
                        }
                    }
                }

                if (g1 == g2) {
                    no();
                    continue set;
                } else if (g1 < g2) {
                    int tm = g1;
                    g1 = g2;
                    g2 = tm;
                    changed = true;
                }

                if (set <= 2) {
                    if (g1 < 6 || g2 > 6 || g1 > 7) {
                        no();
                        continue set;
                    }
                    if (g2 > 4 && g1 != 7) {
                        no();
                        continue set;
                    }
                } else {

                    if (g1 < 6) {
                        no();
                        continue set;
                    }

                    if (g1 - g2 < 2) {
                        no();
                        continue set;
                    }
                }

                if (changed) {
                    if (++w2 == 2 && st.hasMoreTokens()) {
                        no();
                        continue set;
                    }
                } else {
                    if (++w1 == 2 && st.hasMoreTokens()) {
                        no();
                        continue set;
                    }
                }
                set++;
            }


            if (w2 < 2 && w1 < 2) {
                no();
            } else if (w2 == 2 && w1 == 2) {
                no();
            }else{
                yes();
            }


        }

    }


    static void yes() {
        System.out.println("da");
    }

    static void no() {
        System.out.println("ne");
    }
}
