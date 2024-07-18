package test_0913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class boj_9375_패션왕신해빈 {
    static Map<String, Integer> wear;
    static int all;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine()); //테스트 케이스

        for (int testCase = 0; testCase < tc; testCase++) {
            int n = Integer.parseInt(br.readLine()); //의상 수

            wear = new HashMap<>();

            for (int i = 0; i < n; i++) {

                st = new StringTokenizer(br.readLine());
                String item = st.nextToken();
                String category = st.nextToken();

                if (wear.containsKey(category)) {
                    wear.put(category, wear.get(category) + 1);
                } else {
                    wear.put(category, 1);
                }

            }

            all = 1;
            for (int value : wear.values()) {
                all *= value + 1;//선택하지 않는 경우를 생각하여 +1
            }

            sb.append(all - 1).append("\n"); //알몸일 경우 빼기
        }
        System.out.println(sb);

    }


}
