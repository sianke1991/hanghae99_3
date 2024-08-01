class Solution {
    public long solution(long n) {
        //step a. 정수 n에 들어있는 각 숫자의 등장 횟수 헤아리기
        int[] digitFreq = new int[10];
        while (n!=0) {
            digitFreq[(int)(n%10)]++;
            n/=10;
        } //while loop
        
        //step b. '9' 부터 '0' 순으로 정수 조립
        long result = 0;
        for (int digit=9; digit>=0; digit--) {
            for (int repeat=0; repeat<digitFreq[digit]; repeat++) {
                result*=10;
                result+=digit;
            }
        } //digit loop
        
        return result;
    }
}