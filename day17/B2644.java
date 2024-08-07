import java.util.*;
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //노드 개수: 인원수
        int numNodes = sc.nextInt();
        //출발 노드: 촌수를 구할 사람 중 하나
        int fromNode = sc.nextInt();
        //도착 노드: 촌수를 구할 사람 중 하나
        int toNode = sc.nextInt();
        //간선 개수: 입력 받을 친자 관계 수
        int numEdges = sc.nextInt();
        int[][] edges = new int[numEdges][2];
        for (int i=0; i<numEdges; i++) {
            edges[i][0] = sc.nextInt();
            edges[i][1] = sc.nextInt();
        }
        sc.close();
        
        //인접 리스트: 특정 인원을 기준으로 친자 관계가 있는 사람 목록(...)
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i=0; i<=numNodes; i++)
            adjList.add(new ArrayList<>());
        for (int[] edge:edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        
        int result = 0;
        //fromNode 에서 거리가 result 만큼 떨어져 있는 노드 목록
        List<Integer> nextNodes = new ArrayList<>();
        Queue<Integer> q = new ArrayDeque<>();
        //특정 노드가 탐색을 완료했는지 여부
        boolean[] visited = new boolean[numNodes+1];
        q.add(fromNode);
        visited[fromNode] = true;
        while (!q.isEmpty()) {
		        //fromNode에서 거리가 result 만큼 떨어져 있는 노드를 한 번에 처리하기 위해
		        //q가 빌 때 까지 q에서 원소를 제거하면서 처리한다.
            while (!q.isEmpty()) {
                int currentNode = q.poll();
                if (currentNode==toNode) {
                    System.out.println(result);
                    return;
                }
                for (int nextNode:adjList.get(currentNode)) {
                    if (visited[nextNode]) continue;
                    nextNodes.add(nextNode);
                    visited[nextNode] = true;
                } //nextNode loop
            }
            result++;
            q.addAll(nextNodes);
            nextNodes.clear();
        } //while loop
        
        //q가 빌 때 까지 toNode에 도달하지 못한 경우 두 사람은
        //촌수 관계가 없는 것이다.
        System.out.println(-1);
    }
}