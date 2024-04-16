package test_0913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class BOJ_3085_사탕게임 {
    static int[] dx = {0, 1};
    static int[] dy = {1, 0};
    static char[][] candy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        candy = new char[n][n];

        for (int i = 0; i < n; i++) {
            candy[i] = br.readLine().toCharArray();
        }

        int max = 0;

        max = changeCandy(candy, n, max);

        System.out.println(max);

    }

    public static int changeCandy(char[][] candy, int n, int max) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {//n-1까지만 돌고 오른쪽과 아래쪽 값만 비교 탐색했습니다
                for (int k = 0; k < 2; k++) {
                    if (i + dx[k] >= n || j + dy[k] >= n) continue;//가장 끝값인 경우 범위 넘어감 방지

                    if (candy[i][j] != candy[i + dx[k]][j + dy[k]]) {//인접한 값의 알파벳이 다를 경우
                        switchCandy(i, j, i + dx[k], j + dy[k]);//값 변경해줌
                        max = Integer.max(max, chkMax(candy));//변경한 상태에서 최대값 구하기
                        switchCandy(i + dx[k], j + dy[k], i, j);//원 상태 복귀
                    }
                }
            }
        }

        return max;
    }

    public static void switchCandy(int x, int y, int dx, int dy) {//변경
        char temp = candy[x][y];
        candy[x][y] = candy[dx][dy];
        candy[dx][dy] = temp;
    }

    public static int chkMax(char[][] candy) {//사탕을 바꾸준 후 최대 길이 카운트함
        int M = 0;

        for (int i = 0; i < candy.length; i++) {//가로 세로를 한꺼번에 봤습니다.
            int cnt_x = 0;
            int cnt_y = 0;
            char cur_x = '0';//초기 문자를 절대 올 수 없는 문자로 초기화 했습니다.
            char cur_y = '0';

            for (int j = 0; j < candy.length; j++) {
                if (cur_x != candy[i][j]) {//같지 않으면 기존의 cnt를 max와 비교 후 갱신 처리
                    M = Integer.max(M, cnt_x);
                    cur_x = candy[i][j];//바뀐 문자로 변경
                    cnt_x = 1;//카운트 초기화
                } else {//같으면 cnt++
                    cnt_x++;
                }

                //세로 탐색(방식은 위와 동일)
                if (cur_y != candy[j][i]) {
                    M = Integer.max(M, cnt_y);
                    cur_y = candy[j][i];
                    cnt_y = 1;
                } else {
                    cnt_y++;
                }
            }

            M = Integer.max(M, Integer.max(cnt_x, cnt_y));//마지막 인덱스에서 다시 max 갱신
        }

        return M;
    }
}
