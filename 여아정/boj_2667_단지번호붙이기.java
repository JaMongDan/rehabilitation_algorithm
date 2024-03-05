package test_0913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_2667_단지번호붙이기 {
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        char[][] map = new char[n][n];

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();//문자로 받기
        }

        boolean[][] chk = new boolean[n][n];//체킹
        int count=0;
        List<Integer> list=new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == '0') continue;
                if (chk[i][j]) continue;
                list.add(bfs(map, chk, i, j));
                count++;
            }
        }
        Collections.sort(list);//오름차순 정렬하기
        sb.append(count).append("\n");

        for(int i=0;i<count;i++){
            sb.append(list.get(i)).append("\n");
        }

        System.out.println(sb);

    }

    private static int bfs(char[][] map, boolean[][] chk, int sX, int sY) {
        Deque<int[]> q = new ArrayDeque<>();
        int cnt=1;
        q.add(new int[]{sX, sY});
        chk[sX][sY] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];

            for (int i = 0; i < 4; i++) {//갈 수 있는 4방 탐색
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                //안되는 경우
                if (nx < 0 || nx >= map.length) continue;
                if (ny < 0 || ny >= map.length) continue;
                if(map[nx][ny]=='0') continue;
                if(chk[nx][ny]) continue;

                q.add(new int[]{nx,ny});
                chk[nx][ny]=true;
                cnt++;
            }
        }
        return cnt;
    }
}
