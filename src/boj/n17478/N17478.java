package boj.n17478;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N17478 {

    static final String PRE = "____";
    static StringBuilder ANS = new StringBuilder();
    static final String[] SEN = new String[]{
            "\"재귀함수가 뭔가요?\"\n",
            "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n",
            "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n",
            "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n",
            "\"재귀함수는 자기 자신을 호출하는 함수라네\"\n",
            "라고 답변하였지.\n"
    };

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ANS.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
        ans(0, n);

        System.out.print(ANS.toString());
    }

    static void ans(int count, int target) {
        append(count);
        ANS.append(SEN[0]);
        if (count == target) {
            append(count);
            ANS.append(SEN[4]);
        } else {
            for (int i = 1; i <= 3; i++) {
                append(count);
                ANS.append(SEN[i]);
            }
            ans(count + 1, target);
        }
        append(count);
        ANS.append(SEN[5]);

    }

    static void append(int n) {
        for (int i = 0; i < n; i++) {
            ANS.append(PRE);
        }
    }
}
