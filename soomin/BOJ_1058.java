import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1058 {

    static final int INF = 2500; // 연결 안된

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 1. 인접행렬 초기화
        // 다른 노드와 연결되지 않았다면 INF로 초기화 => 연결되지 않은 곳은 선택되지 않도록 하기 위해서
        int[][] friend = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(friend[i], INF);
            friend[i][i] = 0;
        }

        // 2. 입력
        // 연결된 노드를 표시
        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    continue;
                }

                if (line[j] == 'Y')
                    friend[i][j] = 1;
            }
        }

        // 3. 플로이드 워셜로 가장 유명한 사람의 2-친구의 수를 구함
        // 플로이드 워셜의 핵심은 a에서 b로 바로 가는 경우와 a에서 k를 거쳐서 b로 가는 경우를 비교해서 최단 경로를 구한다는 것임
        // 문제에서 a에서 b로 바로 가는 경우도 2-친구, a에서 k를 거쳐서 b로 가는 경우도 2-친구라고 했으므로 플로이드 워셜을 이용해서 2-친구 수를 구한다
        int maxFriend = 0;
        for (int i = 0; i < N; i++) {
            int count = 0;
            for (int j = 0; j < N; j++) {
                if (i == j) continue;
                for (int k = 0; k < N; k++) {
                    friend[i][j] = Math.min(friend[i][k] + friend[k][j], friend[i][j]); // 최단 경로 갱신
                }
                if (friend[i][j] == 1 || friend[i][j] == 2) count++; // 2-친구의 수 카운트
            }
            maxFriend = Math.max(maxFriend, count); // 가장 유명한 사람의 2-친구의 수
        }

        System.out.println(maxFriend);
    }
}
