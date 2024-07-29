import java.util.*;

class Solution {
    boolean solution(String s) {
        char[] arr = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char ch:arr) {
            if (ch=='(') { //여는 괄호
                stack.add('(');
            } else { //닫는 괄호
                if (stack.isEmpty()) return false;
                stack.pop();
            }
        } //ch loop
        
        //마지막에 닫히지 않는 괄호가 있으면 false를 반환해야 한다.
        return stack.isEmpty();
    }
}