package hashTable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class KeyboardRow_500 {
	public String[] findWords(String[] words) {
	    return Stream.of(words).filter(s -> s.toLowerCase().matches("[qwertyuiop]*|[asdfghjkl]*|[zxcvbnm]*")).toArray(String[]::new);
	}
	
	public String[] findWords1(String[] words) {
		String[] strs = {"QWERTYUIOP", "ASDFGHJKL", "ZXCVBNM"};
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < strs.length; i++)
			for (char c : strs[i].toCharArray())
				map.put(c, i);				// 键盘字符 - 字符在键盘上的排树
		
		List<String> res = new LinkedList<>();
		
		for (String w : words) {
			if (w.equals(""))
				continue;
			int index = map.get(w.toUpperCase().charAt(0));		// 单词第一个字母出现在第几排
			for (char c : w.toUpperCase().toCharArray()) {		// 单词所有字符是否出现在同一排
				if (map.get(c) != index) {
					index = -1; // don't need a boolean flag.
					break;
				}
			}
			if (index != -1)		// if index != -1, this is a valid string
				res.add(w);
		}
		
		return res.toArray(new String[0]);	// 返回找到的
	}
}
