import java.util.*;
class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> clothCounter = new HashMap<>();
        //step a. 각 의상 종류에 해당하는 의상 개수를 헤아린다.
        for (String[] cloth:clothes) {
            final String type = cloth[1];
            clothCounter.merge(type, 1, (a, b)->a+1);
        } //cloth loop
        
        //step b. 각 의상 종류에 해당하는 의상 개수에 1을 더한 값을 곱한다.
        int result = 1;
        for (Map.Entry<String, Integer> entry:clothCounter.entrySet()) {
            result*=(entry.getValue()+1);
        }
        
        //step c. 곱한 결과에 1을 뺀 값을 반환한다.
        return result-1;
    }
}