import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2470 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 입력
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 2. 정렬해서 양 끝에 포인터를 두고 두 수를 합하기 위함
        Arrays.sort(arr);

        int left = 0, right = N - 1; // 포인터
        int acid = 0, alkaline = 0; // 산성, 알칼리성 용액의 값
        int value = Integer.MAX_VALUE; // 두 수의 최솟값
        int now, tmp; // 현재 포인터가 가르키는 두수의 합, 절댓값

        // 3. 포인터를 이동하면서 두 수의 합의 절댓값이 최소가 나오도록 함
        // 양수라면 right 포인터를 감소 이시고 음수라면 left를 증가하여 두 수의 핪이 최소가 되도록 함
        while (left < right) {

            now = arr[left] + arr[right];
            tmp = Math.abs(now);

            if(tmp < value) { // 최솟값을 갱신
                acid = arr[right];
                alkaline = arr[left];
                value = tmp;
            }

            if(now == 0) break; // 0이면 나올 수 있는 가장 최솟값이므로 탐색 종료

            if(now > 0) right--;
            else left++;

        }

        // 4. 오름차순으로 출력
        StringBuilder sb = new StringBuilder();
        sb.append(alkaline).append(" ").append(acid);
        System.out.println(sb);
    }
}
