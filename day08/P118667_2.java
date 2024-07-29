/* 
queue1 = [3, 2, 7, 2]
queue2 = [4, 6, 5, 1]
queue1의 합 sum1 = 14
queue2의 합 sum2 = 16
전체 원소의 합 sum1+sum2 = 30
따라서 두 큐의 합을 일치시키기 위해선 각 큐의 원소의 합은 15(=target)가 되어야 한다.
현재 sum1은 target보다 작으므로 queue2에서 queue1으로 원소를 줘야 한다.
(queue2에서 queue1으로 원소를 준 이후)
queue1 = [3, 2, 7, 2, 4], sum1 = 18
queue2 = [6, 5, 1], sum2 = 12
이 상태에서 sum1은 target보다 크므로 queue1에서 queue2로 원소를 줘야 한다.
(queue1에서 queue2로 원소를 준 이후)
queue1 = [2, 7, 2, 4], sum1 = 15
queue2 = [6, 5, 1, 3], sum2 = 15

두 번의 조작으로 두 큐의 합을 일치시켰으므로 2를 반환한다.
sum1과 target을 계속 비교하면서
=> sum1과 target이 일치하면 그 때의 조작 횟수를 반환한다.
=> sum1이 target보다 크면 queue1의 원소를 빼서 queue2에 더한다. (sum1은 감소)
=> sum1이 target보다 작으면 queue2의 원소를 빼서 queue1에 더한다. (sum1은 증가)
*/

import java.util.*;
class Solution {
	
	public int solution(int[] queue1, int[] queue2) {
		//q1 및 sum1 구성
		Queue<Integer> q1 = new ArrayDeque<>();
		long sum1 = 0;
		for (int elem:queue1) {
			q1.add(elem);
			sum1 += elem;
		}
		//q2 및 sum2 구성
		Queue<Integer> q2 = new ArrayDeque<>();
		long sum2 = 0;
		for (int elem:queue2) {
			q2.add(elem);
			sum2 += elem;
		}
		long target = sum1+sum2;
		if ((target&1)==1) return -1; //if target is odd
		target >>= 1;
		int result = 0;
		for (int lim=4*queue1.length; result<=lim; result++) {
			//sum1이 target에 도달하면 곧바로 result 반환
			if (sum1==target) {
				return result;
			}
			//sum1이 target보다 크다면 (queue1의 합이 queue2보다 크다면)
			//queue1에서 원소를 하나 빼서 queue2에 넣는다.
			else if (sum1>target) {
				sum1 -= q1.peek();
				q2.add(q1.poll());
			}
			//sum1이 target보다 작다면 (queue1의 합이 queue2보다 작다면)
			//queue2에서 원소를 하나 빼서 queue1에 넣는다.
			else {
				sum1 += q2.peek();
				q1.add(q2.poll());
			}
		} //result loop
		
		return -1;
	}
}