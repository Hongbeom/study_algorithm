## 백준 11656 - [접미사 배열](https://www.acmicpc.net/problem/11656)

### 풀이법
1. 모든 접미사를 StringBuilder를 이용해 구함.
2. sort후 출력.

~~~JAVA
public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    char[] word = br.readLine().toCharArray();
    StringBuilder sb = new StringBuilder();
    List<String> list = new ArrayList<>();
    for (int i = word.length - 1; i >= 0; i--) {
        sb.append(word[i]);
        list.add(sb.reverse().toString());
        sb.reverse();
    }
    Collections.sort(list);
    for(String s : list){
        System.out.println(s);
    }

}
~~~

