import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class BOJ_10653 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Point[] points = new Point[N]; // 체크포인트 저장하는 배열
        int[][] dp = new int[K+1][N]; // N번째 체크포인트까지 K번 건너뛰었을 때, 거리의 최솟값을 저장하는 배열

        // 1. 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(x, y);
        }

        // 2. 메모라이제이션을 이용해 최소 거리를 구하는 과정
        // 점화식: dp[j][i] = dp[j][i-1] + i-1번째 체크포인트와 i번째 체크포인트의 거리 (i > j)
        // i-1번째 체크포인트까지 j번 건너뛴 상태에서 최솟값 vs i-2번째 체크포인트까지 j-1번 건너뛴 상태에서 최솟값 vs ...
        // 점진적으로 i와 j의 값을 감소시키면서 최솟값을 구합니다.
        int min;
        for (int i = 1; i < N; i++) { // 첫번째 체크포인트에서 시작하기 때문에 계산할 필요가 없습니다.
            for (int j = 0; j <= K; j++) {
                min = Integer.MAX_VALUE; // 최솟값을 구하기 위해서 맨 처음 값은 최댓값과 비교한다.
                for (int k = 0; k <= j; k++) { // k는 i와 j를 감소시키기 위한 변수이다. 건너뛴 횟수에 따라 조정해야하기 때문
                    int index = i - 1 - k;
                    if(index < 0) continue; // points의 배열 범위를 벗어나지 않기 위함임
                    min = Math.min(dp[j - k][index] + calDistance(points[i], points[index]), min);
                }
                dp[j][i] = min; // 최소 거리를 배열에 저장함다
            }
        }

        // 3. 마지막으로 dp[][N-1] 중에서 최솟값을 구하기
        // 마지막 체크포인트에 도달하는 모든 가능한 최솟값이므로 이 중에서 가장 짧은 거리를 탐색해야합니다.
        // k번 건너뛰기가 반드시 k번을 모두 사용하는 것이 아닌 최대 k번 건너뛸 수 있다는 뜻입니다. 휴 이것 때매 헤맷슴다
        int answer = dp[0][N-1]; // 건너뛰기를 아예 하지 않은 경우 가장 큰 값을 가지므로 초기값으로 설정
        for(int i = 1; i<=K; i++) {
            if(dp[i][N-1] < 0) continue; // 유효하지 않은 경우 건너 뜁니다.
            answer = Math.min(dp[i][N-1], answer);
        }

        System.out.println(answer);
    }


    /***
     * 멘허튼 거리를 구하는 메서드
     * @param a 체크포인트 A
     * @param b 체크포인트 B
     * @return 두 포인트 사이의 거리를 반환
     */
    private static int calDistance(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
}
