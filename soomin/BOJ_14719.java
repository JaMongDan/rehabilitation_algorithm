import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//백준 14719번: 빗물
public class BOJ_14719 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[] block = new int[W];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<W; i++){
            block[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0;
        for(int i = 1; i<W-1; i++){ // 첫, 마지막은 안됨

            int left = 0;
            int right = 0;

            for(int j = i+1; j<W; j++){
                right = Math.max(right, block[j]); // 오른쪽에서 젤 큰 애
            }

            for(int j = 0; j<i; j++){ // 왼쪽에서 젤 큰애
                left = Math.max(left, block[j]);
            }

            if(block[i] >= right || block[i] >= left) continue; // 현재 블록이 가장 클 경우 빗물을 계산하지 않는다.

            int height = Math.min(right, left); // 최종 물높이를 정한다.
            result += height - block[i]; // 현재 블록에서 구할 수 있는 빗물을 구한다.

        }

        System.out.println(result);



    }
}
