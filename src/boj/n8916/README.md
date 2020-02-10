## 백준 8916 - [이진 검색 트리](https://www.acmicpc.net/problem/8916)
 
공식을 떠오르기 까지 많이 걸린 문제... 답을 찾아 해결하였다.


### 풀이법
 
1. 생각해 보면, 트리의 왼쪽자식의 원소는 트리의 오른쪽 자식의 어디에서나 등장해도 트리의 구조가 바뀌지 않는다.
- 2-1-4-3-5
- 2-4-1-3-5
- 2-4-3-1-5
- 2-4-3-5-1

2. 루트에서 부터 말단 노드까지 nCr 을 계속 곱해주면 된다.
- n: 왼쪽 자식의 수 + 오른쪽 자식의 수
- r: 왼쪽 자식의 수
 
 ```JAVA
static class Tree {

    boolean root;
    int value;
    int lCnt;
    int rCnt;
    Tree leftNode;
    Tree rightNode;

    Tree(boolean root, int value) {
        this.root = root;
        this.value = value;
        this.lCnt = 0;
        this.rCnt = 0;
    }

    void add(int value) {
        Tree t = this;

        while (true) {
            if (t.value > value) {
                t.lCnt++;
                if (t.leftNode == null) {
                    t.leftNode = new Tree(false, value);
                    break;
                } else {
                    t = t.leftNode;
                }

            } else {
                t.rCnt++;
                if (t.rightNode == null) {
                    t.rightNode = new Tree(false, value);
                    break;
                } else {
                    t = t.rightNode;
                }
            }
        }
    }


}

static void getCandidates(Tree tree) {

    if (tree == null) {
        return;
    }

    ANS = (ANS * comb(tree.lCnt + tree.rCnt, tree.lCnt)) % 9999991;
    getCandidates(tree.leftNode);
    getCandidates(tree.rightNode);
}
```