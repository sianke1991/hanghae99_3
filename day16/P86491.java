class Solution {
    public int solution(int[][] sizes) {
        int maxWidth = 0, maxHeight = 0;
        for (int[] size:sizes) {
            // 모든 명함을 가로로 눞혀서 보관하는 것이 보관 크기를 줄일 수 있는 방법이다.
            //따라서 명함의 두 변의 길이 중 긴 쪽을 가로로, 짧은 쪽을 세로로 잡는다.
            int width = Math.max(size[0], size[1]);
            int height = Math.min(size[0], size[1]);
            maxWidth = Math.max(maxWidth, width);
            maxHeight = Math.max(maxHeight, height);
        } //size loop
        return maxWidth * maxHeight;        
    }
}