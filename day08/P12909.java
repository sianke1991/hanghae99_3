class Solution {
    boolean solution(String s) {
        // 여는 괄호에 의해 들어간 깊이
        int depth = 0;
        byte[] arr = s.getBytes();
        for (byte b:arr) {
            switch (b) {
                case '(':
                    depth++;
                    break;
                case ')':
                    depth--;
                    break;
            } //switch-case
            if (depth<0)
                return false;
        } //b loop
        return depth==0;
    }
}