package sw.sw4229;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class SW4229 {

    public static void main(String[] args) throws IOException, ParseException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date criteria = format.parse("2011-11-11 11:11:00");

        int TC = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int tc = 0; tc < TC; tc++) {
            st = new StringTokenizer(br.readLine());
            Date date = format.parse("2011-11" + "-" + st.nextToken() + " " + st.nextToken() + ":" + st.nextToken() + ":00");
            long minute = (date.getTime() - criteria.getTime()) / 60000;

            if (minute >= 0) {
                System.out.println("#" + (tc + 1) + " " + minute);
            } else {
                System.out.println("#" + (tc + 1) + " " + -1);
            }
        }
    }
}