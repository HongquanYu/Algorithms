package hashTable;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class LongestSubstringwithAtMostKDistinctCharacters_340 {
	
	/* sliding window */
	
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		
		int[] chars = new int[256];					// 
		int num = 0, l = 0, len = 0;
		
		for (int r = 0; r < s.length(); r++) {
			if (chars[s.charAt(r)]++ == 0)			// 右边进入的是一个新元素， chars[i] == 0表示之前没出现过
				num++;
			if (num > k) {							// 现在已经超过了允许的k个元素了，要开始从左边减去
				while (--chars[s.charAt(l++)] > 0) ;
				num--;
			}
			
			len = Math.max(len, r - l + 1);				// 更新最大长度
		}
		
		return len;
	}
	
	/* sliding window 2 */
	
	public int lengthOfLongestSubstringKDistinct2(String s, int k) {
	    if(k==0 || s==null || s.length()==0)
	        return 0;
	 
	    if(s.length()<k)
	        return s.length();
	 
	    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
	 
	    int maxLen=k;
	    int left=0;
	    for(int i=0; i<s.length(); i++){
	        char c = s.charAt(i);
	        if(map.containsKey(c)){
	            map.put(c, map.get(c)+1);
	        }else{
	            map.put(c, 1);
	        }
	 
	        if(map.size()>k){
	            maxLen=Math.max(maxLen, i-left);
	 
	            while(map.size()>k){
	 
	                char fc = s.charAt(left);
	                if(map.get(fc)==1){
	                    map.remove(fc);
	                }else{
	                    map.put(fc, map.get(fc)-1);
	                }
	 
	                left++;
	            }
	        }
	 
	    }
	 
	    maxLen = Math.max(maxLen, s.length()-left);
	 
	    return maxLen;
	}
	
	public int lengthOfLongestSubstringKDistinct1(String str, int k) {
		if (str == null || str.isEmpty() || k == 0)
			return 0;
		
		TreeMap<Integer, Character> lastOccurrence = new TreeMap<>();
		Map<Character, Integer> inWindow = new HashMap<>();
		int j = 0;
		int max = 1;
		for (int i = 0; i < str.length(); i++) {
			char in = str.charAt(i);
			while (inWindow.size() == k && !inWindow.containsKey(in)) {
				int first = lastOccurrence.firstKey();
				char out = lastOccurrence.get(first);
				inWindow.remove(out);
				lastOccurrence.remove(first);
				j = first + 1;
			}
			// update or add in's position in both maps
			if (inWindow.containsKey(in)) {
				lastOccurrence.remove(inWindow.get(in));
			}
			inWindow.put(in, i);
			lastOccurrence.put(i, in);
			max = Math.max(max, i - j + 1);
		}
		return max;
	}
}
