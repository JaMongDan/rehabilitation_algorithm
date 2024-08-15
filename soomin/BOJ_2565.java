import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Wire implements Comparable<Wire> {
    int a, b;

    Wire(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(Wire o) {
        return a - o.a;
    }
}

public class BOJ_2565 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<Wire> wireList = new ArrayList<>();
        int[] dp = new int[N]; // a 전봇대를 기준으로 몇개나 연결할 수 있는지 저장하는 배열

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            wireList.add(new Wire(a, b));
        }

        /*
         * <아이디어>
         * 문제에서 요구하는 것 몇 개를 철거해야 안겹칠까요?
         * 역으로 안겹치게 최대로 설치하면 몇개일까? 를 생각하면서 풀었습니다. 그리고 전깃줄 갯수 N - 설치한 갯수
         * 를 해서 구하였습니당
         */

        /*
         * 출발 전봇대 기준으로 정렬한다.
         * 정렬을 해야하는 이유: 출발 전봇대(A) 기준으로 전깃줄을 도착 전봇대(B)에 잇고 그 후 전선을 탐색하면서
         * B 전봇대보다 큰 전봇대를 탐색하면서 안 겹치게 정렬하기 위함이다.
         */
        Collections.sort(wireList);

        /*
         * 0~i-1번째 전봇대들을 탐색하면서 i번째 전봇대를 B와 연결해도 되는 지 탐색하는 메소드
         * i번째 전봇대에 연결된 도착 전봇대(B)는 0~i-1번째 전봇대에 연결된 B보다 커야 안켭침
         */
        int max = 0;
        for (int i = 0; i < N; i++) {
            dp[i] = 1; // i 이전 값에 + 1을 해야하기 때문에 자기 자신으로 초기화해야합니당
            Wire now = wireList.get(i);

            for (int j = 0; j < i; j++) {
                if (now.b > wireList.get(j).b)
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            max = Math.max(max, dp[i]);
        }

        System.out.println(N - max); // 철거한
    }
}
