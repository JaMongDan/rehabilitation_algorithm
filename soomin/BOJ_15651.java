import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15651 {

    static int N, M;
    static int[] list;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        /*
         * 백트레킹
         * 1~N까지의 수 중에서 M개를 선택해서 조합합니다
         * 중복이 허용되기 때문에 1~N까지 반복문을 돌면 됩니당나귀!
         */
        list = new int[M];
        find(0);

        System.out.println(sb);

    }

    private static void find(int count) {
        if (count == M) {
            for(int num : list) sb.append(num).append(" ");
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            list[count] = i;
            find( count+1);
        }
    }
}
