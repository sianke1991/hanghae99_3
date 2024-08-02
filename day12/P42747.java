import java.util.*;
class Solution {
    public int solution(int[] citations) {
        //step a. 배열 citations를 내림차순 정렬한다.
        Arrays.sort(citations);
        for (int i=0, lim=citations.length/2; i<lim; i++) {
            int j = citations.length-1-i;
            int tmp = citations[i];
            citations[i] = citations[j];
            citations[j] = tmp;
        } //i loop
        
        //step b. H-index 값을 찾기 위한 탐색
        //H-index의 값으로 0은 탐색할 필요가 없다. (leftIndex의 시작값을 1로 둔 이유)
        int leftIndex = 1, rightIndex = citations.length, midIndex = 0;
        int result = 0;
        while (leftIndex<=rightIndex) {
            midIndex = (leftIndex+rightIndex)/2;
            if (citations[midIndex-1]>=midIndex) {
                //midIndex는 H-index 값 후보가 된다.
                result = midIndex;
                leftIndex = midIndex+1;
            } else {
                //midIndex는 H-index 값 후보가 되지 못한다.
                rightIndex = midIndex-1;
            }
        } //while loop
        
        return result;
    }
}