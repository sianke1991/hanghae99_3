import java.util.*;

public class Solution {
    public int[] solution(int[] arr) {
        Stack<Integer> s = new Stack<>();
        s.add(arr[0]);
        for (int elem:arr) {
            if (s.peek()==elem) continue;
            s.add(elem);
        }
        
        int[] result = new int[s.size()];
        for (int i=0; i<result.length; i++)
            result[i] = s.get(i);
        
        return result;
    }
}