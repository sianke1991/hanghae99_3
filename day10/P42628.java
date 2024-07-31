/*
입력 받은 값 중 최대치 조회 및 제거, 최소치 조회 및 제거를
수행하기 위해 값을 MultiSet에 보관하는 방법을 사용한다.
*/

import java.util.*;
class Solution {
    /**
     * 이중 우선순위 큐
     */
    private static class PPQ {
		/**
         * 이중 우선순위 큐에 들어있는 원소(key)와, 
         * 각 원소의 개수(value)를 담은 TreeMap
         */
        private TreeMap<Integer, Integer> map = new TreeMap<>();
        
		/**이중 우선순위 큐에 연산을 적용한다.*/
        public void operate(String operation) {
            switch (operation) {
				//최대치 제거
                case "D 1": {
                    if (map.isEmpty())
                        break;
                    int maxKey = map.lastKey();
                    int value = map.get(maxKey);
                    if (value<=1) map.remove(maxKey);
                    else map.put(maxKey, value-1);
                    break;
                }
				//최소치 제거
                case "D -1": {
                    if (map.isEmpty())
                        break;
                    int minKey = map.firstKey();
                    int value = map.get(minKey);
                    if (value<=1) map.remove(minKey);
                    else map.put(minKey, value-1);
                    break;
                }
				//원소 삽입
                default: {
                    int key = Integer.parseInt(operation.substring(2));
                    Integer value = map.get(key);
                    if (value==null) map.put(key, 1);
                    else map.put(key, value+1);
                    break;
                }
            } //switch
        }
        
        /**이중 우선순위 큐에 있는 값 중 최대치를 조회한다.
            이중 우선순위 큐가 비어있으면 0을 반환한다.*/
        public int maxKey() {
            return map.isEmpty() ? 0 : map.lastKey();
        }
        
        /**이중 우선순위 큐에 있는 값 중 최소치를 조회한다.
            이중 우선순위 큐가 비어있으면 0을 반환한다.*/
        public int minKey() {
            return map.isEmpty() ? 0 : map.firstKey();
        }
    }
    
    public int[] solution(String[] operations) {
        PPQ ppq = new PPQ();
        for (String operation:operations) 
            ppq.operate(operation);
        int[] answer = {ppq.maxKey(), ppq.minKey()};
        return answer;
    }
}