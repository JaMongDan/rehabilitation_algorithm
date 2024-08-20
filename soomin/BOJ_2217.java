import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2217 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] rope = new int[N];
        for (int i = 0; i < N; i++) {
            rope[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(rope); // 정렬

        /*
         * 로프의 최소 무게 * 병렬 연결된 로프의 갯수 = 최대 중량이므로
         * 로프의 최소 무게가 최대가 되어야한다.
         * 그래서 입력받은 배열을 정렬해서 로프의 무게가 큰 순서대로 탐색하면서
         * 들어올릴 수 있는 물체의 최대 중량을 구한다
         */
        int sum = 0;
        for (int i = 1; i <= N; i++) { // 배열의 역순으로 참조하기 위해서 1~N까지 탐색합니다. N-i은 인덱스를, i는 병렬 연결된 인덱스르 의미
            sum = Math.max(sum, rope[N - i] * i); // rope의 무게 * 병렬 연결된 로프의 갯수
        }

        System.out.println(sum);
    }
}
