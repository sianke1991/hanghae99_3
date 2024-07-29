import java.util.*;

class Solution {
		/**
		 * 특정 기능의 작업 진도와 작업 속도를 입력받아,
		 * 해당 기능이 개발 완료될 때 까지 필요한 날짜 수를 반환한다.
		 */
    private static int daysRequired(int progress, int speed) {
        return (100-progress+speed-1)/speed;
    }
    
    public int[] solution(int[] progresses, int[] speeds) {
        //각 작업에 대해 완성되기까지 필요한 날짜 수를 보관하는 Queue
        Queue<Integer> daysRequired = new ArrayDeque<>();
        for (int i=0; i<progresses.length; i++)
            daysRequired.add(daysRequired(progresses[i], speeds[i]));
        
        //한 번 배포될 때 함께 배포되는 기능 수를 담은 List
        List<Integer> result = new ArrayList<>();
        
        while (!daysRequired.isEmpty()) {
		        //Queue에 남아있는 기능 중 가장 번호가 앞선 기능의
		        //개발 완료까지 필요한 날짜 수
            int front = daysRequired.poll();
            //Queue에 남아있는 기능 중 가장 번호가 앞선 기능이
            //배포될 때 함께 배포되는 기능 수
            int cnt = 1;
            while (!daysRequired.isEmpty() && daysRequired.peek()<=front) {
                daysRequired.poll();
                cnt++;
            } //while loop (inner loop)
            result.add(cnt);
        } //while loop (outer loop)
        
        int[] resultArray = new int[result.size()];
        for (int i=0; i<resultArray.length; i++)
            resultArray[i] = result.get(i);
        return resultArray;
    }
}