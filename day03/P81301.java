class Solution {
    public int solution(String s) {
        StringBuffer sb = new StringBuffer(s);
        String[] numbers = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String[] numberi = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        
        for (int i=0; i<numbers.length; i++) {
            //sb 내에서 문자열 "zero", "one", "two" ... 등이 들어있는 위치
            //이 위치가 -1일 경우 sb 내에서 해당 문자열은 포함되지 않는 것이다.
            int idx = -1;
            while ((idx=sb.indexOf(numbers[i]))>=0) {
                sb.replace(idx, idx+numbers[i].length(), numberi[i]);
            }
        } //i loop
        
        int answer = Integer.parseInt(sb.toString());
        return answer;
    }
}