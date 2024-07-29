/*
queue1 = [3, 2, 7, 2]
queue2 = [4, 6, 5, 1]
각 queue에 대해 부분합 배열을 만든다. 이 때 queue2의 원소가 queue1 뒤에 올 수 있고, queue1의 원소가 queue2 뒤에 올 수 있으므로
queue1의 부분합 배열 sum1은 [3, 2, 7, 2, 4, 6, 5, 1]의 부분합 배열을,
queue2의 부분합 배열 sum2는 [4, 6, 5, 1, 3, 2, 7, 2]의 부분합 배열을
만든다.
sum1 = [0, 3, 5, 12, 14, 18, 24, 29, 30]
sum2 = [0, 4, 10, 15, 16, 19, 21, 28, 30]
이 부분합 배열을 통해 다음을 알 수 있다.
queue1의 합은 sum1[queue1.length] = sum1[4] = 14
queue2의 합은 sum2[queue2.length] = sum2[4] = 16
[queue1의 합]과 [queue2의 합]은 2 차이가 난다. (=> 차이 2 값의 절반은 1) 따라서 queue1과 queue2간에 원소를 교환할 때
queue1이 1 만큼 덜 교환하면 (다시 말해 queue2가 1 만큼 더 교환하면) 두 큐의 합을 일치시킬 수 있다.

sum1 = [0, 3, 5, 12, 14, 18, 24, 29, 30]인데, 각 원소에 대해
sum1 에서 0을 뽑았으면 sum2 에서 1을 탐색한다 
    => sum2 에는 1이 없으니 pass
sum1 에서 3을 뽑았으면 sum2 에서 4를 탐색한다
    => sum1에서 3의 인덱스는 1, sum2에서 4의 인덱스는 1 => queue1 에서 원소 1개를 주고, queue2에서 원소 1개를 주면 두 큐의 합을 일치시킬 수 있다.
sum1 에서 5를 뽑았으면 sum2 에서 6을 탐색한다
    => sum2 에는 6이 없으니 pass
...
두 큐의 합을 일치시키는 모든 경우를 찾아서 필요한 작업 횟수의 최소치를 구한다.
sum2는 순증가하는 배열이므로 sum2에서 특정 원소를 찾기 위해 이진 탐색을 사용할 수 있다.

또한 위의 예시에서는 [queue1의 합]과 [queue2의 합]의 차이가 짝수이므로 논의를 계속할 수 있지만
[queue1의 합]과 [queue2의 합]의 차이가 홀수라면 두 큐의 합을 일치시킬 수 없다. 따라서 후자의 경우
더 이상의 논의 없이 -1을 반환하면 된다.
 */

import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        //부분합 배열 구하기
        long[] sum1 = null;
        long[] sum2 = null;
        {
            int len = (queue1.length<<1)+1;
            sum1 = new long[len];
            sum2 = new long[len];
        }
        for (int i=0; i<queue1.length; i++) {
            sum1[i+1] = sum1[i]+queue1[i];
            sum2[i+1] = sum2[i]+queue2[i];
        }
        for (int i=0; i<queue1.length; i++) {
            int idx = queue1.length+i;
            sum1[idx+1] = sum1[idx]+queue2[i];
            sum2[idx+1] = sum2[idx]+queue1[i];
        }
        
        //이진 탐색을 통해 두 큐의 합을 일치시키는 움직임 횟수 찾기 (시간복잡도 NlbN으로 탐색)
        long diff = (sum1[queue1.length]-sum2[queue2.length]);
        if ((diff&1)==1) //if the diff is odd
            return -1;
        diff >>= 1;
        int result = Integer.MAX_VALUE;
        for (int idx1=0; idx1<sum1.length; idx1++) {
            long elem1 = sum1[idx1];
            long elem2 = elem1-diff;
            int idx2 = Arrays.binarySearch(sum2, elem2);
            if (idx2<0) continue;
            result = Math.min(result, idx1+idx2);
        } //idx1 loop
        
        if (result==Integer.MAX_VALUE) return -1;
        else return result;
    }
}