import java.util.*;

class Solution {
	
	public int[] solution(int[] numbers) {
        int[] result = new int[numbers.length];
        //Arrays.fill(result, -1);
        //뒷 큰수를 아직 만나지 않은 원소의 인덱스 모음
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i=0; i<result.length; i++) {
        	while (!stack.isEmpty() && numbers[stack.peekLast()]<numbers[i]) {
        		result[stack.pollLast()] = numbers[i];
        	}
        	stack.add(i);
        } //i loop
        
        //뒷 큰수가 없는 원소에 대해-
        while (!stack.isEmpty()) {
        	result[stack.pollLast()] = -1;
        }
        
        return result;
    }
	
	public static void main(String[] args) {
		Solution sol = new Solution();
		int[] result = null;
		result = sol.solution(new int[] {2, 3, 3, 5});
		System.out.println(Arrays.toString(result));
		result = sol.solution(new int[] {9, 1, 5, 3, 6, 2});
		System.out.println(Arrays.toString(result));
		result = sol.solution(new int[] {130, 52, 18, 41, 91, 113, 62, 6, 87, 156});
		System.out.println(Arrays.toString(result));
		result = sol.solution(new int[] {156, 52, 18, 41, 91, 113, 62, 6, 87, 130});
		System.out.println(Arrays.toString(result));
		int[] numbers = new int[1000000];
		for (int i=0; i<numbers.length; i++)
			numbers[i] = (int)(Math.random()*1000000)+1;
		long time0 = System.nanoTime();
		result = sol.solution(numbers);
		long time1 = System.nanoTime();
		System.out.println((time1-time0)/1e6);
	}
}