import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_13164 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] height = new int[N];
        int[] diff = new int[N-1]; // 인접한 원생의 키 차이를 저장할 배열

        st = new StringTokenizer(br.readLine());
        height[0] = Integer.parseInt(st.nextToken());
        for(int i = 1; i<N; i++){
            height[i] = Integer.parseInt(st.nextToken());
            diff[i-1] = height[i] - height[i-1];
        }

        // 한명만 있으면 비용이 0이므로
        // 정렬해서 가장 키 차이가 적게 나도록 구간을 나누기 위함입니다.
        Arrays.sort(diff);

        // N명의 원생을 K개 그룹으로 나누기 위해서 N-K개의 구간 합을 구해야합니다.
        // N-1개의 키 차이 중에서 가장 큰 키 차이가 나는 곳에 K-1개의 구간을 둬야 해당 구간에서
        // 발생하는 비용이 0이 되어 최솟값이 될 수 있습니다.
        // 따라서 N-1개의 키 차이 중에서 K-1개의 구간을 제외한 나머지 구간의 합이 곧 비용 입니다.
        // (N-1) - (K-1) = N-K
        int sum = 0;
        for(int i = 0; i<N-K; i++) {
            sum += diff[i];
        }

        System.out.println(sum);
    }
}
