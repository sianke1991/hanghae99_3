import java.util.*;
public class Main {
	/**
	 * dpArray[i]는 i 번 노드를 루트로 하는 서브트리에 대해 소식이 퍼지는데 걸리는 시간을 의미한다.<br/>
	 * dpArray[0]는 전체 트리에 소식이 퍼지는데 걸리는 시간이 되고, 이 값을 구하는 것이 목표이다.<br/>
	 * dpArray는 -1로 초기화하여 특정 값을 아직 구하지 않았음을 나타낸다.
	 */
	private static int[] dpArray;

	/**
	 * childrenOf.get(i)는 i번 사원의 직속 부하 사번 목록이다.<br/>
	 * j 번 사원이 직속 부하기 없을 때 (트리에서 리프 노드일 경우) childrenOf.get(j)는 an empty list이다.
	 */
	private static List<List<Integer>> childrenOf;
	
	/**
	 * 각 서브트리에 소식이 전달되는데 걸리는 시간들을 입력받아,<br/>
	 * 한 단계 위의 서브트리에 소식이 전달되는데 걸리는 시간을 계산하여 반환한다.
	 */
	private static int requiredTimeOf(List<Integer> times) {
		final int sz = times.size();
		Collections.sort(times);
		int result = 0;
		for (int i=0; i<sz; i++) {
			result = Math.max(result, times.get(i)+sz-i);
		}
		return result;
	}
	
	/**
	 * 입력 받은 노드를 루트 노드로 하는 서브트리에 소식이 전달되는데 걸리는 시간을 반환한다.<br/>
	 * 메서드 호출 전 static 멤버 필드를 초기화해야 한다.
	 */
	private static int requiredTimeWhenRootIs(int node) {
		//node를 루트로 하는 서브트리에 대해 시간을 이미 구한 경우-
		if (dpArray[node]>=0) {
			return dpArray[node];
		}
		//node가 리프 노드일 경우
		if (childrenOf.get(node).isEmpty()) {
			dpArray[node] = 0;
			return dpArray[node];
		}
		
		List<Integer> times = new ArrayList<>();
		for (int child:childrenOf.get(node)) {
			times.add(requiredTimeWhenRootIs(child));
		} //child loop
		dpArray[node] = requiredTimeOf(times);
		return dpArray[node];
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//직원의 수
		int N = sc.nextInt();
		int[] parentOf = new int[N];
		for (int i=0; i<N; i++) 
			parentOf[i] = sc.nextInt();
		sc.close();
		dpArray = new int[N];
		Arrays.fill(dpArray, -1);
		childrenOf = new ArrayList<>();
		for (int i=0; i<N; i++) childrenOf.add(new ArrayList<>());
		//0번 사원은 특정 사원의 부하 사원이 되지 않는다. 따라서 아래 루프는 1번부터 수행한다.
		for (int i=1; i<N; i++) childrenOf.get(parentOf[i]).add(i);
		
		int result = requiredTimeWhenRootIs(0);
		System.out.println(result);
	}
}