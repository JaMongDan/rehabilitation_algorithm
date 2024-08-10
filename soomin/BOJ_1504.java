import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
    int e, w;

    Edge(int e, int w) {
        this.e = e;
        this.w = w;
    }

    @Override
    public int compareTo(Edge o) {
        return w - o.w;
    }
}

public class BOJ_1504 {

    static int N, E;
    static int[][] graph;

    static int INF = 200000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        //
        graph = new int[N + 1][N + 1];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            // 양방향
            graph[s][e] = w;
            graph[e][s] = w;
        }

        // 반드시 거쳐야하는 노드
        st = new StringTokenizer(br.readLine());
        int must1 = Integer.parseInt(st.nextToken());
        int must2 = Integer.parseInt(st.nextToken());


        int Sto1 = dijkstra(1, must1);
        int Sto2 = dijkstra(1, must2);
        int M1toM2 = dijkstra(must1, must2);


        if (M1toM2 == INF) { // 꼭 거쳐야하는 노드끼리 연결이 안되어 있으면??
            System.out.println(-1);
            return;
        }

        int M2toE = dijkstra(must2, N);
        int M1toE = dijkstra(must1, N);

        // s -> 1 -> 2 -> e
        int result1 = Sto1 + M1toM2 + M2toE;
        // s -> 2 -> 1 -> e
        int result2 = Sto2 + M1toM2 + M1toE;

        int result = Math.min(result1, result2);
        if(result >= INF) result = -1;
        System.out.println(result);
    }

    private static int dijkstra(int start, int end) {
        PriorityQueue<Edge> pq = new PriorityQueue();
        boolean[] visited = new boolean[N + 1]; // 방문체크

        int[] dist = new int[N + 1]; // 거리
        Arrays.fill(dist, INF);

        dist[start] = 0; // 시작점 초괴화
        pq.add(new Edge(start, 0)); // 자기자신

        while (!pq.isEmpty()) {
            Edge now = pq.poll();

            if (visited[now.e]) continue;
            visited[now.e] = true;

            for (int i = 1; i <= N; i++) { // 이어진 노드를 차례대로 탐색
                if (graph[now.e][i] == 0) continue; // 연결 여부
                if (dist[i] > graph[now.e][i] + dist[now.e]) { // 거리 갱신
                    dist[i] = graph[now.e][i] + dist[now.e];
                    pq.add(new Edge(i, dist[i]));
                }
            }

        }
        return dist[end];
    }
}
