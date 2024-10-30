import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_9935 {

    static int len;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String explosion = br.readLine();

        len = explosion.length();

        // 모든 폭발 문자열이 폭발
        // 남은 문자열을 순서대로 이어 붙여서 새로운 문자열을 만듬
        // 새로운 문자열에도 폭발 문자열이 포함 되어있을 수 있음
        // 더이상 폭발하지 않을 때까지 반복한다.

        // 스택을 사용할거임
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));

            if (stack.size() < len) continue;

            boolean flag = isSame(explosion, stack);

            if (flag) {
                for (int j = 0; j < len; j++) { // 폭발 문자열 제거
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

        // 스택의 마지막 부분을 폭발 문자열과 비교
        // 스택의 끝에서부터 폭발 문자열 길이만큼을 순회하며 각 문자가 일치하는 지 확인한다.
        for (int j = 0; j < explosion.length(); j++) {
            if (stack.get(stack.size() -len + j) != explosion.charAt(j)) { // 스택의 마지막 N(폭발 문자열의 길이)개의 요소를 폭발 문자열과 순서대로 비교
                flag = false;
                break; // 폭발 문자열과 다르면 종료
            }
        }
        return flag;
    }
}
