import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String explosion = br.readLine();

        // 모든 폭발 문자열이 폭발
        // 남은 문자열을 순서대로 이어 붙여서 새로운 문자열을 만듬
        // 새로운 문자열에도 폭발 문자열이 포함 되어있을 수 있음
        // 더이상 폭발하지 않을 때까지 반복한다.

        // 스택을 사용할거임
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));

            if (stack.size() < explosion.length()) continue;

            boolean flag = isSame(explosion, stack);

            if (flag) {
                for (int j = 0; j < explosion.length(); j++) { // 폭발 문자열 제거
                    stack.pop();
                }
            }

        }

        // 출력
        if (stack.isEmpty()) System.out.println("FRULA");
        else {
            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) sb.append(stack.pop());
            System.out.println(sb.reverse());
        }
    }

    // 폭발 문자열과 동일한지 비교하는 함수
    private static boolean isSame(String explosion, Stack<Character> stack) {
        boolean flag = true;

        for (int j = 0; j < explosion.length(); j++) {
            if (stack.get(stack.size() - explosion.length() + j) != explosion.charAt(j)) {
                flag = false;
                break; // 폭발 문자열과 다르면 종료
            }
        }
        return flag;
    }
}
