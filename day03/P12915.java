class Solution {
    public String[] solution(String[] strings, int n) {
        String[] answer = new String[strings.length];
        System.arraycopy(strings, 0, answer, 0, strings.length);
        java.util.Arrays.sort(answer, (a, b) -> {
            int cmpr = Character.compare(a.charAt(n), b.charAt(n));
            if (cmpr!=0) return cmpr;
            else return a.compareTo(b);
        });
        return answer;
    }
}