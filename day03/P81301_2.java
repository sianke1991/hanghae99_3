class Solution {
    public int solution(String s) {
        int result = 0, index = 0;
        while (index<s.length()) {
            switch (s.charAt(index)) {
                case '0': result = result*10; index++; continue;
                case '1': result = result*10+1; index++; continue;
                case '2': result = result*10+2; index++; continue;
                case '3': result = result*10+3; index++; continue;
                case '4': result = result*10+4; index++; continue;
                case '5': result = result*10+5; index++; continue;
                case '6': result = result*10+6; index++; continue;
                case '7': result = result*10+7; index++; continue;
                case '8': result = result*10+8; index++; continue;
                case '9': result = result*10+9; index++; continue;
            }
            switch (s.substring(index, index+2)) {
                case "ze": result = result*10; index+=4; continue;
                case "on": result = result*10+1; index+=3; continue;
                case "tw": result = result*10+2; index+=3; continue;
                case "th": result = result*10+3; index+=5; continue;
                case "fo": result = result*10+4; index+=4; continue;
                case "fi": result = result*10+5; index+=4; continue;
                case "si": result = result*10+6; index+=3; continue;
                case "se": result = result*10+7; index+=5; continue;
                case "ei": result = result*10+8; index+=5; continue;
                case "ni": result = result*10+9; index+=4; continue;
            }
        } //while loop

        return result;
    }
}
