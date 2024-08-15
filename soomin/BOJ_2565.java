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
         * 이분탐색을 사용하여 현재 i번째 전봇대와 연결되는 b 전봇대가 dp 테이블의 어떤 위치에 포함될지 탐색
         * 탐색 범위는 0 ~ size(dp 배열의 유효한 길이)입니당
         */
        int size = 0; // dp 배열의 의미있는 길이
        for (int i = 0; i < N; i++) {

            int b = wireList.get(i).b;

            // 종료값이 size인 이유는 이분탐색의 반복 조건이 left < right이기 때문입니다.
            // left == right면 종료되기 때문에 right를 size-1(인덱스)로 두면 마지막 원소를 탐색 못합니다.
            // 따라서 size는 사실상 최종인덱스 + 1이고 size를 right으로 두어야 마지막 원소까지 탐색이 가능합니다.
            int pos = binarySearch(0, size, b);

            dp[pos] = b;
            if(pos == size) size++; // b값이 추가되었기 때문에 증가!

        }

        System.out.println(N - size); // 철거한 갯수를 출력
    }

    /**
     * b 전봇대가 dp 배열의 어느 위치에 위치할 수 있을지 탐색하는 이분탐색 메소드입니다.
     * b 전봇대의 값과 db 배열의 원소값을 비교하면서
     * 더 크다면 위치를 오른쪽으로 이동하고
     * 작다면 왼쪽으로 이동합니당
     *
     * @param left 탐색 시작 인덱스 0으로 고정입니다.
     * @param right 탐색 종료 인덱스 dp 배열의 유효 길이입니다
     * @param value 현재 탐색할 a전봇대가 연결될 b 전봇대 값입니당
     * @return
     */
    private static int binarySearch(int left, int right, int value) {


        while (left < right) {
            int mid =  (left + right) / 2; // 삽입할 위치

            if (dp[mid] < value) left = mid + 1;
            else right = mid;
        }

        return right; // 삽입할 위치를 반환
    }
}
