## 백준 7662 - [이중 우선순위 큐](https://www.acmicpc.net/problem/7662)

### 초기 잘못된 접근

1. 초기에 max heap, min heap을 이용해 해결하려함.
- max heap과 min heap에서 각각 pop 한것을 remove 하는데서 시간초과가 발생.

2. 이진 검색트리를 이용.
- max, min 값만 삭제를 잘 해준다.
- max, min 연산을 잘못 구현하여 계속 틀림.

### 풀이법

1. 이진 검색트리 이용.
- 중복된 값이 존재할 수 있으므로 노드에는 value와 count가 존재함.

~~~JAVA
static class BST {
    long value;
    int count;

    BST parent;
    BST left;
    BST right;

    BST(long value) {
        this.value = value;
        this.count = 1;
    }

    BST(long value, BST parent) {
        this.value = value;
        this.count = 1;
        this.parent = parent;
    }

    void add(long v) {
        BST t = this;
        while (true) {
            if (v > t.value) {
                if (t.right == null) {
                    t.right = new BST(v, t);
                    break;
                }
                t = t.right;
            } else if (v < t.value) {
                if (t.left == null) {
                    t.left = new BST(v, t);
                    break;
                }
                t = t.left;
            } else {
                t.count++;
                break;
            }
        }
    }

    BST removeMax() {
        BST t = this;
        while (t.right != null) {
            t = t.right;
        }
        t.count--;
        if (t.count == 0) {
            if (t.parent == null) {
                if (t.left == null) {
                    return null;
                } else {
                    t.left.parent = null;
                    return t.left;
                }
            } else {
                if (t.left != null) {
                    t.parent.right = t.left;
                    t.left.parent = t.parent;
                } else {
                    t.parent.right = null;
                }

            }
        }
        return this;
    }

    BST removeMin() {
        BST t = this;

        while (t.left != null) {
            t = t.left;
        }
        t.count--;
        if (t.count == 0) {
            if (t.parent == null) {
                if (t.right == null) {
                    return null;
                } else {
                    t.right.parent = null;
                    return t.right;
                }
            } else {
                if(t.right != null){
                    t.parent.left = t.right;
                    t.right.parent = t.parent;
                }else{
                    t.parent.left = null;
                }
            }
        }
        return this;
    }

    long getMax() {
        BST t = this;
        while (t.right != null) {
            t = t.right;
        }

        return t.value;
    }

    long getMin() {
        BST t = this;
        while (t.left != null) {
            t = t.left;
        }
        return t.value;
    }
}
~~~

- 삽입(add)
    - 같은값이 트리에 존재하면 그 노드의 count만 ++
    - 이외의 경우는 계속 노드를 만들어 나간다.
    
- remove max
    - max값을 찾아 count--
    - 감소시킨 count가 0이라면 노드를 없애준다.
    - 만약 이 노드가 root인 경우
        - left child가 존재하는 경우 left child가 root가 된다.
        - left child가 존재하지 않는 경우 root 는 null (값이 다 삭제된 경우)
    - 이 노드가 root가 아닌 경우
        - max 노드의 left child가 존재한다면 부모 노드의 right child로 연결시켜줌.
    
- remove min
    - min값을 찾아 count--
    - 감소시킨 count가 0이라면 노드를 없애준다.
    - 만약 이 노드가 root인 경우
        - right child가 존재하는 경우 right child가 root가 된다.
        - right child가 존재하지 않는 경우 root 는 null (값이 다 삭제된 경우)
    - 이 노드가 root가 아닌 경우
        - min 노드의 right child가 존재한다면 부모 노드의 left child로 연결시켜줌.