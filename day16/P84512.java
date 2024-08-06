import java.util.*;
class Solution {
		//모음만으로 구성된 문자열을 그 인덱스와 보관하는 Map
    private Map<String, Integer> vowelDictionary = new HashMap<>();
    private char[] vowels = {'A', 'E', 'I', 'O', 'U'};
    
    private void recursive(String word) {
		    //해당 문자열의 인덱스는, 그 시점에서 Map 객체의 size와 같다.
        vowelDictionary.put(word, vowelDictionary.size());
        if (word.length()>=5) //문자열의 길이가 5에 도달한 경우 자식 노드로 가지 않는다.
            return;
        for (char vowel:vowels)
            recursive(new StringBuilder(word).append(vowel).toString());
    }
    
    public int solution(String word) {
        if (vowelDictionary.isEmpty())
            recursive("");
        return vowelDictionary.get(word);
    }
}