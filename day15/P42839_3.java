/* 백트래킹을 사용한 풀이 */

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
	
	private void recursive(String value) {
		int currentNumber = value.isEmpty() ? 0 : Integer.parseInt(value);
		if (isPrime(currentNumber))
			numSet.add(currentNumber);
		for (int i=0; i<used.length; i++) {
			if (!used[i]) {
				used[i] = true;
				recursive(new StringBuilder(value).append(numbers.charAt(i)).toString());
				used[i] = false;
			}
		} //i loop
	}
	
	public int solution(String numbers) {
		this.used = new boolean[numbers.length()];
		this.numSet.clear();
		this.numbers = numbers;
		recursive("");
		return this.numSet.size();
	}
}