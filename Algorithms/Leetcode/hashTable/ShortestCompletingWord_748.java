package hashTable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/** @author: Hongquan Yu
 *  @date: Jan 28, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class ShortestCompletingWord_748 {
	public String shortestCompletingWord(String licensePlate, String[] words) {
		String target = licensePlate.toLowerCase();
		int[] charMap = new int[26];
		
		// Construct the character map
		for (int i = 0; i < target.length(); i++)
			if (Character.isLetter(target.charAt(i)))
				charMap[target.charAt(i) - 'a']++;
		
		int minLength = Integer.MAX_VALUE;
		String result = null;
		
		for (int i = 0; i < words.length; i++) {
			String word = words[i].toLowerCase();
			if (matches(word, charMap) && word.length() < minLength) {
				minLength = word.length();
				result = words[i];
			}
		}
		
		return result;
	}

	private boolean matches(String word, int[] charMap) {
		int[] targetMap = new int[26];
		
		for (int i = 0; i < word.length(); i++)
			if (Character.isLetter(word.charAt(i)))
				targetMap[word.charAt(i) - 'a']++;

		for (int i = 0; i < 26; i++)
			if (charMap[i] != 0 && targetMap[i] < charMap[i])
				return false;
		
		return true;
	}
	
	/* HashMap */
	
	public String shortestCompletingWord2(String licensePlate, String[] words) {
		String letters = licensePlate.replaceAll("[^A-Za-z]+", "").toLowerCase();		// remove spaces and numbers
		String res = null;
		System.out.println("Letter: " + letters);
		Map<Character, Integer> map = new HashMap<>();
		for (char c : letters.toCharArray()) 		// char - occurrence pair of licensePlate
			map.put(c, map.getOrDefault(c, 0) + 1);

		Arrays.sort(words, (s1, s2) -> s1.length() - s2.length());
		for (String word : words) {
			Map<Character, Integer> hash = new HashMap<>();
			res = word;
			
			for (char ch : word.toCharArray()) 		// char - occurrence pair of current word
				hash.put(ch, hash.getOrDefault(ch, 0) + 1);
			
			if (hash.size() < map.size())	continue;
			for (char c : map.keySet())
				if (!hash.containsKey(c) || hash.get(c) < map.get(c)) 
					res = null;
			if (res != null) 	break;
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		String pattern = "1s3 PSt";
		String [] list = new String[]{"step","steps","stripe","stepple"};
		
		ShortestCompletingWord_748 s = new ShortestCompletingWord_748();
		System.out.println(s.shortestCompletingWord2(pattern, list));
	}
}
