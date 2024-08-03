import java.util.*;
class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Set<Integer> cards = new HashSet<>();
		for (int i=0; i<N; i++)
			cards.add(sc.nextInt());
		int M = sc.nextInt();
		int[] queries = new int[M];
		for (int i=0; i<M; i++)
			queries[i] = sc.nextInt();
		sc.close();
		int[] results = new int[M];
		for (int i=0; i<M; i++)
			results[i] = cards.contains(queries[i]) ? 1 : 0;
		StringBuilder sb = new StringBuilder();
		for (int elem:results)
			sb.append(elem).append(' ');
		System.out.print(sb);
	}
}