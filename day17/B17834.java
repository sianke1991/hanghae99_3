/*
시작 위치에서 [사자와 토끼의 거리가 짝수로 해석될 수 있으면]
토끼는 사자에 잡힐 수 있다. => 매 턴 사자와 토끼 사이의 거리는 +2, 0, -2 만큼 변하고, 그 거리가 0이 되면 토끼는 사자에 잡히는 것이다.
예를 들어 
        /-------------\
토끼 ---                ---사자
        \____________/
이렇게 두 길이 있는데 윗 길은 거리 4, 아랫 길은 거리 3일 경우
최소 거리는 3이지만 토끼와 사자 사이에 거리가 (짝수) 4인 길이 있기 때문에 토끼는 사자에 잡힐 수 있다.

보드판을 그래프로 표현할 때 이 그래프가 이분 그래프
(그래프의 노드를 두 색으로 칠하되, 서로 인접한 두 노드가 다른 색을 가지도록 칠할 수 있는 그래프)
인지 여부를 따진다.
예를 들어 그래프를 붉은색, 푸른색으로 칠할 수 있는 이분 그래프라면
해당 그래프에서 붉은색 노드와 푸른색 노드는 거리가 (어떠한 경로를 선택하더라도-) 홀수이다.
따라서 사자가 붉은색 노드, 토끼가 푸른색 노드에 있거나 반대로
사자가 푸른색 노드, 토끼가 붉은색 노드에 있으면 이 게임은 영원히 끝나지 않는다.
=> 그래프가 이분그래프이라면 붉은색 노드 개수 * 푸른색 노드 개수 * 2를 출력한다.
 */

import java.util.*;
class Main {
    //노드의 개수 (보드에서 수풀의 개수)
    private static int numNodes;
    //간선의 개수 (보드에서 오솔길의 개수)
    private static int numEdges;
    //인접 리스트
    private static List<List<Integer>> adjList;
    //붉은색을 나타내는 수
    private static final int RED = 1;
    //푸른색을 나타내는 수
    private static final int BLUE = 2;
    //그래프가 이분 그래프일 경우 붉은색 노드 및 푸른색 노드의 개수 정보를 담고 있는 배열
    //eg. numColours[RED] := 붉은색 노드의 개수
    //eg. numColours[BLUE] := 푸른색 노드의 개수
    private static long[] numColours = {0, 0, 0};
    
    //해당 그래프가 이분 그래프인지 여부를 반환한다.
    //멤버 필드 numColours를 갱신한다.
    private static boolean isBipartite() {
        int[] visited = new int[numNodes+1]; //해당 노드의 색 정보를 담고 있는 배열
                                            //visited[i] == 0     := i 번째 노드는 아직 탐색하지 않음
                                            //visited[i] == RED   := i 번째 노드는 붉은색으로 칠해짐
                                            //visited[i] == BLUE  := i 번째 노드는 푸른색으로 칠해짐
        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);
        visited[1] = RED;
        numColours[RED]++;
        while (!q.isEmpty()) {
            int currentNode = q.poll();
            int currentColour = visited[currentNode];
            //currentNode에 인접한 노드가 가져야 할 색상
            int oppositeColour = 3-currentColour;
            for (int nextNode:adjList.get(currentNode)) {
                if (visited[nextNode]==currentColour) //인접한 노드가 해당 노드와 같은 색이 칠해져 있다면 해당 그래프는 이분 그래프가 아니다.
                    return false;
                if (visited[nextNode]==oppositeColour) //인접한 노드가 해당 노드와 다른 색이 칠해져 있다면 해당 인접한 노드는 다시 탐색하지 않는다.
                    continue;
                q.add(nextNode);
                visited[nextNode] = oppositeColour;
                numColours[oppositeColour]++;
            } //nextNode loop
        } //while loop
        
        return true;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        numNodes = sc.nextInt();
        numEdges = sc.nextInt();
        int[][] edges = new int[numEdges][2];
        for (int i=0; i<numEdges; i++) {
            edges[i][0] = sc.nextInt();
            edges[i][1] = sc.nextInt();
        }
        sc.close();
        

        //step a. 인접 리스트 구성
        adjList = new ArrayList<>();
        for (int i=0; i<=numNodes; i++) adjList.add(new ArrayList<>());
        for (int[] edge:edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        
        //step b. 이분 그래프 여부 확인
        if (isBipartite()) {
            long result = 1L;
            result *= numColours[RED];
            result *= numColours[BLUE];
            result *= 2;
            System.out.println(result);
        } else {
            System.out.println(0);
        }
    }
}