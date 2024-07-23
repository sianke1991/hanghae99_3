class Solution {

    /**
     * 두 양의 정수를 입력받아 두 수의 최대공약수를 반환한다.
     */
    private int gcd(int num0, int num1) {
        int max, min;
        if (num0>num1) {
            max = num0;
            min = num1;
        } else {
            max = num1;
            min = num0;
        }

        //min 값은 0이 아니라고 가정-
        //min 값이 0이라면 아래 문장은 예외를 던진다.
        int remainder = max%min;
        while (remainder!=0) {
            max = min;
            min = remainder;
            remainder = max%min;
        }

        return min;
    }

    /**
     * 양의 정수로 구성된 배열을 입력받아 수 들의 최대공약수를 반환한다.
     */
    private int gcd(int... nums) {
        switch (nums.length) {
            case 0: return 0;
            case 1: return nums[0];
            case 2: return gcd(nums[0], nums[1]);
        }
        int result = gcd(nums[0], nums[1]);
        for (int i=2; i<nums.length; i++)
            result = gcd(result, nums[i]);
        
        return result;
    }

    public int solution(int[] arrayA, int[] arrayB) {
        int gcdA = gcd(arrayA);
        int resultA = gcdA;
        //만약 arrayB 내부에 gcdA의 배수가 있다면 resultA는 그 즉시 0이 된다.
        for (int elem:arrayB) {
            if (elem%gcdA==0) {
                resultA = 0;
                break;
            }
        } //elem loop

        int gcdB = gcd(arrayB);
        int resultB = gcdB;
        //만약 arrayA 내부에 gcdB의 배수가 있다면 resultB는 그 즉시 0이 된다.
        for (int elem:arrayA) {
            if (elem%gcdB==0) {
                resultB = 0;
                break;
            }
        } //elem loop

        return Math.max(resultA, resultB);
    }
}