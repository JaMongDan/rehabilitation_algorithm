package test_0913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_20922_겹치는건싫어 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] numCnt = new int[100001];

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int ans = 0;

        st=new StringTokenizer(br.readLine(), " ");
        int[] numbers = new int[n];

        for(int i=0;i<n;i++){
            numbers[i]=Integer.parseInt(st.nextToken());
        }

        int start=0;
        int end=0;

        while (end<n){
            while(end<n && numCnt[numbers[end]]+1<=m){
                numCnt[numbers[end]]++;
                end++;
            }
            ans=Math.max(ans,end-start);
            numCnt[numbers[start]]--;
            start++;
        }


        System.out.println(ans);

    }
}
