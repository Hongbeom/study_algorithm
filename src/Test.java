import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n1 = 0;
        int n2 = 0;

        int id = 0;
        for (char c : st.nextToken().toCharArray()) {
            n1 += Math.pow(10, id++) * (c - '0');
        }

        id = 0;
        for (char c : st.nextToken().toCharArray()) {
            n2 += Math.pow(10, id++) * (c - '0');
        }

        if (n1 > n2) {
            System.out.println(n1);
        } else {
            System.out.println(n2);
        }


    }
}
