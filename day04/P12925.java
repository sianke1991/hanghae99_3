class Solution {
    public int solution(String s) {
        //입력 받은 문자열이 음수를 가리키는지 여부
        boolean isNegative = false;
        int result = 0;
        char[] arr = s.toCharArray();
        //첫 번째 문자는 부호일 수도 있으므로 별도로 처리한다.
        switch (arr[0]) {
            case '+': break;
            case '-': isNegative = true; break;
            default: result = arr[0]-'0'; break;
        }
        for (int i=1; i<arr.length; i++) {
            result = result*10+(arr[i]-'0');
        }
        if (isNegative) return -result;
        else return result;
    }
}