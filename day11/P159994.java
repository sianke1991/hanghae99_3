/*
각 단어 카드 더미를 큐로 표현하여
현재 필요로 하는 단어를 큐에서 뽑을 수 없다면 곧바로 "No"를,
모든 단어를 큐에서 뽑을 수 있다면 "Yes"를 반환한다.
*/

import java.util.*;
class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
	    //카드 더미 1을 표현하는 큐
        //아래와 같이 작성하여 Queue 내부의 원소를 초기화 할 수 있다.
        //Queue<String> q1 = new ArrayDeque<>(Arrays.asList(cards1));
        Queue<String> q1 = new ArrayDeque<>();
        for (var elem:cards1) q1.add(elem);
        //카드 더미 2를 표현하는 큐
        Queue<String> q2 = new ArrayDeque<>();
        for (var elem:cards2) q2.add(elem);
        //목표로 하는 단어 배열의 각 단어에 대해-
        for (var word:goal) {
		        //해당 단어를 카드 더미 1에서 빼올 수 있는 경우-
            if (word.equals(q1.peek()))
                q1.poll();
            //해당 단어를 카드 더미 1이 아닌 카드 더미 2에서 빼올 수 있는 경우-
            else if (word.equals(q2.peek()))
                q2.poll();
            //해당 단어를 두 카드 더미에서 빼올 수 없는 경우-
            else
                return "No";
        } //word loop
        
        //목표로 하는 단어 배열의 모든 단어를 두 카드 더미에서 빼올 수 있는 경우-
        return "Yes";
    }
}