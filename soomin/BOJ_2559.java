import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2559 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken()); // 연속된 k일

        int[] sum = new int[N+1]; // 누적합을 구하기 위한 배열 (0일의 누적합은 0으로 초기화)

        // 입력 및 누적합을 구해서 sum 배열에 저장합니다.
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=N; i++){
            sum[i] += sum[i-1] + Integer.parseInt(st.nextToken()); // 이전 일까지의 합 + 현재 값
        }

        // 연속된 k일 동안의 온도합을 구하는 반복문
        // 누적합을 이미 구했으므로 탐색은 K-1부터 시작
        int max = -10000001; // 문제에서 주어진 최소값보다 작은 초기값
        for(int i = K; i<=N; i++){
            max = Math.max(max, sum[i] - sum[i-K]); // K일 전 누적합을 빼서 K일간의 합 계산
        }

        System.out.println(max);

    }
}
