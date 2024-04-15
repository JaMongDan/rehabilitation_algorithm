import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;


/*
 * N과 M(1)
 *
 * */

public class Main {

    static int N,M;

    static int [] arr;
    static boolean [] isVisited;

    static int [] output;

    public static void main(String[] args) throws IOException {


        // 1. 값 넣기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int [N];
        isVisited = new boolean[N];
        output = new int [M];

        for (int i = 0; i < N; i++) {
            arr[i] = i+1;
        }

        // 2. Permutation 진행
        Permutation(0);
    }

    public static void Permutation (int depth) {

        // 1) 기저 조건
        if(depth == M){
            for(int temp : output){
                System.out.print(temp + " ");
            }
            System.out.println();
            return;
        }

        // 2) 진행 조건
        for (int i = 0; i < N; i++) {
            if(!isVisited[i]){
                output[depth] = arr[i];
                isVisited[i] = true;
                Permutation(depth+1);
                isVisited[i] = false;
            }
        }

    }
}
