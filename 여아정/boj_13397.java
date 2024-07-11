package test_0913;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_13397 {
    static int MAX =Integer.MAX_VALUE;
    static int MIN=Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());//배열크기
        int m = Integer.parseInt(st.nextToken());//나눌 구간 개수

        int right = Integer.MIN_VALUE;
        int[] arr = new int[n];
        int[] sub = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            right = Math.max(right, arr[i]);// 수열 중 가장 큰 값을 right에 갱신
        }

        //이분탐색을 활용하여 구간 최댓값 최소로 구함
        System.out.println(BSearch(0, right, m, arr));
    }

    //이분탐색
    static int BSearch(int left, int right, int m, int[] arr) {
        int mid;

        while (left < right) {
            mid = (left + right) / 2;

            //mid를 기준으로 구간을 나눈게 m개 보다 작거나 같으면 mid를 더 줄여도 될듯욤
            if (chkGroup(mid, arr) <= m) {
                right = mid;
            } else left = mid + 1;// mid 값이 작아서 m구간 수 보다 더 나옴. 안돼 left 올려.
        }
        return right;
    }

    //이분탐색을 통해 구한 mid 값보다 최대값-최소값이 작도록 했을때 나눠지는 구분 count를 구하는 함수
    static int chkGroup(int mid, int[] arr) {
        int cnt =1;// 구분구간 카운트 변수 최소 1개는 생기니까 1로 초기화
        int max =MIN;
        int min=MAX;

        for(int i=0;i<arr.length;i++){
            //최대값 최소값을 갱신
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);

            //mid값보다 클때
            if(max- min>mid){
                //구분구간 cnt를 증가
                cnt++;
                //최소 최대값 초기화
                max=MIN;
                min=MAX;
                // 구간 나눈 이후
                i--;// 현재 i값으로 다시 새 구간을 탐색하기 위해 앞으로 --해준다
            }
        }

        return cnt;
    }

}
