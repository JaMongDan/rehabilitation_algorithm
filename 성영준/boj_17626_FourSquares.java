import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_17626_FourSquares {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] count = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;

            for (int j = 1; j * j <= i; j++)
                min = Math.min(min, count[i - j * j]);

            count[i] = min + 1;
        }

        System.out.println(count[n]);
    }
}