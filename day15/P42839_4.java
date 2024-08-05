/* 문자 순열을 사용한 풀이 */

import java.util.*;
class Solution {	
	private boolean[] isNotPrime = new boolean[1000_0001];
	private boolean isNotPrimeFixed = false;
	/**생성된 정수의 중복을 제거한다.*/
	private Set<Integer> numSet = new HashSet<>();
	
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
	
	/**
	 * permutation("", str)을 호출하여 str의 각 문자를 조립하여 생성할 수 있는 문자열을 방문한다.
	 * @param prefix
	 * @param str
	 */
	private void permutation(String prefix, String str) {
		int n = str.length();
		if (!prefix.equals("")) numSet.add(Integer.parseInt(prefix));
		for (int i=0; i<n; i++) {
			// prefix에 str[i]를 더하고, str에 str[i]를 제거하여 재귀 호출
			// str은 [문자열 조립에 사용할 수 있는 문자]가 남아있다.
			permutation(prefix + str.charAt(i), str.substring(0, i)+str.substring(i+1, n));
		}
	}

	public int solution(String numbers) {
		numSet.clear();
		permutation("", numbers);
		int result = 0;
		for (int num:numSet) {
			if (isPrime(num))
				result++;
		}
		return result;
	}
}
