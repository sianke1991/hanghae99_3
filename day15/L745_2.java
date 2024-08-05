/* Trie 사용한 풀이 */

class TrieNode {
    int index;
    TrieNode[] children = new TrieNode[27];
}

class Trie {
    TrieNode root = new TrieNode();
    
    //트라이에 문자열 하나를 넣는다.
    private void add(String str, int index) {
        char[] chs = str.toCharArray();
        TrieNode currentNode = root;
        for (char ch:chs) {
            if (currentNode.children[ch-'a']==null)
                currentNode.children[ch-'a'] = new TrieNode();
            currentNode = currentNode.children[ch-'a'];
            currentNode.index = index;
        } //ch loop
    }
    
    //접두사, 접미사 조회를 위해 트라이에 문자열"들"을 넣는다.
    public void insert(String str, int index) {
        String target = new StringBuilder(str).append('{').append(str).toString();
        for (int i=0,lim=str.length(); i<=lim; i++)
            add(target.substring(i), index);
    }
    
    //접두사, 접미사를 입력받아 이에 해당하는 문자열 인덱스를 반환한다.
    public int search(String pref, String suff) {
        char[] target = new StringBuilder(suff)
										        .append('{')
										        .append(pref)
										        .toString()
										        .toCharArray();
        TrieNode currentNode = root;
        int result = -1;
        for (char ch:target) {
            if (currentNode.children[ch-'a']==null)
                return -1;
            currentNode = currentNode.children[ch-'a'];
            result = currentNode.index;
        } //ch loop
        
        return result;
    }
}

class WordFilter {
    private Trie trie;
    
    public WordFilter(String[] words) {
        trie = new Trie();
        for (int i=0; i<words.length; i++)
            trie.insert(words[i], i);
    }
    
    public int f(String pref, String suff) {
        return trie.search(pref, suff);
    }
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(pref,suff);
 */