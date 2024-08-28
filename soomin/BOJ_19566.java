import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_19566 {

    static int N, M;
    static int[] arr, answer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        answer = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        dfs(0, 0);
        System.out.println(sb);
    }

    /**
     * 모든 가능한 수열을 생성하고 조건에 맞지 않는다면 해당 경우는 탐색 종료한다.
     * @param start 다음 탐색할 인덱스
     * @param count 탐색 깊이? 길이?
     */
    private static void dfs(int start, int count){
        if(count == M) {
            for(int i = 0; i<M; i++){
                sb.append(answer[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        int tmp = 0; // 이전에 선택한 값을 저장, 중복 제거를 위함

        for(int i = start; i<N; i++) {
            if(tmp == arr[i]) continue; // 수열에 중복되는 게 있는지 확인

            tmp = arr[i];
            answer[count] = arr[i];
            dfs(i+1, count+1);

        }
    }
}
