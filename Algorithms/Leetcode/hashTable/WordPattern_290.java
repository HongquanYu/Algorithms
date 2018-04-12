package hashTable;

import java.util.HashMap;

public class WordPattern_290 {
	public boolean wordPattern(String pattern, String str) {
		String[] words = str.split(" ");
		if (words.length != pattern.length())
			return false;
		
		HashMap<Object, Integer> index = new HashMap<>();
		
		for (Integer i = 0; i < words.length; ++i)
			if (index.put(pattern.charAt(i), i) != index.put(words[i], i))
				return false;
		
		return true;
	}
	
	public static boolean wordPattern1(String pattern, String str) {
		String[] arr = str.split(" ");
		HashMap<Character, String> map = new HashMap<Character, String>();
		
		if (arr.length != pattern.length())
			return false;
		
		for (int i = 0; i < arr.length; i++) {
			
			char c = pattern.charAt(i);
			
			if (map.containsKey(c)) {
				if (!map.get(c).equals(arr[i]))
					return false;
			} else {
				if (map.containsValue(arr[i]))
					return false;
				map.put(c, arr[i]);
			}
		}
		
		return true;
	}
	
	public static void main(String [] args) {
		String p = "abba";
		String string = "dog cat cat dog";
		
		System.out.print(wordPattern1(p, string));
	}
}
