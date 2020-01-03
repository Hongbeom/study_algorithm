import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = 1;
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int l = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            if(l==0 && p == 0 && v == 0){
                return;
            }

            int d = v/p;
            int r = v%p;

            if(r > l){
                System.out.println("Case "+ c +": " + (d*l + l));
            }else{
                System.out.println("Case "+ c +": " + (d*l + r));
            }
            c++;
        }

    }
}
