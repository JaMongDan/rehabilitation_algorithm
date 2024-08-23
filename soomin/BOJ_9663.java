import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9663 {

    static int[] queen;
    static int sum = 0, N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        queen = new int[N]; // 인덱스는 행이고 값은 열을 의미한다.

        // 한줄에 퀸이 한개씩 와야함 그래야 N개를 맞출 수 있다.
        backtracking(0);

        System.out.println(sum);

    }

    /**
     *
     * 백트래킹으로 퀸을 놓을 수 있다면 계속 탐색하고 아니라면 탐색을 종료한다.
     *
     * row = 다음 퀸 차례
     * col = 이 줄에서 퀸을 놓는 위치
     */
    private static void backtracking(int cntRow) {

        if (cntRow == N) {
            sum++;
            return;
        }

        for (int col = 0; col < N; col++) { // 현재 열에 놓을 수 있는 지 확인
            if (!isPossible(cntRow, col)) continue;
            queen[cntRow] = col;
            backtracking(cntRow + 1);

        }
    }


    /**
     * 현재 위치에 퀸을 놓을 수 있는지 체크하는 메서드
     *
     * 이미 같은 열에 퀸이 있는지와 대각선에 퀸이 있는 지 확인합니다.
     *
     * @param col 열의 인덱스
     * @param row 행의 인덱스
     * @return
     */
    private static boolean isPossible(int col, int row) {

        for (int i = 0; i < col; i++) {

            if (queen[i] == row)
                return false; // 같은 열인지 확인한다. 같은 행인지는 확인할 필요가 없는 게 한 행에 퀸이 한 개씩 오도록 백트래킹에서 처리를 했기 때문에 검사안해도 됩니다.

            // 대각선 확인
            // -1, -1 / -1, 1 / 1, 1 / 1, -1 => 따라서 열의 차이와 행의 차이가 동일하다는 점을 이용해서 체크함
            if (Math.abs(col - i) == Math.abs(row - queen[i])) return false;
        }

        return true;
    }
}
