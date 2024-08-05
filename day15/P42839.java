/* 너비우선탐색을 사용한 풀이 */

import java.util.*;
class Solution {
	private boolean[] isNotPrime = new boolean[1000_0001];
	private boolean isNotPrimeFixed = false;
	
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
	 * 숫자로 구성된 문자열을 입력받아, 이 숫자를 조합하여 만들 수 있는 정수 집합을 반환한다.
	 * @param numbers
	 * @return
	 */
	private Set<Integer> numbersFrom(String numbers) {
		Set<Integer> result = new HashSet<>();
		
		// numbers의 각 문자를 조합하는데 사용할 수 있는 인덱스 목록
		List<Integer> possibleIndices = new ArrayList<>();
		for (int i=0; i<numbers.length(); i++)
			possibleIndices.add(i);
		
		// [선택한 인덱스 목록]을 노드로 삼고 너비 우선 탐색을 사용한다.
		// 인덱스 선택 [1, 6]과 [6, 1]는 서로 다른 선택이므로 [선택한 인덱스 집합]이 아닌 [선택한 인덱스 목록]을
		// 노드로 삼는다.
		Set<List<Integer>> visitedNodes = new HashSet<>();
		Queue<List<Integer>> q = new ArrayDeque<>();
		q.add(new ArrayList<>());
		
		while (!q.isEmpty()) {
			List<Integer> currentNode = q.poll();
			for (int index:possibleIndices) {
				if (currentNode.contains(index)) //해당 노드가 index를 포함하고 있는 경우 동일한 index를 담시 담지 않는다.
					continue;					//문자열 내 한 숫자를 두 번 이상 사용하는 것을 막기 위함
				List<Integer> nextNode = new ArrayList<>(currentNode);
				nextNode.add(index);
				if (visitedNodes.add(nextNode)) {
					q.add(nextNode);
				}
			} //index loop
		} //while loop
		
		// 인덱스 집합을 문자열로 변환하고 다시 정수로 변환하여 result에 담는다.
		for (List<Integer> node:visitedNodes) {
			StringBuilder strNode = new StringBuilder();
			for (int index:node) {
				strNode.append(numbers.charAt(index));
			}
			result.add(Integer.parseInt(strNode.toString()));
		}
		
		return result;
	}
	
    public int solution(String numbers) {
    	Set<Integer> numberSet = numbersFrom(numbers);
//    	System.out.println(numberSet);
    	int result = 0;
    	for (int number:numberSet) {
    		if (isPrime(number))
    			result++;
    	}
    	return result;
    }
}