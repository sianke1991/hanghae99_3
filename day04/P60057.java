import java.util.*;
/*
입력받은 문자열을 길이 1로, 길이 2로... (문자열길이/2) 길이로 나누어 압축을 시도하고
그 결과의 길이 중 가장 짧은 것을 반환한다.
압축 시 문자열을 일정한 길이로 나누고 그 결과 (부분 문자열)들을 Queue에 넣는다.
Queue에서 부분 문자열을 뽑아가면서
1. 직전에 뽑은 문자열과 같으면 카운터를 1 증가시키고,
2. 그렇지 않으면 카운터와 문자열을 기입하고 카운터를 초기화한다.
 */

class Solution {
    /**
     * 입력 받은 문자열을 입력 받은 단위 크기로 잘라서 압축한 결과를 반환한다.
     */
    private static String zip(String str, int nodeSize) {
        StringBuilder result = new StringBuilder();
        //일정한 크기로 나눈 부분문자열을 담은 Queue
        Queue<String> q = new ArrayDeque<>();
        //str을 nodeSize로 나누고 그 마디를 순서대로 Queue에 넣는다.
        {
            final int lim = str.length()/nodeSize*nodeSize;
            for (int i=0; i<lim; i+=nodeSize) {
                q.add(str.substring(i, i+nodeSize));
            } //i loop
            if (lim<str.length()) { //일정한 간격으로 문자열을 나누고 나머지 부분을 별도로 Queue에 넣는다.
                q.add(str.substring(lim));
            }
        }
        
        String currentNode = null;
        int counter = 0; //currentNode가 반복된 횟수
        
        while (!q.isEmpty()) {
            if (currentNode==null) {
                currentNode = q.poll();
                counter = 1;
                continue;
            }
            if (!q.peek().equals(currentNode)) {
                if (counter>1) result.append(counter);
                result.append(currentNode);
                currentNode = null;
                continue;
            }
            q.poll();
            counter++;
        } //while loop
        
        //while loop를 빠져나온 시점에서는 언제나 currentNode!=null 이다.
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