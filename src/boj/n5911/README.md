## 백준 5911 - [선물](https://www.acmicpc.net/problem/5911)

### 풀이법

1. N이 최대 1000 이므로, O(N^2logN) 방식으로 풀이
  - 각 한개당 반값쿠폰을 하는 경우를 모두 본다.

```JAVA
 public static void main(String[] args) throws IOException {                            
                                                                                        
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));          
     StringTokenizer st = new StringTokenizer(br.readLine());                           
                                                                                        
     int n = Integer.parseInt(st.nextToken());                                          
     long b = Long.parseLong(st.nextToken());                                           
                                                                                        
     int[] p = new int[n];                                                              
     int[] s = new int[n];                                                              
     int[] sum = new int[n];                                                            
     for (int i = 0; i < n; i++) {                                                      
         st = new StringTokenizer(br.readLine());                                       
         p[i] = Integer.parseInt(st.nextToken());                                       
         s[i] = Integer.parseInt(st.nextToken());                                       
         sum[i] = p[i] + s[i];                                                          
     }                                                                                  
     int ans = -1;                                                                      
                                                                                        
     for (int i = 0; i < n; i++) {                                                      
         int[] ts = sum.clone();                                                        
         ts[i] = p[i] / 2 + s[i];                                                       
         Arrays.sort(ts);                                                               
         int tCnt = 0;                                                                  
         long tSum = 0;                                                                 
         for (int ss : ts) {                                                            
             if (tSum + ss > b) {                                                       
                 break;                                                                 
             }                                                                          
             tSum += ss;                                                                
             tCnt++;                                                                    
         }                                                                              
         ans = Math.max(ans, tCnt);                                                     
     }                                                                                  
     System.out.println(ans);                                                           
}                                                                                      
```

