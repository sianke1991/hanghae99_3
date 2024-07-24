class Solution {
    public String[] solution(String[] strings, int n) {
        StringBuilder[] tmp = new StringBuilder[strings.length];
        for (int i=0; i<strings.length; i++) {
            //각 문자열에 n 번째 문자를 앞에 붙인 새로운 문자열을 구성하여
            //새로 구성한 문자열의 배열을 만든다.
            tmp[i] = new StringBuilder().append(strings[i].charAt(n)).append(strings[i]);
        } //i loop
        java.util.Arrays.sort(tmp);
        String[] answer = new String[tmp.length];
        for (int i=0; i<tmp.length; i++) {
            //배열 정렬 후 각 원소의 첫 번째 문자를 제거하여
            //본래 문자열로 원복시킨다.
            answer[i] = tmp[i].substring(1).toString();
        } //i loop
        return answer;
    }
}