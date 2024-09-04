import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Node {
    int num;
    Node left, right;

    Node(int num) {
        this.num = num;
    }

    Node(Node left, Node right){
        this.left = left;
        this.right = right;
    }

}
public class BOJ_5639 {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Node root = new Node(Integer.parseInt(br.readLine())); // 전위순회이므로 첫 노드는 무조건 루트
        String input;
        // 입력값
        while((input = br.readLine()) != null && !input.isEmpty()) {
            // 트리에 삾입
            insert(root, Integer.parseInt(input));
        }

        // 후위순회
        postorder(root);
        System.out.println(sb);
    }

    /**
     * 이진 탐색 트리에 새로운 노드를 삽입하는 함수
     * @param parent 현재 노드
     * @param n 삽입할 값
     */
    private static void insert(Node parent, int n) {

        if(n < parent.num) { // 삽입할 값이 현재 노드의 값보다 작다면 왼쪽 서브트리에 삽입
            if(parent.left == null)
                parent.left = new Node(n); // 자식이 없다면 새로운 노드를 생성해서 삽입
            else insert(parent.left, n); // 자식이 이미 있다면 왼쪽 서브트리로 재귀 호출
        }
        else if(n > parent.num) { // 위와 반대로 오른쪽 서브트리에 삽입
            if(parent.right == null)
                parent.right = new Node(n);
            else insert(parent.right, n);
        }
    }

    /**
     * 이진 트리를 후위순회하는 함수
     * @param node 현재 방문 중인 노드
     */
    private static void postorder (Node node) {
        // 방문 순서: 왼쪽서브트리 -> 오른쪽서브트리 -> 현재노드
        if(node.left != null) postorder(node.left);
        if(node.right != null) postorder(node.right);
        sb.append(node.num).append("\n");
    }
}
