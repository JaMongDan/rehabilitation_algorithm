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
        int left = 0;
        int right = 0;
        for(int i = 1; i<W-1; i++){ // 첫, 마지막은 안됨

            for(int j = i+1; j<W; j++){
                right = Math.max(right, block[j]); // 오른쪽에서 젤 큰 애
            }

            for(int j = 0; j<i; j++){ // 왼쪽에서 젤 큰애
                left = Math.max(left, block[j]);
            }

            if(block[i] >= right || block[i] >= left) continue;

            int height = Math.min(right, left); //더 작은 쪽이 최종 높이가 된다
            result += height - block[i]; // 현재 높이와 물높이의 차를 구해서 result에 더해준다.

        }

        System.out.println(result);



    }
}
