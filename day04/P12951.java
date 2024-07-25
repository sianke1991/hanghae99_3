class Solution {
    public String solution(String s) {
        char[] result = s.toCharArray();
        //문자열의 첫 문자가 소문자일 경우 대문자로 변경
        {
            //s의 길이는 1 이상이므로 initial 문자가 반드시 있다.
            char initial = result[0];
            if (Character.isLowerCase(initial))
                result[0] = Character.toUpperCase(initial);
        }
        
        //(공백문자+소문자) 조합을 찾아서 해당 소문자를 대문자로 변경
        //(공백이 아닌 문자+대문자) 조합을 찾아서 해당 대문자를 소문자로 변경
        for (int i=1; i<result.length; i++) {
            char current = result[i];
            char previous = result[i-1];
            if (Character.isLowerCase(current) && Character.isWhitespace(previous))
                result[i] = Character.toUpperCase(current);
            else if (Character.isUpperCase(current) && !Character.isWhitespace(previous))
                result[i] = Character.toLowerCase(current);
        }
        
        return new String(result);
    }
}