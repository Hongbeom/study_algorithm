## 백준 7432 - [디스크 트리](https://www.acmicpc.net/problem/7432)

### 풀이법 

1. 서브 디렉터리를 보관하는 리스트, 서브 디렉터리의 인덱스를 보관하는 해쉬맵을 이용하여 구현.
2. 출력은 정렬 후 dfs를 이용. 
```JAVA
public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine());


    Directory root = new Directory(null);

    for (int i = 0; i < n; i++) {
        String[] names = br.readLine().split("\\\\");
        root.add(names);
    }

    print(root, "");

}

static void print(Directory current, String prefix){
    if(current.children.size() == 0){
        return;
    }
    Collections.sort(current.children);
    for(Directory child : current.children){
        System.out.println(prefix + child.name);
        print(child, prefix + " ");
    }
}

static Directory createSub(String[] names, int from){

    Directory subRoot = new Directory(names[from]);
    Directory current = subRoot;

    for(int i = from + 1; i < names.length; i++) {
        current.map.put(names[i], current.children.size());
        Directory child = new Directory(names[i]);
        current.children.add(child);
        current = child;
    }

    return subRoot;
}



static class Directory implements Comparable<Directory> {


    String name;
    List<Directory> children;
    HashMap<String, Integer> map;

    public Directory(String name) {
        this.name = name;
        this.children = new ArrayList<>();
        this.map = new HashMap<>();
    }




    void add(String[] names) {
        Directory current = this;

        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            int id = current.map.getOrDefault(name, -1);
            if (id == -1) {
                Directory sub = createSub(names, i);
                current.map.put(names[i], current.children.size());
                current.children.add(sub);
                break;
            }
            current = current.children.get(id);
        }

    }

    @Override
    public int compareTo(Directory o) {
        return this.name.compareTo(o.name);
    }
}
```
