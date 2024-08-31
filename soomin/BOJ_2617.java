import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2617 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 1. 입력: 인접행렬
        // bead[b][a] = 1이면, 구슬 a가 구슬 b보다 큰 것을 의미
        // bead 배열을 boolean으로도 해도 되는데 int로 한 이유는 각 원소가 몇개의 원소보다 큰지 작은지 탐색할 때 if문을 사용하지 않고 그냥 더해서 계산할라구 int 배열 썼습니다.
        int[][] bead = new int[N + 1][N + 1];
        int limit = (N + 1) / 2;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            bead[b][a] = 1; // a -> b 연결 ( 작은쪽에서 큰쪽으로 )
        }

        // 2. 플로이드-워셜을 사용해 모든 쌍의 최당 경로 여부를 계산
        // 경유해서 가는 경우가 있다면 1로 갱신합니다.
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (bead[i][k] == 1 && bead[k][j] == 1) bead[i][j] = 1;
                }
            }

        }

        // 3. 나보다 크거나 작은 구슬이 몇개인지 탐색합니다.
        // N+1/2 이상이라면 절대 중간값이 될 수 없으므로 제거 해야합니다.
        int remove = 0;
        for (int i = 1; i <= N; i++) {
            int sumSmall = 0; // 나보다 작은 구슬의 개수
            int sumBig = 0; // 나보다 큰 구슬의 개수
            for (int j = 1; j <= N; j++) {
                if(i == j) continue;
                sumBig += bead[j][i];
                sumSmall += bead[i][j];
            }

            // 조건을 만족하면 제거
            if (sumSmall >= limit || sumBig >= limit) remove++;
        }


        System.out.println(remove);
    }
}
