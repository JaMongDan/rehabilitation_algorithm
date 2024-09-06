import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1018 {

    static int N, M;
    static String[] chess = {"BWBWBWBW", "WBWBWBWB"}; // 왼쪽 상단의 타일이 검정일때, 흰색일때 타일을 문자열 패턴으로 저장해서 비교
    static String[] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. 입력
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new String[N];
        for(int i = 0; i<N; i++) {
            map[i] = br.readLine();
        }

        // 2. 8*8 선택
        int answer = 501;
        for(int i = 0; i<= N-8; i++) { // 범위를 벗어나지 않도록 8칸 확보
            for(int j = 0; j<=M-8; j++) {
                // 3. 변경할 타일 개수 구하기
                answer = Math.min(answer, countChangeColor(i, j)); // 시작 위치 (왼쪽 젤 상단 좌표)
            }
        }

        System.out.println(answer);
    }

    /**
     * 왼쪽 젤 상단의 타일색이 검정일때를 기준으로 
     * 8*8 체스판을 탐색하면서 변경해야할 타일 갯수를 세고 
     * 검정일 때 변경해야할 갯수와 흰색일 때 변경해야할 갯수를 비교해 최솟값을 반환합니다.
     * @param y 왼쪽 젤 상단 타일의 행 좌표
     * @param x 왼쪽 젤 상단 타일의 열 좌표
     * @return 변경해야할 최소 타일 개수
     */
    private static int countChangeColor(int y, int x) {
        int blackCnt = 0; //검정일 때 변경해야할 타일 개수
        
        for(int i = 0; i<8; i++){
            int row = y + i;
            for(int j = 0; j<8; j++) {
                int col = x + j;
                if(map[row].charAt(col) != chess[i%2].charAt(j)) blackCnt++; // BWBWBWBW과 WBWBWBWB이 반복됨
            }
        }
        return Math.min(blackCnt, 64-blackCnt);
    }
}
