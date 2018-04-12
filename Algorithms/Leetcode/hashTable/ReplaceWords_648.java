package hashTable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReplaceWords_648 {
	
	/* Trie */
	
	public String replaceWords(List<String> dict, String sentence) {
		String[] tokens = sentence.split(" ");
		TrieNode trie = buildTrie(dict);
		return replaceWords(tokens, trie);
	}

	private String replaceWords(String[] tokens, TrieNode root) {
		
		StringBuilder stringBuilder = new StringBuilder();
		
		for (String token : tokens) {
			stringBuilder.append(getShortestReplacement(token, root));
			stringBuilder.append(" ");
		}
		return stringBuilder.substring(0, stringBuilder.length() - 1);	// 去掉最后加的那个空格
	}

	private String getShortestReplacement(String token, final TrieNode root) {
		
		TrieNode temp = root;
		StringBuilder stringBuilder = new StringBuilder();
		
		for (char c : token.toCharArray()) {		// 遍历整个word里面的字母
			stringBuilder.append(c);
			if (temp.children[c - 'a'] != null) {		// 找到一个存在的dict了，就返回他
				if (temp.children[c - 'a'].isWord)
					return stringBuilder.toString();
				
				temp = temp.children[c - 'a'];			// 指针下移
			} else {			// 没找到就直接返回原单词
				return token;
			}
		}
		
		return token;
	}
	
	// 将dict里面的单词都给送到trie里面去
	private TrieNode buildTrie(List<String> dict) {
		
		TrieNode root = new TrieNode(' ');
		
		for (String word : dict) {
			
			TrieNode temp = root;
			
			for (char c : word.toCharArray()) {
				if (temp.children[c - 'a'] == null) {		// 插入一个字符
					temp.children[c - 'a'] = new TrieNode(c);
				}
				temp = temp.children[c - 'a'];			// 指针下移
			}
			
			temp.isWord = true;		// 完成插入一个word，标记其为一个word
		}
		
		return root;
	}

	public class TrieNode {
		char val;
		TrieNode[] children;
		boolean isWord;

		public TrieNode(char val) {
			this.val = val;
			this.children = new TrieNode[26];
			this.isWord = false;
		}
	}
	
	/* Hashset */
	
	public String replaceWords1(List<String> dict, String sentence) {
		if (dict == null || dict.size() == 0)
			return sentence;

		Set<String> set = new HashSet<>();
		for (String s : dict)
			set.add(s);

		StringBuilder sb = new StringBuilder();
		String[] words = sentence.split("\\s+");

		for (String word : words) {
			String prefix = "";
			for (int i = 1; i <= word.length(); i++) {
				prefix = word.substring(0, i);
				if (set.contains(prefix))
					break;
			}
			sb.append(" " + prefix);
		}

		return sb.deleteCharAt(0).toString();
	}
	
	/**  */
	
    public String replaceWords2(List<String> dict, String sentence) {
        Map<String, String> map = new HashMap<>();
        String res = "";
        
        for (String s : sentence.split("\\s+")) {
            for (String t : dict) {
                if (s.indexOf(t) == 0) {
                		String temp = map.getOrDefault(s, "");
                    if (temp == "" || temp.length()  > t.length())
                        map.put(s, t);
                }
            }
        }
        for (String s : sentence.split(" ")) {
            if (map.containsKey(s))
                res = res + (" " + map.get(s));
            else
                res = res + (" " + s);
        }
        return res.trim();
    }
    
    public static void main(String[] args) {
		List<String> list = new LinkedList<>();
		list.add("a");
		list.add("b");
		list.add("c");
//		List<String> list = new LinkedList<>();
//		list.add("cat");
//		list.add("rat");
//		list.add("bat");
		String tmp = "aadsfasf absbs bbab cadsfafs";
		ReplaceWords_648 r = new ReplaceWords_648();
		
		System.out.println(r.replaceWords2(list, tmp));
	}
}
