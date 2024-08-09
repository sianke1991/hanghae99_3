/*
    이름을 입력하기 위해 필요한 상하 입력의 개수는 쉽게 구할 수 있다.
    좌우 입력 개수의 최소치를 구하기 위해 [너비 우선 탐색]을 적용할 수 있다.
*/
import java.util.*;
class Solution {
    //해당 문자 하나를 입력하기 위해 필요한 상하 입력 개수
    private int verticalMoveFor(char ch) {
        return Math.min(ch-'A', '['-ch);
    }
    
    //문자 배열을 입력하기 위해 필요한 상하 입력 개수
    private int verticalMoveFor(char[] targetName) {
        int result = 0;
        for (char ch:targetName)
            result += verticalMoveFor(ch);
        return result;
    }
    
    //좌우 방향 열거체
    private static enum Direction {
        LEFT, RIGHT;
        
        //from 위치에서 한 칸 이동했을 때 나오는 커서 위치를 반환한다.
        //전체 문자열 길이(len)를 추가로 입력받는다.
        public int nextIndexFrom(int from, int len) {
            int result = from-1+2*this.ordinal();
            //맨 왼쪽에서 왼쪽으로 이동한 경우-
            if (result<0) return len-1;
            //맨 오른쪽에서 오른쪽으로 이동한 경우-
            if (result>=len) return 0;
            return result;
        }
    }
    
    //너비 우선 탐색에 사용할 노드 객체
    private static class Node {
        //현재 커서의 위치
        int currentPosition;
        //현재까지 좌우 입력 횟수
        int currentNumMoves;
        //현재 입력된 이름
        char[] currentName;
        //현재까지 좌우 입력을 변경한 횟수
        int currentNumUTurns;
        //최근 좌우 입력의 방향 (좌우 입력을 하지 않은 경우 이 필드의 값은 null이 된다.)
        Direction recentDirection;
        
        private Node(int len) {
            this.currentPosition = 0;
            this.currentNumMoves = 0;
            this.currentName = new char[len];
            this.currentNumUTurns = 0;
            this.recentDirection = null;
        }
        
        //너비 우선 탐색을 시작할 노드를 반환한다.
        public static Node initialNode(char[] targetName) {
            Node result = new Node(targetName.length);
            Arrays.fill(result.currentName, 'A');
            result.currentName[0] = targetName[0]; //인덱스 0번 글자는 처음부터 맞추고 시작한다.
            return result;
        }
        
        public Node clone() {
            Node result = new Node(this.currentName.length);
            result.currentPosition = this.currentPosition;
            result.currentNumMoves = this.currentNumMoves;
            System.arraycopy(this.currentName, 0, result.currentName, 0, this.currentName.length);
            result.currentNumUTurns = this.currentNumUTurns;
            result.recentDirection = this.recentDirection;
            return result;
        }
        
        //현재 노드가 이름 입력을 완성했는지 여부를 반환한다.
        public boolean isCompleted(char[] targetName) {
            return Arrays.equals(targetName, this.currentName);
        }
        
        //입력받은 방향으로 한 칸 이동한다.
        public void move(Direction direction, char[] targetName) {
            this.currentPosition = direction.nextIndexFrom(this.currentPosition, targetName.length);
            this.currentNumMoves++;
            this.currentName[this.currentPosition] = targetName[this.currentPosition];
            if (this.recentDirection!=null && this.recentDirection!=direction)
                this.currentNumUTurns++;
            this.recentDirection = direction;
        }
        
        //현재 노드에서 가능한 좌우 입력 목록을 반환한다.
        public List<Direction> possibleDirections() {
            if (this.recentDirection==null || this.currentNumUTurns<1)
                return List.of(Direction.LEFT, Direction.RIGHT);
            else
                return List.of(this.recentDirection);
        }
    }
    
    public int solution(String name) {
        char[] targetName = name.toCharArray();
        Queue<Node> q = new ArrayDeque<>();
        q.add(Node.initialNode(targetName));
        while (!q.isEmpty()) {
            Node currentNode = q.poll();
            if (currentNode.isCompleted(targetName))
                return currentNode.currentNumMoves + verticalMoveFor(targetName);
            for (Direction direction:currentNode.possibleDirections()) {
                Node nextNode = currentNode.clone();
                nextNode.move(direction, targetName);
                q.add(nextNode);
            } //direction loop
        } //while loop
        
        return -1;
    }
}

/*
테스트 케이스
"JEROEN"                56
"JAN"                   23
"JAZ"                   11
"BBAAAAAAAAAAAAAAB"     6
"BABAAAAAAAAAAAAAB"     7
"AAAAAA"                0
"AAAACB"                5
"AABAAAAAAABBB"         11
"CANAAAAANAN"           48
"ABAAAAABAB"            8
"CAAAAAAAAA"            2
"BBBAAB"                8
*/