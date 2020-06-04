package boj.n3111;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N3111 {

    static char[] A;
    static char[] T;
    static char FIRST;
    static char LAST;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = br.readLine().toCharArray();
        T = br.readLine().toCharArray();
        FIRST = A[0];
        LAST = A[A.length - 1];

        int start = 0;
        int end = T.length - 1;
        Node fromStart = null;
        Node fromEnd = null;
        root:
        while (true) {

            while (true) {
                char c = T[start];
                if(start > end){
                    if(fromEnd == null){
                        break root;
                    }
                    c = fromEnd.c;
                    fromEnd = fromEnd.next;
                    if(fromEnd != null){
                        fromEnd.prev = null;
                    }
                }
                if(fromStart == null){
                    fromStart = new Node(c, 0, 0);
                }else{
                    Node startNext = new Node(c, fromStart.sid + 1, 0);
                    fromStart.next = startNext;
                    startNext.prev = fromStart;
                    fromStart = startNext;
                }

                if(c == LAST && fromStart.sid + 1 >= A.length){
                    Node prevFromStart = fromStart.leftCheck();
                    if(fromStart != prevFromStart){
                        fromStart = prevFromStart;
                        start++;
                        break;
                    }
                }
                start++;
            }

            while (true) {
                char c = T[end];
                if(end < start){
                    if(fromStart == null) {
                        break root;
                    }
                    c = fromStart.c;
                    fromStart = fromStart.prev;
                    if(fromStart != null){
                        fromStart.next = null;
                    }
                }
                if(fromEnd == null){
                    fromEnd = new Node(c, 0, 0);
                }else{
                    Node endPrev = new Node(c, 0, fromEnd.eid + 1);
                    fromEnd.prev = endPrev;
                    endPrev.next = fromEnd;
                    fromEnd = endPrev;
                }

                if(c == FIRST && fromEnd.eid + 1 >= A.length){
                    Node nextFromEnd = fromEnd.rightCheck();
                    if(fromEnd != nextFromEnd){
                        fromEnd = nextFromEnd;
                        end--;
                        break;
                    }
                }
                end--;
            }
        }

        StringBuilder sb = new StringBuilder();
        while(fromStart != null){
            sb.append(fromStart.c);
            fromStart = fromStart.prev;
        }
        sb.reverse();
        while(fromEnd != null){
            sb.append(fromEnd.c);
            fromEnd = fromEnd.next;
        }
        System.out.println(sb.toString());

    }

    static class Node {
        char c;
        int sid;
        int eid;
        Node prev;
        Node next;

        public Node(char c, int sid, int eid) {
            this.c = c;
            this.sid = sid;
            this.eid = eid;
        }

        public Node leftCheck(){
            Node re = this.prev;
            int cid = A.length - 2;
            for(int i = cid; i >= 0; i--){
                if(re.c != A[i]){
                    // 아닌 경우 자기를 리턴하자.
                    return this;
                }
                re = re.prev;
            }
            if(re != null) {
                re.next = null;
            }
            return re;
        }

        public Node rightCheck() {
            Node re = this.next;
            int cid = 1;
            for(int i = cid; i < A.length; i++){
                if(re.c != A[i]){
                    return this;
                }
                re = re.next;
            }
            if(re != null){
                re.prev = null;
            }
            return re;
        }
    }


}
