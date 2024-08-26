import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16507 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        // 입력 & 누적합
        // 각 행의 누적값과 각 열의 누적값을 arr에 저장합니당 (중복되는 부분은 빼주기)
        int[][] arr = new int[R + 1][C + 1];
        for (int i = 1; i <= R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= C; j++) {
                arr[i][j] = arr[i][j - 1] + arr[i-1][j] - arr[i-1][j-1] + Integer.parseInt(st.nextToken()); 
            }
        }

        // arr를 탐색하면서 직사각형의 평균을 출력합니당
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {

            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            int total = (r2 - r1 + 1) * (c2 - c1 + 1);
            int sum = arr[r2][c2] - arr[r2][c1 - 1] - arr[r1-1][c2] + arr[r1-1][c1-1];

            sb.append(sum/total).append("\n");
        }
        System.out.println(sb);
    }
}
