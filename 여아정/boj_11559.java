package test_0913;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj_11559 {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] chk;
    static List<Puyo> puyoList;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        char[][] game = new char[12][6];// 뿌요 판

        //역순 입력
        for (int i = 11; i >= 0; i--) {
            game[i] = br.readLine().toCharArray();
        }

        ans = doGame(game);
        System.out.println(ans);
    }

    private static int doGame(char[][] game) {

        //특정 횟수 정해진거 없이 뿌요폭발(?) 가능할때까지 돌아유
        while (true) {
            int cnt = 0;
            chk = new boolean[12][6];
            puyoList = new ArrayList<>();// 폭발할 친구들 위치 저장할 리스트

            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (game[i][j] < 'A' || chk[i][j]) {
                        cnt++;
                        continue;
                    }
                    if (!searchPuyo(game, i, j, game[i][j])) {//bfs 를 통해 탐색하여 4개 이상 되는지 유무 체크
                        cnt++;
                        continue;
                    }


                    deletePuyo(game);//폭발 예정인 친구들 값 갱신

                }
            }

            if (cnt == 72) break;//다돌아도 폭발할게 없으면 종료

            ans++;
            dropPuyo(game);
        }

        return ans;
    }

    private static boolean searchPuyo(char[][] game, int px, int py, char color) {
        Deque<Puyo> q = new ArrayDeque<>();
        q.add(new Puyo(px, py));
        puyoList.add(new Puyo(px, py));

        chk[px][py] = true;
        int cnt = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                Puyo cur = q.poll();
                cnt++;

                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i];
                    if (nx < 0 || nx >= 12) continue;

                    int ny = cur.y + dy[i];
                    if (ny < 0 || ny >= 6) continue;

                    if (chk[nx][ny]) continue;
                    if (game[nx][ny] != color) continue;

                    chk[nx][ny] = true;
                    q.add(new Puyo(nx, ny));
                    puyoList.add(new Puyo(nx, ny));
                }
            }
        }

        //4개 이상이 안된다면 다시 지움
        if (cnt < 4) {
            for (int i = 0; i < cnt; i++) {
                puyoList.remove(puyoList.size() - 1);
            }

            return false;
        }

        return true;

    }


    private static void deletePuyo(char[][] game) {

        for (int j = 0; j < puyoList.size(); j++) {
            game[puyoList.get(j).x][puyoList.get(j).y] = '0';    //puyoList에 있는 좌표부분에 폭발한 친구들로 '0'으롷 갱신
        }

    }

    //폭발 후 자리 채우기
    private static void dropPuyo(char[][] game) {
        for (int i = 0; i < 6; i++) {
            char[] temp = new char[12];
            int idx = 0;

            for (int j = 0; j < 12; j++) {
                if (game[j][i] == '0') continue;//폭발 한 친구들꺼 빼고 값 배열에 넣기
                temp[idx++] = game[j][i];
            }

            //다시 갱신하기~
            for (int j = 0; j < idx; j++) {
                game[j][i] = temp[j];
            }
            for (int j = idx; j < 12; j++) {
                game[j][i] = '.';
            }
        }
    }

    //큐, 리스트에 담을 객체
    public static class Puyo {
        int x;
        int y;

        public Puyo(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
