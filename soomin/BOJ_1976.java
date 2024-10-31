import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1976 {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 도시수
        int M = Integer.parseInt(br.readLine()); // 계획한 여행지 수

        // 알고리즘 => 집합 만들기? 유니온 파인드
        // 이유는

        // 부모를 저장할 배열
        parent = new int[N+1];
        for(int i = 1; i<=N; i++){
            parent[i] = i;
        }

        StringTokenizer st;
        for(int i =1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<=N; j++) {

                if(st.nextToken().equals("1")) {
                    union(i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine()); // 동혁이의 여행 계획
        int start = Integer.parseInt(st.nextToken()); // 출발지
        String result = "YES"; // 여행 계획이 가능한지 여부를 나타낼 변수
        for(int i = 1; i<M; i++){
            int next = Integer.parseInt(st.nextToken()); // 다음 여행지

            // 같은 집합에 속하지 않았다면 실패
            if(parent[start] != parent[next]) { // 출발지와 다음 여행지가 같은 집합에 속하지 않았다면 실패
                result = "NO";
                break;
            }
        }

        System.out.println(result);
    }

    // 집합으로 합침
    // y의 부모를 x의 부모로 변경?
    private static void union(int x, int y){

        int px = find(x);
        int py = find(y);

        if(px != py) {
            if(px < py) parent[py] = px;
            else parent[px] = py;
        }
    }

    // 부모찾기
    private static int find(int x) {
        if(parent[x] == x) return x;
        // 자기 자신이 부모가 아니면 부모를 찾아서 업데이트 함
        return parent[x] = find(parent[x]);
    }
}
