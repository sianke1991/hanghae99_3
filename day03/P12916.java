class Solution {
    boolean solution(String s) {
        char[] arr = s.toCharArray();
        int counter = 0;
        for (char ch:arr) {
            switch (ch) {
                case 'p': case 'P': counter++; break;
                case 'y': case 'Y': counter--; break;
            }
        } //ch loop
        
        return counter==0;
    }
}