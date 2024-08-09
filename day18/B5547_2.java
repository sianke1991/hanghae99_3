/* 
    건물 외부에서 보이는 벽을 조사하기 위해서
    건물의 외부라고 "확실"하게 말할 수 있는 (0, 0)에서 탐색을 시작한다.
    => H*W 격자를 입력받으면 (그 외부를 조사해야 하므로) (H+2)*(W+2) 격자를 탐색한다.
    건물의 외부에서 탐색을 시작하여 건물과 닿는 벽을 만나게 되면 counter 값을 1 증가시킨다.
    (하지만 건물 내부로 탐색을 진행하지는 않는다.)
    이 과정을 [건물 외부의 영역에 대해 탐색할 때 까지] 반복한다.
    6각형 격자에 대해서 한 노드에 대해 인접한 여섯 노드의 좌표는 별도의 파일 참조

	6각형 격자에 대해 너비우선탐색을 수행할 때 더 깔끔한 방법을 적용한 코드
*/
import java.util.*;
class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        //열의 개수: 격자의 가로 길이
		int numCols = sc.nextInt();
        //행의 개수: 격자의 세로 길이
		int numRows = sc.nextInt();
        //탐색할 격자
		int[][] grid = new int[numRows+2][numCols+2];
		for (int row=1; row<=numRows; row++) {
			for (int col=1; col<=numCols; col++) {
				grid[row][col] = sc.nextInt();
			} //col loop
		} //row loop
		sc.close();
        //외부에서 보이는 벽의 길이
		int result = 0;
		Node.numRows = numRows+2;
		Node.numCols = numCols+2;
        //해당 노드가 방문한 적이 있는지 여부
		boolean[][] visited = new boolean[Node.numRows][Node.numCols];
		Queue<Node> q = new ArrayDeque<>();
		visited[0][0] = true;
		q.add(new Node(0, 0));
		while (!q.isEmpty()) {
			Node currentNode = q.poll();
			for (Node nextNode:currentNode.nextNodes()) {
                //한 노드에 대해 이웃한 노드가 방문한 적이 있으면 그 노드는 탐색을 진행하지 않는다.
				if (visited[nextNode.row][nextNode.col]) {
					continue;
				}
                //한 노드에 대해 이웃한 노드가 건물일 경우 [외부에서 보이는 벽의 길이]를 1 증가시킨다.
				if (grid[nextNode.row][nextNode.col]==1) {
					result++;
					continue;
				}
                //한 노드에 대해 이웃한 노드가 방문한 적이 없으면 그 이웃한 노드에 대해서 탐색을 진행한다.
				visited[nextNode.row][nextNode.col] = true;
				q.add(nextNode);
			} //nextNode loop
		} //while loop
		System.out.println(result);
	}
	
	private static class Node {
        //격자의 행의 개수
		static int numRows;
        //격자의 열의 개수
		static int numCols;
        static int[] dRow = {-1, -1, 0, 1, 1, 0};
        static int[][] dCol = {
            {-1, 0, 1, 0, -1, -1}, //row가 짝수일 때 사용
            {0, 1, 1, 1, 0, -1}    //row가 홀수일 때 사용
        };
        //해당 노드의 행 번호
		public final int row;
        //해당 노드의 열 번호
		public final int col;
		public Node(int row, int col) {
			this.row = row;
			this.col = col;
		}
        //해당 노드에 대해 인접한 여섯 노드의 목록을 반환한다.
		public List<Node> nextNodes() {
			List<Node> result = new ArrayList<>();
			for (int dir=0; dir<6; dir++) {
                int nextRow = this.row+dRow[dir];
                int nextCol = this.col+dCol[this.row%2][dir];
                if (nextRow<0 || nextRow>=numRows || nextCol<0 || nextCol>=numCols)
                    continue;
                result.add(new Node(nextRow, nextCol));
            } //dir loop
			return result;
		}
	}
}