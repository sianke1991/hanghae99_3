class Solution {
    /**
     * 입력 받은 문자열을 입력 받은 크기 만큼 나눈 결과를 반환한다.
     */
    private static String[] split(String s, int splitLength) {
        String[] result = new String[(s.length()+splitLength-1)/splitLength];
        for (int i=0; i<result.length-1; i++) {
            result[i] = s.substring(splitLength*i, splitLength*(i+1));
        } //i loop
        result[result.length-1] = s.substring(splitLength*(result.length-1));
        return result;
    }
    
    /**
     * 입력 받은 문자열을 입력 받은 단위 크기로 잘라서 압축한 결과를 반환한다.
     */
    private static String zip(String s, int nodeSize) {
        StringBuilder result = new StringBuilder();
        String[] substrings = split(s, nodeSize);
        String currentNode = substrings[0];
        int counter = 1;
        for (int i=1; i<substrings.length; i++) {
            if (!substrings[i].equals(currentNode)) {
                if (counter>1) result.append(counter);
                result.append(currentNode);
                currentNode = substrings[i];
                counter = 1;
                continue;
            }
            counter++;
        } //i loop
        
        if (counter>1) result.append(counter);
        result.append(currentNode);
        return result.toString();
    }
    
    public int solution(String s) {
        int result = s.length();
        final int lim = result/2;
        for (int i=1; i<=lim; i++) {
            result = Math.min(result, zip(s, i).length());
        }
        return result;
    }
}