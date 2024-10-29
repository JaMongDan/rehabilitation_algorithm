import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2573 {

    static class Ice {
        int y, x, v;

        Ice(int y, int x, int v) {
            this.y = y;
            this.x = x;
            this.v = v;
        }
    }

    static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int N, M;
    static int[][] sea;
    static List<Ice> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sea = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                sea[i][j] = Integer.parseInt(st.nextToken());
                if (sea[i][j] > 0) list.add(new Ice(i, j, 0)); // 빙산의 좌표를 삽입하는 이유는 상하좌우에 0이 몇개 붙어있는 지 탐색하기 위함임
            }
        }

        System.out.println(process());

    }

    // 두 덩어리 이상으로 분리되는 최초의 시간(년)을 구하는 메소드
    public static int process() {


        int time = 0; // 연도를 저장할 변수
        while (!list.isEmpty()) { // 빙산이 모두 녹을 때까지 반복
            boolean[][] visited = new boolean[N][M]; // 방문한 빙산을 체크할 변수
            time++;

            // 인접한 zero 갯수 찾기
            getZero();

            // 빙하 녹이기
            for (int i = 0; i < list.size(); i++) {
                Ice now = list.get(i);
                sea[now.y][now.x] -= now.v;

                if (sea[now.y][now.x] <= 0) { // 빙산이 다 녹았다면
                    list.remove(i); // 빙산 리스트에서 제거
                    sea[now.y][now.x] = 0;
                    i--;
                }
            }

            // 구역 갯수 구하기
            int count = 0;
            for(Ice now : list) {
                if(visited[now.y][now.x]) continue;
                dfs(now.y, now.x, visited);
                count++;
            }

            if(count > 1) return time;
        }

        return 0;
    }

    // 상하좌우를 탐색하며 인접한 빈칸의 갯수를 셈
    public static void getZero() {

        for (Ice now : list) {
            int count = 0;
            for (int d = 0; d < 4; d++) {
                int ny = now.y + move[d][0];
                if (ny < 0 || ny >= N) continue;
                int nx = now.x + move[d][1];
                if (nx < 0 || nx >= M) continue;

                if (sea[ny][nx] > 0) continue;

                count++;
            }

            now.v = count;
        }
    }

    // 몇개의 구역인지 확인!!!
    public static void dfs(int y, int x, boolean[][] visited) {
        visited[y][x] = true;

        int ny, nx;
        for (int d = 0; d < 4; d++) {
            ny = y + move[d][0];
            if (ny < 0 || ny >= N) continue;
            nx = x + move[d][1];
            if (nx < 0 || nx >= M) continue;

            if(visited[ny][nx]) continue;
            if(sea[ny][nx] == 0) continue;

            dfs(ny, nx, visited);
        }
    }
}
