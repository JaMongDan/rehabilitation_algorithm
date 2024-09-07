import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500 {
    static boolean[][]  visited;
    static int max = 0;
    static int[][] paper;
    static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        paper = new int[N+2][M+2];
        for(int i = 1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<=M; j++){
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 브루트스 포스
        visited = new boolean[N+2][M+2];
        for(int i = 1; i<=N; i++) {
            for(int j = 1; j<=M; j++) { // 모든 좌표를 탐색 시작 좌표로 설정해서 나올 수 있는 값을 모두 비교
                visited[i][j] = true;
                dfs(i, j, 1, paper[i][j]);
                visited[i][j] = false;
            }
        }

        System.out.println(max);
    }

    /**
     * dfs로 상하좌우로 인접한 테크로미노를 만들고 최대 값을 구함
     * 탐색 깊이가 2일 경우 ㅏ, ㅓ, ㅗ, ㅜ 모양을 만들기 위해서
     * 튀어나온 부분의 값을 더하고 해당 타일이 아닌 기존 타일에서 상하좌우를 탐색하도록
     * 조건문을 사용해 처리했습니다.
     *
     * @param y 좌표
     * @param x 좌표
     * @param depth 탐색 깊이 == 개수
     * @param sum 값의 합
     */
    private static void dfs(int y, int x, int depth, int sum) {

        // 기저 조건
        if(depth == 4) {
            max = Math.max(sum, max);
            return;
        }

        // 상하좌우 인접한 정사각형 탐색
        // ㅏ ㅗ ㅓ ㅜ 모양을 만들기 위해서 따로 처리해줌
        for(int d = 0; d<4; d++){
            int ny = y + move[d][0];
            int nx = x + move[d][1];

            if(paper[ny][nx] == 0) continue;
            if(visited[ny][nx]) continue;

            if(depth == 2) {
                visited[ny][nx] = true;
                dfs(y, x, depth+1, sum+paper[ny][nx]); // ㅑ ㅗ ㅓ ㅜ 튀어나온 부분을 갔다왔다 치고 기존 y, x 좌표에서 다시 상하좌우로 탐색함
                visited[ny][nx] = false;
            }

            visited[ny][nx] = true;
            dfs(ny, nx, depth+1, sum+paper[ny][nx]);
            visited[ny][nx] = false;

        }

    }
}
