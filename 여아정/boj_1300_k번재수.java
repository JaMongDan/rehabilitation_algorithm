package test_0913;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1300_k번재수 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());


        int ans = binarySearch(n, k);
        System.out.println(ans);
    }

    private static int binarySearch(int n, int k) {
        int left = 1;
        int right = k;

        while (left <= right) {
            int mid = (left + right) / 2;

            int cnt = chkCount(mid, n);

            if (cnt >= k) right = mid - 1;
            else left = mid + 1;
        }

        return left;
    }

    private static int chkCount(int mid, int n) {
        int cnt = 0;

        for (int i = 1; i <= n; i++) {
            cnt += Math.min(mid / i, n);
        }

        return cnt;
    }
}
