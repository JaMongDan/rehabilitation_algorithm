package test_0913;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1477 {
    static int n, m, l;
    static List<Integer> rest;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        rest = new ArrayList<>();
        rest.add(0);
        rest.add(l);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            rest.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(rest);

        int ans = binarySearch();
        System.out.println(ans);
    }

    private static int binarySearch() {
        int left = 1;
        int right = l;

        while (left <= right) {
            int mid = (left + right) / 2;
            int cnt = 0;

            for (int i = 1; i < rest.size(); i++) {
                cnt += (rest.get(i) - rest.get(i - 1) - 1) / mid;
            }
            if (cnt > m)
                left = mid + 1;
            else right = mid - 1;
        }

        return left;
    }
}
