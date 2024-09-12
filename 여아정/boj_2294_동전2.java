package test_0913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2294_동전2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] dp = new int[k + 1];
        int[] num = new int[n];

        Arrays.fill(dp, Integer.MAX_VALUE - 1);//dp 배열을 최대값으로 둔다

        for (int i = 0; i < n; i++)
            num[i] = Integer.parseInt(br.readLine());//동전 정보 배열에 받기

        dp[0] = 0;//0번째 값은 0으로 초기화

        for (int i = 0; i < n; i++) {
            for (int j = num[i]; j <= k; j++) {
                dp[j] = Integer.min(dp[j], dp[j - num[i]] + 1);//중복이 아닌 최소를 구해야하므로 기존의 값과 동전가치를 뺀 값+1 중 최소인걸로 계속 갱신한다.
            }
        }

        if (dp[k] == Integer.MAX_VALUE - 1)//초기값 그대로 나온다면 그 값을 만들 수 없다는 뜻
            dp[k] = -1;//-1로 둔다

        System.out.println(dp[k]);

    }
}
