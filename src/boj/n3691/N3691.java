package boj.n3691;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class N3691 {

    static Map<String, List<Component>> map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());

            int l = Integer.MAX_VALUE;
            int r = Integer.MIN_VALUE;

            int n = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map = new HashMap<>();

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

            System.out.println(bs(l, r, b));
        }

    }

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

}
