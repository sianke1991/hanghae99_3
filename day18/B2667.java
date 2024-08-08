/*
    지도 내에서 너비 우선 탐색을 시작할 노드를 찾은 뒤,
    그 지점에서 너비 우선 탐색을 수행한다.

    이 과정을 지도 내 모든 가구를 방문할 때 까지 반복한다.
*/
import java.util.*;
class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
    //지도의 한 변의 길이
		int gridSize = Integer.parseInt(sc.nextLine());
    //격자 각 칸의 정보 ('0': 빈 공간, '1': 집)
		char[][] grid = new char[gridSize][];
		for (int i=0; i<gridSize; i++) 
			grid[i] = sc.nextLine().toCharArray();
		sc.close();
		
		final int[] dRow = {-1, 0, 1, 0};
		final int[] dCol = {0, 1, 0, -1};
    //각 격자를 방문했는지 여부
		boolean[][] visited = new boolean[gridSize][gridSize];
    //각 단지의 가구 수
		List<Integer> groupSizes = new ArrayList<>();
		for (int row=0; row<gridSize; row++) {
			for (int col=0; col<gridSize; col++) {
				if (grid[row][col]=='0' || visited[row][col])
					continue;
        //너비 우선 탐색을 시작할 노드(row, col)를 찾은 경우-
        //너비 우선 탐색을 시작한다.
				Queue<Node> q = new ArrayDeque<>();
        //이번 단지에 속할 가구의 개수
				int groupSize = 1;
				visited[row][col] = true;
				q.add(new Node(row, col));
				while (!q.isEmpty()) {
					Node currentNode = q.poll();
					int currentRow = currentNode.row;
					int currentCol = currentNode.col;
					for (int dir=0; dir<4; dir++) {
						int nextRow = currentRow+dRow[dir];
						int nextCol = currentCol+dCol[dir];
						if (nextRow<0 || nextRow>=gridSize || nextCol<0 || nextCol>=gridSize)
							continue;
						if (grid[nextRow][nextCol]=='0' || visited[nextRow][nextCol])
							continue;
						groupSize++;
						visited[nextRow][nextCol] = true;
						q.add(new Node(nextRow, nextCol));
					} //dir loop
				} //while loop

        //너비 우선 탐색이 종료되면 이번 단지에 속한 가구의 개수를 리스트에 담는다.
				groupSizes.add(groupSize);
			} //col loop
		} //row loop
		Collections.sort(groupSizes);
		StringBuilder output = new StringBuilder();
		output.append(groupSizes.size()).append('\n');
		for (int elem:groupSizes)
			output.append(elem).append('\n');
		System.out.print(output);
	}
	
	private static class Node {
		public final int row;
		public final int col;
		public Node(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
}