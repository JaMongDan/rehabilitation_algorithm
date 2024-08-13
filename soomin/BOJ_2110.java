import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2110 {

    static int N, C;
    static int[] house;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        house = new int[N];
        for (int i = 0; i < N; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(house); // 순차적으로 공유기를 설치하기 위함

        /* 두 공유기 사이의 거리의 최댓값을 구하기 위한 이분탐색
         * 탐색 범위는 거리의 최솟값(1) ~ 거리의 최댓값(입력받은 값중 젤 큰 집좌표 - 작은 집좌표) + 1
         * 거리를 최소 거리라고 생각하고 공유기를 설치해 설치된 공유기와 총 공유기 갯수를 비교하여
         * 거리를 좁히고 늘리면서 최댓값을 찾습니다.
         */

        int left = 1;
        int right = house[N - 1] - house[0] + 1; // 최대 길이는 젤 큰 좌표 - 젤 작은 좌표이나 최대 길이도 탐색에 포함되기 위해서는 +1을 해 탐석범위를 늘려야함. left < right 조건 때문

        while (left < right) {

            int mid = (left + right) / 2;

            if (countWifi(mid) < C) { // mid에 대해 설치 가능한 공유기가 C개보다 작다면 거리를 좁혀야 하기 때문에 right를 감소
                right = mid;
            } else { // 거리를 벌리면서 최대 거리를 찾아야함
                left = mid + 1;
            }

        }

        System.out.println(left - 1); // left = mid + 1이 되고 탐색이 종료되므로 -1을 해줌

    }

    /**
     * 두 공유기 사이의 최소 길이가 mid일때 설치할 수 있는 공유기 수를 반환
     *
     * @param mid 두 공유기 사이의 길이
     * @return 설치된 공유기 수
     */
    private static int countWifi(int mid) {

        int cnt = 1; // 맨 첫 집은 무조건 설치!
        int preIdx = house[0]; // 직전에 설치된 좌표를 저장해서 두 공유기 사이의 길이를 구함

        for (int i = 1; i < N; i++) {

            int now = house[i];

            if (now - preIdx >= mid) {
                cnt++;
                if (cnt == C) return cnt;
                preIdx = now;
            }
        }
        return cnt;
    }
}
