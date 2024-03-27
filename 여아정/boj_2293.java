package test_0913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2293 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
         int n=Integer.parseInt(st.nextToken());
         int k=Integer.parseInt(st.nextToken());

         int[]dp=new int[k+1];
         int[] num=new int[n];

         for(int i=0;i<n;i++){
            num[i]=   Integer.parseInt(br.readLine());
         }

         dp[0]=1;

         for(int i=0;i<n;i++){
             for(int j=1;j<=k;j++){
                 if(j>=num[i])
                     dp[j]=dp[j]+dp[j-num[i]];//j 값을 구성할 수 있는 경우의 수
             }
         }

        System.out.println(dp[k]);

    }
}
