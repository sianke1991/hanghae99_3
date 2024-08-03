/*
28분의 시간이 주어졌다면 한 사람 당 7분 소요되는 심사대(이하, 7분 심사대)에서는 4명이 입국심사를 받을 수 있다.
한 사람 당 10분 소요되는 심사대(이하, 10분 심사대)에서는 2명이 입국심사를 받을 수 있다.
따라서 28분의 시간 동안 총 6명이 입국심사를 받을 수 있다. (다시 말해 6명이 입국심사를 받는데 28분의 시간은 충분한 시간이다.)
이보다 짧은 27분의 시간이 주어졌다면 7분 심사대에서는 3명이 입국심사를 받을 수 있다.
10분 심사대에서는 2명이 입국 심사를 받을 수 있다.
따라서 27분의 시간 동안 총 5명이 입국심사를 받을 수 있다. (다시 말해 6명이 입국심사를 받는데 27분은 부족한 시간이다.)
이상으로 입력값 n = 6, times = [7, 10]일 때 반환값이 28 이어야 하는 이유의 설명을 마친다.

입국심사에 소요되는 시간이 주어졌을 때 입국심사를 받을 수 있는 총 인원 수는 [각 심사대에서 입국심사를 받은 인원수의 합]으로 구할 수 있다.
이 인원수를 입력값인 n 이상으로 만드는 시간의 최소치를 찾는다.
입국심사의 시간을 증가시키면 입국심사를 받을 수 있는 총 인원 수는 증가하고,
반대로 입국심사의 시간을 감소시키면 입국심사를 받을 수 있는 총 인원 수는 감소한다.
따라서 입국심사의 시간을 기준으로 이진탐색을 사용할 수 있다.
이진탐색의 상한은 [입국심사에 소요되는 시간은 이 값을 넘을 수 없다]는 값으로 설정하면 되는데
[배열 times 내 최소치 * n](=가장 빠르게 심사하는 심사대가 n 명의 사람들을 전부 처리할 때 필요한 시간)을 사용할 수 있다.
 */

class Solution {
	/**각 창구의 통관에 걸리는 시간*/
	private int[] timesArr;
	
	private long maxNumPeoplePassIn(long totalTime) {
		long result = 0L;
		for (int time:timesArr)
			result+=totalTime/time;
		return result;
	}
	
	public long solution(int n, int[] times) {
		timesArr = times;
		long leftIndex=0, rightIndex=((long)times[0])*n, midIndex = 0;
		long result=0;
		while (leftIndex<=rightIndex) {
			midIndex = (leftIndex+rightIndex)/2;
			long maxPeoplePass = maxNumPeoplePassIn(midIndex);
			if (maxPeoplePass<n) {
				leftIndex = midIndex+1;
			} else {
				result = midIndex;
				rightIndex = midIndex-1;
			}
		} //while loop
		return result;
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		System.out.println(sol.solution(6, new int[] {7, 10}));
		System.out.println(sol.solution(10, new int[] {7, 10, 12}));
	}
}
