import java.util.*;
class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] cards = new int[N];
		for (int i=0; i<N; i++)
			cards[i] = sc.nextInt();
		Arrays.sort(cards);
		int M = sc.nextInt();
		int[] queries = new int[M];
		for (int i=0; i<M; i++)
			queries[i] = sc.nextInt();
		sc.close();
		int[] results = new int[M];
		for (int i=0; i<M; i++)
			results[i] = Arrays.binarySearch(cards, queries[i])<0 ? 0 : 1;
		StringBuilder sb = new StringBuilder();
		for (int elem:results)
			sb.append(elem).append(' ');
		System.out.println(sb);
	}
}