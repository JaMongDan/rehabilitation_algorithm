import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_1863 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int count = 0;
        Stack<Integer> skyline = new Stack<>();

        // 입력과 동시에 연산
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()); // 얘 활용 안함 근데 왜 문제에서 주어주징??
            int y = Integer.parseInt(st.nextToken()); // 빌딩의 높이!

            // 이전 빌딩 높이보다 높으면 stack에 삽입
            // 이전 빌딩의 높이보다 낮으면 pop하고 count를 증가한다 => 높이가 낮아졌기때문에 여기까지가 그 건물의 너비인거임
            while (!skyline.isEmpty() && skyline.peek() > y) {
                skyline.pop();
                count++;
            }

            // 이전 빌딩의 높이랑 같으면 여전히 그 건물이므로 아무 작업도 안함, 만약 0이라면 새로운 스카이라인이므로 아무작업하지 않음
            if (!skyline.isEmpty() && skyline.peek() == y || y == 0) continue;

            // 지금 건물의 높이를 삽입!
            skyline.add(y);

        }

        // 남은 빌딩 수 더해주기
        count += skyline.size();

        System.out.println(count);

    }
}
