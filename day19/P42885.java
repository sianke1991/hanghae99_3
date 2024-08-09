/*
    구명보트에 두 사람을 탑승시킬 때
    최대한 가벼운 사람과 무거운 사람을 매칭시켜
    구명보트의 남은 무게를 최소화시키는 것이
    구명보트의 필요 수량을 줄이는 방법이다.
    => 구명보트 하나에 탑승시킬 최대한 가벼운 사람과 무거운 사람을 찾기 위애 투 포인터 방법을 사용한다.
*/
class Solution {
    public int solution(int[] people, int limit) {
		    //step a. 배열을 정렬한다.
        java.util.Arrays.sort(people);
        //leftIndex, rightIndex: 투-포인터 방법에 사용할 두 인덱스
        //numPairs: 한 구명보트에 탑승시킬 수 있는 인원 쌍의 수
        int leftIndex = 0, rightIndex = people.length-1, numPairs = 0;
        //투 포인터 적용
        while (leftIndex<rightIndex) {
		        //가리키고 있는 두 사람을 한 구명보트에 탑승시킬 수 없는 경우-
            if (people[leftIndex]+people[rightIndex]>limit) {
                rightIndex--;
                continue;
            }
            numPairs++;
            leftIndex++;
            rightIndex--;
        } //while loop
        return people.length-numPairs;
    }
}