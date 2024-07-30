import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        Queue<Long> pq = new PriorityQueue<>();
        for (long elem:scoville)
            pq.add(elem);
        while (pq.peek()<K) { //K 보다 맵지 않은 음식이 남아있다면-
            if (pq.size()<2) //더 이상 음식 섞기가 불가능한 경우-
                return -1;
            long first = pq.poll();
            long second = pq.poll();
            pq.add(first+second*2);
            answer++;
        }
        
        return answer;
    }
}