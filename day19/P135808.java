/*
좋은 품질의 사과와 나쁜 품질의 사과를 묶으면 (묶여 있는) 전체 사과가 나쁜 품질의 가격을 따라간다.
따라서 좋은 품질의 사과끼리 묶어야 (좋은 품질의 사과를 낮은 가격에 팔아서 생기는) 이익 감소를 줄일 수 있다.
좋은 품질의 사과부터 순서대로 m 개씩 묶어서 판매한다.
 */
class Solution {
    public int solution(int k, int m, int[] score) {
        java.util.Arrays.sort(score);
        int result = 0;
        for (int i=score.length-m; i>=0; i-=m)
            result += score[i];
        return result*m;
    }
}