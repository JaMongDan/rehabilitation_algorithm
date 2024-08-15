import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] num = new int[N];
        int[] dp = new int[N];
        int[] pre = new int[N]; // 역추적을 위한 이전 원소의 index를 저장합니다.

        int max = 0;
        int index = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());

        /*
         * 입력과 동시에 각 원소가 포함된 부분 수열의 길이를 구하고 갱신합니다.
         * 가장 긴 증가하는 부분 수열을 출력하기 위해서 가장 긴 길이 max와 이때 마지막으로 포함되는 원소의 index를 저장합니다.
         * 해당 index를 가지고 역추적을하여 부분 수열을 출력합니다.
         * pre[] 배열에 이전 원소의 index를 저장합니다.
         */

        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                if (num[i] <= num[j]) continue;
                if (dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    pre[i] = j; // 이전 원소의 index를 저장! 역추적을 하기 위함입니다.
                }
            }

            if (dp[i] > max) {
                max = dp[i];
                index = i;
            }

        }

        /*
         * pre에 이전 원소들의 index를 적어둔 것을 역추적하여
         * 부분 수열의 값을 출력했습니다.
         *
         * 자료구조를 처음에는 list를 생각했는데 이러면 reverse 정렬을 또 해줘야한다는 점 때문에 배열로 선택했습니다.
         * 새로 저장할 배열 인덱스를 max - 1 ~ 0 순으로 돌면서 값을 저장하면 역정렬이 되니까유!
         */
        StringBuilder sb = new StringBuilder();
        int[] tmp = new int[max];
        for (int i = max - 1; i >= 0; i--) {
            tmp[i] = num[index];
            index = pre[index];
        }

        sb.append(max);
        sb.append("\n");
        for (int n : tmp) {
            sb.append(n).append(" ");
        }

        System.out.println(sb);
    }
}
