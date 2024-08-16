import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_4949 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        /*
            스택을 사용해 괄호의 조건을 만족하는 지 확인
         */

        while (true) {

            String line = br.readLine();
            if (line.equals(".")) break;

            sb.append(isBalanced(line) ? "yes" : "no").append("\n");

        }

        System.out.println(sb);
    }

    /**
     * 괄호의 짝이 맞는 지 확인하는 메소드
     * 
     * (또는 [ 같이 여는 괄효일 때는 stack에 삽입하고 닫는 괄호일때는 pop을 해 짝이 맞는 지 확인합니당
     * 
     * @param line 입력받은 문자
     * @return 괄호의 짝이 맞는 지 여부를 출력
     */
    private static boolean isBalanced(String line) {

        Stack<Character> stack = new Stack<>();

        for (char c : line.toCharArray()) {

            if (c == '(' || c == '[') { // switch로 했다가 if로 바꾼 이유입니다. (와 [일때 로직이 똑같기 때문에 if문을 채택햇습니다. (java 12부턴 switch-case도 가능)
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty() || stack.pop() != '(') return false;
            } else if (c == ']') {
                if (stack.isEmpty() || stack.pop() != '[') return false;
            }
        }
        return stack.isEmpty();
    }
}
