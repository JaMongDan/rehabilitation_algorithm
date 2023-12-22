import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] array = new int[N];
        for(int i = 0; i<N; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }

        int end = 0; // 종료 위치
        int sum = array[0]; // 배열의 첫번째 원소로 합 초기화
        int len = Integer.MAX_VALUE; // 최소 길이를 저장하는 변수
        int result = 0; // 답을 저장하는 변수로 불가능하다면 0, 가능하다면 가장 짧은 길이를 출력해야한다.
        boolean isPossible = true;

        for(int i = 0; i<N; i++){ // 시작 위치를 차례대로 증가시키면서 반복한다.

            while(true){ // 종료 포인터 위치
                if(sum >= S) { // 부분합이 주어진 합 이상이면 종료하고 다음 시작 위치로 가야한다.
                    result = len = Math.min(len, end-i+1); // 최소 길이로 갱신
                    break;
                }

                // 부분합이 주어진 합보다 작으므로 종료 위치를 증가시킨다.
                end++;
                if(end >= N) {
                    //isPossible = false; 범위를 벗어나게 된다는 건 앞으로 시작 위치를 계속 옮겨도 부분합이 S 이상이 될 수 없으니까 시간단축을 위해 넣은 flag
                    break; // 범위가 벗어나면 종료!
                }
                sum += array[end];
            }

           // if(!isPossible) break;
            sum -= array[i]; // 시작위치가 다음 인덱스로 넘어가므로 합에서 빼기

        }

        System.out.println(result);
    }
}
