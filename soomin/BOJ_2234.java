import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2234 {

    static int[][] wall; // 입력으로 주어지는 벽 정보를 저장하는 배열
    static int[][] roomNum; // 각 좌표마다 방 번호를 넣음
    static int[][] move = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}}; // 서 북 동 남
    static int[] wallInfo = {1, 2, 4, 8}; // 서 북 동 남
    static Map<Integer, Integer> roomArea = new HashMap<>(); //각 방의 넓이를 넣을 배열
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // 가로
        N = Integer.parseInt(st.nextToken()); // 세로

        wall = new int[N][M];
        roomNum = new int[N][M]; // 방 번호를 넣을 배열

        // 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                wall[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 연산
        System.out.println(process());


    }

    public static String process() {

        int roomCnt = 1; // 방의 갯수
        int areaMax = 0; // 가장 넒은 방의 넓이

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (roomNum[i][j] > 0) continue;
                bfs(i, j, roomCnt);
                areaMax = Math.max(areaMax, roomArea.get(roomCnt++));
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(roomCnt - 1).append("\n").append(areaMax).append("\n").append(getMaxArea());

        return sb.toString();
    }

    // 구역을 구하고 각 구역의 넓이를 구함
    public static void bfs(int y, int x, int roomNumber) {
        Queue<int[]> q = new ArrayDeque<>();

        q.add(new int[]{y, x});
        roomNum[y][x] = roomNumber; // 구역 표시 이자 방문 표시

        while (!q.isEmpty()) {

            int[] cur = q.poll();
            roomArea.put(roomNumber, roomArea.getOrDefault(roomNumber, 0) + 1); // 넓이 증가!

            int wallNum = wall[cur[0]][cur[1]];

            for (int d = 0; d < 4; d++) { // 서 > 북 > 동 > 남
                if ((1 << d & wallNum) == wallInfo[d]) continue; // 벽으로 막혔음

                int ny = cur[0] + move[d][0];
                if (ny < 0 || ny >= N) continue;
                int nx = cur[1] + move[d][1];
                if (nx < 0 || nx >= M) continue;

                if (roomNum[ny][nx] > 0) continue;

                roomNum[ny][nx] = roomNumber;
                q.add(new int[]{ny, nx});
            }
        }
    }

    // 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기
    public static int getMaxArea() {
        int max = 0;

        // i+1이 N미만일 조건이 있는데 이걸 굳이 쓰고
        // for문에서 탐색 범위를 N-1, M-1까지 하지 않는 이유는 그렇게 해버리면
        // 0, n-1 이랑 1, n-1이랑 다른 구역일 경우 탐색을 못하기 때문임
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if (i+1 < N && roomNum[i][j] != roomNum[i + 1][j])
                    max = Math.max(max, roomArea.get(roomNum[i + 1][j]) + roomArea.get(roomNum[i][j]));

                if (j+1 < M && roomNum[i][j] != roomNum[i][j+1])
                    max = Math.max(max, roomArea.get(roomNum[i][j+1]) + roomArea.get(roomNum[i][j]));

            }
        }

        return max;
    }

}
