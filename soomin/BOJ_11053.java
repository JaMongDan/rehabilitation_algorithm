import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11053 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        int[] dp = new int[N];

        // 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;

            // 부분 수열의 길이를 저장
            // 해당 인덱스의 수열 원소보다 작은 값이 몇개 인지 탐색하는 반복문
            for(int j = 0; j<i; j++) {
                if(arr[j] >= arr[i]) continue;

                int target = dp[j] + 1;
                if(dp[i] >= target) continue; // 안하면 무조건 값이 갱신되어 최대길이가 보장되지 않는다.
                dp[i] = target; // 이전 부분수열에 i번째 원소가 추가되었기 때문에 +1
            }
        }

        // 최댓길이 구하기
        int max = 0;
        for(int i = 0; i<N; i++){
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);

    }
}
