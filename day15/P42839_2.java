/*백트래킹을 사용한 풀이*/

import java.util.*;
class Solution {
	private boolean[] isNotPrime = new boolean[1000_0001];
	private boolean isNotPrimeFixed = false;
	/**백트랙킹 중 사용할 배열: used[k] = true 시 k 번째 문자가 사용되었음을 의미*/
	private boolean[] used;
	/**백트랙킹 중 사용할 정수 집합: 생성된 정수의 중복을 제거한다.*/
	private Set<Integer> numSet = new HashSet<>();
	private String numbers;
	
	/**
	 * 수를 하나 입력받아서 이 수가 소수인지 여부를 판단한다.
	 * @param number
	 * @return
	 */
	private boolean isPrime(int number) {
		if (isNotPrimeFixed) {
			return !isNotPrime[(number)];
		}
		isNotPrimeFixed = true;
		int sqrt = (int)(Math.sqrt(isNotPrime.length));
		isNotPrime[0] = true;
		isNotPrime[1] = true;
		for (int i=2; i<=sqrt; i++) {
			if (isNotPrime[i])
				continue;
			for (int j=i*i; j<isNotPrime.length; j+=i)
				isNotPrime[j] = true;
		} //i loop
		return !isNotPrime[(number)];	
	}
	
	private void dfs(int maxDepth, String str) {
		//구성한 문자열의 길이가 원하는 자리수가 되었다면-
		if (str.length()==maxDepth) {
			numSet.add(Integer.parseInt(str));
			return;
		}
		
		for (int j=0; j<numbers.length(); j++) {
			if (!used[j]) { //인덱스 j가 사용되지 않았다면-
				//사용 처리
				used[j] = true;
				//인덱스 j에 위치한 문자를 붙이고 다른 숫자 탐색
				dfs(maxDepth, str+numbers.charAt(j));
				//반환 처리
				used[j] = false;
			}
		} //j loop (index loop)
	}
	
	public int solution(String numbers) {
		this.numSet.clear();
		this.numbers = numbers;
		this.used = new boolean[numbers.length()];
		for (int i=1; i<=numbers.length(); i++) {
			//길이 1 짜리, 2 짜리, ... 문자열을 구성하여 numSet을 구성한다.
			dfs(i, "");
		} //i loop (depth loop)
		int result = 0;
		for (int num:numSet) {
			if (isPrime(num))
				result++;
		}
		return result;
	}
}