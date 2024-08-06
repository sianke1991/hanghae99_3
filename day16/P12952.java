class Solution {
    //보드 크기
    private int boardSize;
    //특정 열이 차지되었는지 여부
    private boolean[] colUsed;
    //특정 우상향 대각선이 차지되었는지 여부
    private boolean[] ascDiagUsed;
    //특정 우하향 대각선이 차지되었는지 여부
    private boolean[] descDiagUsed;
    //퀸 n개를 보드에 놓을 수 있는 경우의 수
    private int cnt;

    //우상향 대각선 번호
    private int ascDiag(int row, int col) {
        return row+col;
    }
    
    //우하향 대각선 번호
    private int descDiag(int row, int col) {
        return col-row+boardSize-1;
    }
    
    private void recursive(int row) {
        //베이스 케이스
        if (row==boardSize) {
            cnt++;
            return;
        }
        for (int col=0; col<boardSize; col++) {
            int ascDiag = ascDiag(row, col);
            int descDiag = descDiag(row, col);
            if (colUsed[col]) continue;
            if (ascDiagUsed[ascDiag]) continue;
            if (descDiagUsed[descDiag]) continue;
            colUsed[col] = true;
            ascDiagUsed[ascDiag] = true;
            descDiagUsed[descDiag] = true;
            recursive(row+1);
            descDiagUsed[descDiag] = false;
            ascDiagUsed[ascDiag] = false;
            colUsed[col] = false;
        } //col loop
    }
    
    public int solution(int n) {
        if (n<=0) return 1;
        boardSize = n;
        colUsed = new boolean[n];
        ascDiagUsed = new boolean[2*n-1];
        descDiagUsed = new boolean[2*n-1];
        cnt = 0;
        recursive(0);
        return cnt;
    }
}

/*
n-queen 문제 테스트 케이스
n       returns
0       1
1       1
2       0
3       0
4       2
5       10
6       4
7       40
8       92
9       352
10      724
11      2680
12      14200
*/