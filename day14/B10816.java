import java.util.*;
class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] cardsArray = new int[N];
		for (int i=0; i<N; i++)
			cardsArray[i] = sc.nextInt();
		int M = sc.nextInt();
		int[] queries = new int[M];
		for (int i=0; i<M; i++)
			queries[i] = sc.nextInt();
		sc.close();
		
		Map<Integer, Integer> cards = new HashMap<>();
		for (int card:cardsArray) 
			cards.merge(card, 1, (a, b)->a+1);
		int[] results = new int[M];
		for (int i=0; i<M; i++)
			results[i] = cards.getOrDefault(queries[i], 0);
		
		StringBuilder output = new StringBuilder();
		for (int elem:results)
			output.append(elem).append(' ');
		System.out.print(output);
	}
}