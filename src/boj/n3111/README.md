## 백준 3111 - [검열](https://www.acmicpc.net/problem/3111)

### 풀이법

1. doubly-linked list 사용.
2. fromStart, fromEnd 의 두 노드를 사용한다.
  - 검열은 왼쪽부터 시작된다.
  - 첫 글자(start) 부터 fromStart에 순서대로 저장한다.
  - 저장할때 첫 글자가 A의 마지막 글자 이고, fromStart 이전의 노드들의 갯수가 A.length - 1 보다 크면 그 글자인지 검사한다.
  - 검사하여 그 글자가 맞다면 그 노드들을 삭제하고 첫번째 글자의 이전 노드를 fromStart에 저장해 둔다.
  - 삭제가 완료되면 똑같은 방법으로 fromEnd를 사용하여 글자의 마지막(end) 부터 저장해 나가고 검사를 한다.
  - 만약 start와 end가 교차가되는 시점에는 T의 모든 글자를 우선 탐색한 것이다.
  - 그러므로 start와 end가 교차될때는 각각 fromStart와 fromEnd에 저장되어 있는 글자를 가져와 검사를 계속한다.
  - 둘중의 하나가 비워질때까지 계속 검열한다.
  
  
~~~JAVA
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
~~~
