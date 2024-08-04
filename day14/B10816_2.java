import java.util.*;
class Main {
	
	private static int[] cards;
	private static int lim;
	private static int len;
	
	/**
	 * target 이상의 카드 중 최소치의 위치를 찾아서 반환한다.
	 */
	private static int searchElemAtLeast(int target) {
		int leftIndex = 0, rightIndex = lim, midIndex = 0;
		int result = len;
		while (leftIndex<=rightIndex) {
			midIndex = (leftIndex+rightIndex)/2;
			if (cards[midIndex]>=target) {
				result = midIndex;
				rightIndex = midIndex-1;	
			} else {
				leftIndex = midIndex+1;
			}
		}
		
		return result;
	}
	
	/**
	 * target 이하의 카드 중 최대치의 위치를 찾아서 반환한다.
	 */
	private static int searchElemAtMost(int target) {
		int leftIndex = 0, rightIndex = lim, midIndex = 0;
		int result = -1;
		while (leftIndex<=rightIndex) {
			midIndex = (leftIndex+rightIndex)/2;
			if (cards[midIndex]<=target) {
				result = midIndex;
				leftIndex = midIndex+1;
			} else {
				rightIndex = midIndex-1;
			}
		}
		
		return result;
	}
	
	private static int countElemExactly(int target) {
		int searchElemAtLeastTarget = searchElemAtLeast(target);
		int searchElemAtMostTaget = searchElemAtMost(target);
		return searchElemAtMostTaget-searchElemAtLeastTarget+1;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] cardsInput = new int[N];
		for (int i=0; i<N; i++)
			cardsInput[i] = sc.nextInt();
		int M = sc.nextInt();
		int[] queries = new int[M];
		for (int i=0; i<M; i++) 
			queries[i] = sc.nextInt();
		sc.close();
		
		cards = cardsInput;
		Arrays.sort(cards);
		len = N;
		lim = N-1;
		
		int[] results = new int[M];
		for (int i=0; i<M; i++)
			results[i] = countElemExactly(queries[i]);
		
		StringBuilder sb = new StringBuilder();
		for (int elem:results)
			sb.append(elem).append(' ');
		System.out.print(sb);
	}
}
