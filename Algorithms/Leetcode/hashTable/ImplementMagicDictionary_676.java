package hashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImplementMagicDictionary_676 {
	
	/*
	 * 1. For each word in dict, for each char, remove the char and put the rest of the word as key,
	 * a pair of index of the removed char and the char as part of value list into a map. e.g.
	 * "hello" -> {"ello":[[0, 'h']], "hllo":[[1, 'e']], "helo":[[2, 'l'],[3, 'l']], "hell":[[4,
	 * 'o']]}
	 * 
	 * 2. During search, generate the keys as in step 1. When we see there's pair of same index but
	 * different char in the value array, we know the answer is true. e.g. "healo" when remove a,
	 * key is "helo" and there is a pair [2, 'l'] which has same index but different char. Then the
	 * answer is true;
	 */
	
	
	Map<String, List<int[]>> map;		// 构建一个删掉字符的key 和 在哪里删掉那个字符的list

	/** Initialize your data structure here. */
	public ImplementMagicDictionary_676() {
		this.map = new HashMap<>();
	}

	/** Build a dictionary through a list of words */
	public void buildDict(String[] dict) {
		for (String s : dict) {
			for (int i = 0; i < s.length(); i++) {
				String key = s.substring(0, i) + s.substring(i + 1);
				int[] pair = new int[] {i, s.charAt(i)};

				List<int[]> val = map.getOrDefault(key, new ArrayList<int[]>());
				val.add(pair);

				map.put(key, val);
			}
		}
	}

	/**
	 * Returns if there is any word in the trie that equals to the given word after modifying
	 * exactly one character
	 */
	public boolean search(String word) {
		for (int i = 0; i < word.length(); i++) {
			String key = word.substring(0, i) + word.substring(i + 1);
			if (map.containsKey(key)) {
				for (int[] pair : map.get(key)) {
					if (pair[0] == i && pair[1] != word.charAt(i))
						return true;
				}
			}
		}
		return false;
	}
	
	
	/** 另外一个 相似的解法, 每个位置用通配符 */
	/*
    Map<String, Character> map;  
    public MagicDictionary() {
        map = new HashMap<>();
    } */
	Map<String, Character> indexToChar = new HashMap<>(); 	// 含通配符的字符串 和 删掉字符 / 通配符(当存在两个以上不一样的字符)的mapping
	
    public void buildDict1(String[] dict) {
        for (String d : dict) {
        	
            StringBuilder sb = new StringBuilder(d);
            
            for (int i = 0; i < sb.length(); i++) { 
                sb.setCharAt(i, '*');
                indexToChar.put(sb.toString(), indexToChar.containsKey(sb.toString())? '*' : d.charAt(i));
                sb.setCharAt(i, d.charAt(i));
            }     
        }
    }  
    public boolean search1(String word) {
        StringBuilder sb = new StringBuilder(word);
        for (int i = 0; i < sb.length(); i++) {
            sb.setCharAt(i, '*');
            if (indexToChar.containsKey(sb.toString()) && word.charAt(i) != indexToChar.get(sb.toString()))	// 存在且不相等
            		return true;
            sb.setCharAt(i, word.charAt(i));		// 替换掉的字母装回去
        }
        
        return false;
    }
    
    /** Trie Solution */
    
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord;
        public TrieNode() {}
    }
    TrieNode root = new TrieNode();
    /** Initialize your data structure here. */
/*    public MagicDictionary() {
        root = new TrieNode();
    }*/
    
    /** Build a dictionary through a list of words */
    public void buildDict2(String[] dict) {
        for (String s : dict) {
            TrieNode node = root;
            for (char c : s.toCharArray()) {
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new TrieNode();
                }
                node = node.children[c - 'a'];
            }
            node.isWord = true;
        }
    }
    
    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search2(String word) {
        
    		// 遍历每个位置，然后用其他的25个字母来替换一次搜索，搜索到了一次就返回真
        for (int i = 0; i < word.length(); i++) {	
        		StringBuilder sb = new StringBuilder(word);
            for (char c = 'a'; c <= 'z'; c++) {		
                if (word.charAt(i) == c)
                    continue;
                sb.setCharAt(i, c);
                if (contains(sb.toString(), root))
                    return true;
                sb.setCharAt(i, word.charAt(i));
            }
        }
        
        return false;
    }
    
    private boolean contains(String s, TrieNode root) {
        TrieNode node = root;
        for (char c : s.toCharArray()) {
            if (node.children[c - 'a'] == null)
                return false;
            
            node = node.children[c - 'a'];
        }
        
        return node.isWord;
    }
    
    /* 另一个 Trie Solution */
    
    private class Trie1 {
    		Trie1[] next = new Trie1[26];
        boolean isWord = false;
    }
    
    private Trie1 root1 = new Trie1();
    /** Initialize your data structure here. */
    
    /** Build a dictionary through a list of words */
	public void buildDict4(String[] dict) {
		for (String word : dict) {
			Trie1 walk = root1;
			for (char c : word.toCharArray()) {
				int i = c - 'a';
				if (walk.next[i] == null)
					walk.next[i] = new Trie1();
				walk = walk.next[i];
			}
			walk.isWord = true;
		}
	}
    
    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
	public boolean search4(String word) {
		return search5(root1, word.toCharArray(), 0, 0);
	}

	private boolean search5(Trie1 root, char[] word, int start, int changed) {
		if (start == word.length)
			return root.isWord && changed == 1;
		
		int index = word[start] - 'a';
		
		for (int i = 0; i < 26; i++) {
			if (root.next[i] == null)
				continue;
			if (i == index) {
				if (search5(root.next[i], word, start + 1, changed))
					return true;
			} else {
				if (changed == 0 && search5(root.next[i], word, start + 1, changed + 1))
					return true;
			}
		}
		
		return false;
	}
}
