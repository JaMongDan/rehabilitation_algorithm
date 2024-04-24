package test_0913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class boj_1038 {
    static List<Long> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        if(n<=10){
            System.out.println(n);
        }else if(n>1022){
            System.out.println("-1");
        }else{
            list=new ArrayList<>();
            for(int i=0;i<10;i++){
                downNum(i,1);
            }

            Collections.sort(list);
            System.out.println(list.get(n));
        }
    }

    private static void downNum(long num, int idx) {
        if(idx>10) return;
        list.add(num);
        for(int i=0;i<num%10;i++){
            downNum((num*10)+i,idx+1);
        }
    }

}
