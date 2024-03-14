import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;


public class Main {


    static int [][] field;
    static int N,M,K;

    static int [] idx = {-1,0,1,0};
    static int [] idy = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t <T; t++) {

            // 1) 값 입력 받기
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());   // 가로 길이
            N = Integer.parseInt(st.nextToken());   // 세로 길이
            K = Integer.parseInt(st.nextToken());   // 배추가 심어져 있는 위치의 개수

            field = new int[N][M];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                field[B][A] = 1;
            }

            int cnt = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(field[i][j] == 1) {
                        BFS(i,j);
                        cnt++;
                    }
                }
            }


            System.out.println(cnt);


        }

    }


    // 2)
    static public void BFS(int x, int y) {
        ArrayDeque<Coordinate2> aq1 = new ArrayDeque<>();
        aq1.add(new Coordinate2(x,y));

        while (!aq1.isEmpty()){
            Coordinate2 now = aq1.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + idx[i];
                int ny = now.y + idy[i];

                if(nx>=0 && ny >=0 && nx < N && ny < M && field[nx][ny] == 1){
                    field[nx][ny] = 2;
                    aq1.add(new Coordinate2(nx,ny));
                }
            }
        }
    }
}

class Coordinate2 {
    int x;
    int y;

    public Coordinate2(int x, int y){
        this.x = x;
        this.y = y;
    }
}
