import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1976 {

    static List<Integer>[] list;
    static int[] plan;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 도시수
        int M = Integer.parseInt(br.readLine()); // 계획한 여행지 수


        // 인접리스트 만들기
        list = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                if (st.nextToken().equals("1") || i == j) // 자기자신도 가능함!!
                    list[i].add(j);
            }
        }

        // 동혁이의 여행 계획
        plan = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            plan[i] = Integer.parseInt(st.nextToken());
        }

        // bfs
        // 경유지를 다 거쳐서 가야하므로 반목문으로 경우지마다 가능한지 확인함다.
        boolean result = true;
        for(int i = 0; i<M-1; i++){
            if(!bfs(N, plan[i], plan[i+1])){
                result = false;
                break;
            }
        }

        System.out.println(result ? "YES" : "NO");

    }

    private static boolean bfs(int N, int start, int destination) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N+1];
        q.add(start);
        visited[start] = true;

        while(!q.isEmpty()) {
            int now = q.poll();

            for(int next : list[now]) {

                if(next == destination) return true;
                if(visited[next]) continue;

                visited[next] = true;
                q.add(next);
            }
        }

        return false;
    }
}
