import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14719_빗물 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        st.nextToken();
        int w = Integer.parseInt(st.nextToken());

        int[] blocks = new int[w];

        int max = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < w; i++)
            max = Math.max(max, (blocks[i] = Integer.parseInt(st.nextToken())));

        int puddle = 0;

        for (int i = 1; i <= max; i++) {
            int j = -1;
            while (++j < w)
                if (blocks[j] >= i)
                    break;
            int cnt = 0;
            while (++j < w) {
                if (blocks[j] >= i) {
                    puddle += cnt;
                    cnt = 0;
                    continue;
                }
                cnt++;
            }
        }

        System.out.println(puddle);
    }
}
