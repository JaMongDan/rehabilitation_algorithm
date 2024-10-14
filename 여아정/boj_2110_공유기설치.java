package test_0913;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2110_공유기설치 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] house = new int[n];


        for (int i = 0; i < n; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(house);

        int max = house[n - 1] - house[0];
        int ans = binarySearch(n, c, max, house);

        System.out.println(ans);

    }

    public static int binarySearch(int n, int c, int max, int[] house) {
        int left = 1;
        int right = max;

        while (left <= right) {
            int mid = (left + right) / 2;

            int cnt = 1;
            int now = 0;

            for (int i = 1; i < n; i++) {
                if (house[i] - house[now] < mid) continue;

                cnt++;
                now = i;
            }

            if (cnt < c) right = mid - 1;
            else left = mid + 1;
        }
        return right;
    }
}
