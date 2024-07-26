import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Set<String> phoneSet = new HashSet<>();
        //전화번호를 전부 HashSet에 넣는다.
        for (String elem:phone_book) phoneSet.add(elem);
        //각 전화번호의 부분문자열이 HashSet에 들어있는지 여부를 조사한다.
        for (String elem:phone_book) {
            final int lim = elem.length();
            for (int i=1; i<lim; i++) {
                if (phoneSet.contains(elem.substring(0, i)))
                    return false;
            } //i loop
        } //elem loop
        
        return true;
    }
}