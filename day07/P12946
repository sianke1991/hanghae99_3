import java.util.*;
public class Solution {
	
	/**
	 * n개의 원판을 from 기둥에서 to 기둥으로 옮기기 위해
	 * 수행해야 할 움직임 목록을 반환한다.
	 */
	public List<int[]> move(int n, int from, int to) {
        List<int[]> result = new ArrayList<>();
        //base case: 원판이 1 개일 경우-
        if (n==1) {
        	result.add(new int[] {from, to});
            return result;
        }
        //임시 기둥의 번호
        int tmp = 6-from-to;
        //step a: n-1 개의 원판을 from 에서 tmp 로 옮긴다.
        result.addAll(move(n-1, from, tmp));
        //step b: 1개의 원판을 from 에서 to 로 옮긴다.
        result.add(new int[] {from, to});
        //step c: n-1 개의 원판을 tmp 에서 to 로 옮긴다.
        result.addAll(move(n-1, tmp, to));
        return result;
    }
	
	public int[][] solution(int n) {
        List<int[]> result = move(n, 1, 3);
        int[][] resultArray = new int[result.size()][];
        for (int i=0; i<resultArray.length; i++)
        	resultArray[i] = result.get(i);
        return resultArray;
    }

	public static void main(String[] args) {
		Solution sol = new Solution();
		int[][] result = null;
		result = sol.solution(3);
		for (int[] move:result) {
			System.out.print(Arrays.toString(move));
			System.out.print(' ');
		}
	}
}