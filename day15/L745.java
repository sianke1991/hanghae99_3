/* TreeMap 사용한 풀이 */

import java.util.*;
class WordFilter {
    //문자열과 그 인덱스를 담은 TreeMap
    private TreeMap<String, Integer> words;
    //뒤집은 문자열과 그 인덱스를 담은 TreeMap
    private TreeMap<String, Integer> reversedWords;
    
    //입력받은 접두사에 대해 그 다음 문자열을 반환
    //"ab"를 입력 받으면 "ac"를 반환한다.
    private String nextPref(String pref) {
        char[] result = pref.toCharArray();
        result[result.length-1]++;
        return new String(result);
    }
    
    //입력받은 문자열을 뒤집어서 반환한다.
    private String reverseOf(String str) {
        char[] result = str.toCharArray();
        final int len = result.length;
        for (int i=0,lim=len/2; i<lim; i++) {
            int j = len-1-i;
            char tmp = result[i];
            result[i] = result[j];
            result[j] = tmp;
        } //i loop
        return new String(result);
    }
    
    public WordFilter(String[] words) {
        this.words = new TreeMap<>();
        this.reversedWords = new TreeMap<>();
        for (int i=0; i<words.length; i++) {
            this.words.put(words[i], i);
            this.reversedWords.put(reverseOf(words[i]), i);
        } //i loop
    }
    
    public int f(String pref, String suff) {
		    //pref로 시작하는 문자열의 인덱스 집합
        Set<Integer> prefSubSet = new HashSet<>(
	        this.words.subMap(pref, nextPref(pref)).values()
	      );
	      //suff로 끝나는 문자열의 인덱스 집합
        Set<Integer> suffSubSet = null;
        {
		        //suff-접미사-는 뒤집어서 조회해야 한다.
            String reversedSuff = reverseOf(suff);
            suffSubSet = new HashSet<>(
	            this.reversedWords.subMap(reversedSuff, nextPref(reversedSuff)).values()
	           );
        }
        
        int result = -1;
        //두 집합의 교집합을 찾아서 그 원소의 인덱스를 조회한다.
        for (int index:prefSubSet) {
            if (suffSubSet.contains(index)) {
                result = Math.max(result, index);
            }
        } //index loop
        
        return result;
    }
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(pref,suff);
 */