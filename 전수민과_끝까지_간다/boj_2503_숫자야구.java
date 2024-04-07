import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 숫자 야구 2503
 * 1) 민혁이가 물어본 숫자와 볼 카운트가 일치하면, 어쩌면 3S가 가능한 숫자이다.
 * 2) 민혁이가 물어본 숫자와 볼 카운트가 모두 일치하는 수의 개수를 센다.
 * */

public class Main {

    // ** 테스트 개수
    static int N;

    // ** Ball Count 배열, + 그 때의 값들 배열로 저장
    static char [][] guess;
    static int [] strike;
    static int [] ball;

    // ** 3S의 후보가 될 수 있는 값의 개수
    static int candidate_cnt = 0;

    static int [] now= new int[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1) 값 입력 받기
        N = Integer.parseInt(br.readLine());

        guess   = new char [N][3];
        strike  = new int[N];
        ball    = new int [N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String s = st.nextToken();

            // 1-1) 값 하나하나 분해해서 넣기
            guess[i][0] = s.charAt(0);
            guess[i][1] = s.charAt(1);
            guess[i][2] = s.charAt(2);

            // 1-2)  볼 카운트 넣기
            strike[i]   = Integer.parseInt(st.nextToken());
            ball[i]     = Integer.parseInt(st.nextToken());
        }




        // 2)   숫자 야구에서 나올 수 있는 모든 수를 가지고 Guess[j]와 비교하며 볼 카운트가 일치하면 다음 단계로 넘어가기.
        //      아니면 Return 후 다음 껄로 다시 시도
        next_num: for (int i = 123; i < 988; i++) {


            String temp = String.valueOf(i);

            // 0은 존재하지 않는 수이므로 유효성 탈락
            if(temp.charAt(0) == '0' || temp.charAt(1) == '0' || temp.charAt(2) == '0'){
                continue ;
            }

            // 서로 다른 숫자임으로 같은 값이 나오면 유효성 탈락
            if( temp.charAt(0) == temp.charAt(1) || temp.charAt(0) == temp.charAt(2) || temp.charAt(1) == temp.charAt(2)){
                continue;
            }

            // 2-1) 유효성 체크
            for (int j = 0; j < N; j++) {

                // a - 일치 확인
                int strike_cnt = 0; int bal_cnt  = 0;

                // b - 민혁이가 추측한 값과 비교하여 해당 수의 총 스트라이크 볼 카운트 확인
                outerLoop: for (int k = 0; k < 3; k++) {
                    // 스트라이크 확인 -> 두 수가 일치하면 바로 다음 수 체크
                    if(guess[j][k] == temp.charAt(k)){
                        strike_cnt++;
                        continue;
                    }

                    // 스트라이크가 아니라면 볼 카운트로는 맞는지 확인  -> ball 인게 확인되면 바로 다음 수 체크
                    for (int l = 0; l < 3; l++) {
                        if(guess[j][k] == temp.charAt(l) && k != l){
                            bal_cnt++;
                            continue outerLoop;
                        }
                    }
                }

                // 스트라이크 개수가 맞지 않으면 유효하지 않으므로 그 다음 수를 체크한다.
                if(strike_cnt != strike[j]){
                    continue next_num;
                }

                // 볼 개수가 맞지 않으면 유효하지 않으므로 그 다음 수를 체크한다.
                if(bal_cnt != ball[j]){
                    continue next_num;
                }
            }

            // 2-2) 이 위치까지 Continue 거치지 않고 왔다는 것은 N개의 유효성 검증을 다 통과해낸 유효한 수라는 뜻이다.
            candidate_cnt++;
        }


        System.out.println(candidate_cnt);
    }
}