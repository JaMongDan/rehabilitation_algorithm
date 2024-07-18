package test_0913;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1541 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), "-");//-기준으로 나눔
        int sum = Integer.MAX_VALUE;

        while (st.hasMoreTokens()) {
            int temp = 0;

            StringTokenizer plus = new StringTokenizer(st.nextToken(), "+");//+기준으로 나눈다

            while (plus.hasMoreTokens()) {
                temp += Integer.parseInt(plus.nextToken());//다 더함
            }

            if (sum == Integer.MAX_VALUE) {//처음 부분인제 확인(처음부분은 양수라는 점이 있으므로)
                sum = temp;
            } else {
                sum -= temp;
            }

        }

        System.out.println(sum);

    }
}
