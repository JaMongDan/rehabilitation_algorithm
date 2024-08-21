import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Lecture implements Comparable<Lecture>{
    int s, e;
    
    Lecture(int s, int e) {
        this.s = s;
        this.e = e;
    }


    @Override
    public int compareTo(Lecture o) {
//        if(this.s == o.s) return this.e - o.e; // 시작시간이 같다면 종료 시간으로 오름차순
        return this.s - o.s; // 시작 시간으로 오름차순
    }
}


public class BOJ_11000 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N= Integer.parseInt(br.readLine());

        Lecture[] lectures = new Lecture[N]; // 시작시간 순으로 정렬을 편리하게하기 위해서 배열 사용
        for(int i = 0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            lectures[i] = new Lecture(s, e);
        }

        Arrays.sort(lectures); // 시작시간을 기준으로 정렬하여 시작시간이 빠른 순으로 강의실을 채우기 위함

        // 종료 시간을 관리하기 위한 우선순위 큐
        // Lecture가 아닌 Integer인 이유는 종료시간만 저장하기 때문입니다.
        // 새로운 강의실을 배정받을지 아니면 새 강의실을 배정받을지 종료시간만으로 판별할 수 잇습니다.
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(lectures[0].e);

        // pq의 가장 작은? 종료시간과 현재 강의의 시작시간을 비교해서
        // 종료시간을 업데이트 합니다. => 기존 강의의 종료시간보다 시작시간이 크다면 강의실을 재사용 가능하기 때문에 종료시간을 업뎃해줍니다.
        // 두 번째 강의부터 확인하면서 필요한 강의실 수를 계산
        for(int i = 1; i<N; i++){
            if(pq.peek() <= lectures[i].s)  pq.poll(); // 기존 강의실에 삽입하기 위해서 기존 종료시간을 poll한다.
            pq.offer(lectures[i].e); // 새 강의실 배정
        }

        System.out.println(pq.size());
    }
}
