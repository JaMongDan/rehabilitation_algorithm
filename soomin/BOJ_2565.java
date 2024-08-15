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

    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<Wire> wireList = new ArrayList<>();
        dp = new int[N]; // 도착 전봇대(B)를 저장하는 배열

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            wireList.add(new Wire(a, b));
        }

        /*
         * <아이디어>
         * 문제에서 요구하는 것: 몇 개를 철거해야 안겹칠까요?
         * 역으로 안겹치게 최대로 설치하면 몇개일까? 를 생각하면서 풀었습니다.
         * 전깃줄 갯수 N - 설치한 갯수를 하면 철거해야하는 전선 수를 구할 수 있습니다.
         */

        /*
         * 출발 전봇대 기준으로 정렬합니다.
         * 정렬을 해야하는 이유: 출발 전봇대(A) 기준으로 정렬한 후,
         * 도착 전봇대(B)에 대해 증가하는 부분 수열을 찾음으로써
         * 겹치지 않게 최대 전선을 설치할 수 있는 수를 구하기 위함입니다.
         */
        Collections.sort(wireList);

        /*
         * 0~i-1번째 전봇대들을 탐색하면서 i번째 전봇대를 B와 연결해도 되는 지 탐색하는 메소드
         * i번째 전봇대에 연결된 도착 전봇대(B)는 0~i-1번째 전봇대에 연결된 B보다 커야 안켭침
         */
        int size = 0; // dp 배열의 의미있는 길이
        for (int i = 0; i < N; i++) {

            int b = wireList.get(i).b;
            int pos = binarySearch(0, size, b);

            if (pos < 0) pos = -pos - 1;
            dp[pos] = b;

            if(pos == size) size++; // dp 배열의 길이를 추가!

        }

        System.out.println(N - size); // 철거한 갯수를 출력
    }

    private static int binarySearch(int left, int right, int value) {


        while (left < right) {
            int mid =  (left + right) / 2;

            if (dp[mid] < value) left = mid + 1;
            else right = mid;
        }

        return left - 1;
    }
}
