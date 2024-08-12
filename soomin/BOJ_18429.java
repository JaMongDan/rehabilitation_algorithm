import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_18429 {

    static int N, K;
    static int[] kit;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // N일
        K = Integer.parseInt(st.nextToken()); // 감소될 중량

        kit = new int[N];
        st = new StringTokenizer(br.readLine());
        int sum = 0;
        for(int i = 0; i<N; i++) {
            sum += kit[i] = Integer.parseInt(st.nextToken());
        }

        if(sum < N*K) { // 모든 키트값을 더했을 때 감소될 중량보다 작다면 가능한 경우의 수가 없으므로 빠르게 종료
            System.out.println(0);
            return;
        }

        // 가능한 적응 순서 경우의 수를 구하라 => 순서가 중요하니까 순열을 생각함
        // N이 최대 8이므로 시간 복잡도는 8! = 40320 이므로 시간이 충분하다.
        // 가능한 순서를 순열로 뽑고 조건을 둬서 위배되면 넘어가고 아니면 마저 탐색
        visited = new boolean[N]; // 순열에서 순서가 다르면 다른 경우의 수이므로 방문체크를 통해서 구별

        System.out.println(find(0, 0)); // 일수, 합계
    }


    /**
     * 백트래킹으로 가능한 경우의 수를 구한다.
     * 날짜가 N일이면 종료한다.
     * 만약 현재 day의 키트 값이 남은 day 동안의 손실될 근력값보다 크다면 굳이 더 탐색할 필요없이 팩토리얼로 연산 후 값 저장해 시간을 단축합니다
     * @param day 일수
     * @param sum 근력
     * @return 가능한 경우의 수
     */
    private static int find(int day, int sum) {

        // 기저 조건
        if(day == N) return 1;

        int count = 0;
        // 순열
        for(int i = 0; i<N; i++) {
            if(visited[i]) continue;

            if(kit[i] >= K * (N - day)) { // 더 탐색하지 않아도 무조건 가능한 경우기 때문에 팩토리얼로 경우의 수를 구함
                count += factorial(N-day-1);
                continue;
            }

            int nextValue = sum + kit[i] - K;
            if(nextValue < 0) continue;
            visited[i] = true;
            count += find(day + 1, nextValue);
            visited[i] = false; // 이래야 다른 순서에서 방문할 수 있다.
        }

        return count;
    }

    /**
     * 팩토리얼 (순열로 가능한 경우의 수)을 구하기 위한 함수
     * @param num
     * @return
     */
    private static int factorial(int num) {
        int number = 1;
        for (int i = 2; i <= num; i++)
            number *= i;
        return number;
    }
}
