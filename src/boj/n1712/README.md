## 백준 1712 - [손익분기점](https://www.acmicpc.net/problem/1712)
 
 
 ### 풀이법
 
 1. 지식을 이용.
 
 ```JAVA
public static void main(String[] args) throws IOException {

   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   StringTokenizer st = new StringTokenizer(br.readLine());

   int a = Integer.parseInt(st.nextToken());
   int b = Integer.parseInt(st.nextToken());
   int c = Integer.parseInt(st.nextToken());

   if (c <= b) {
   System.out.println(-1);
   return;
   }

   int point = a / (c - b);
   System.out.println(point + 1);
}
```