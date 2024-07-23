class Solution {
    private int elementAt(long index, int n) {
        return (int)(1+Math.max(index/n, index%n));
    }
    
    public int[] solution(int n, long left, long right) {
        int[] result = new int[(int)(right-left+1)];
        for (int i=0; i<result.length; i++)
            result[i] = elementAt(left+i, n);
        return result;
    }
}