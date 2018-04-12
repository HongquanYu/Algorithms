package hashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FindAllAnagramsinaString_438 {
	
	/** Sliding window solution! */
	
	public List<Integer> findAnagrams(String s, String p) {
		List<Integer> list = new ArrayList<>();
		
		if (s == null || s.length() == 0 || p == null || p.length() == 0)
			return list;
		
		int[] freq = new int[26];
		for (char c : p.toCharArray())	// freq 保存pattern里面字符的个数，供匹配。freq值 > 0 表示此字符在pattern中！
			freq[c - 'a']++;
		
		int l = 0, r = 0;					// window的左右boundary
		int toMatch = p.length();			// 还需要匹配多少个字符才能得到一个解
		
		while (r < s.length()) {	
			// 重点1: 需要从右边加入元素以供搜索解
			if (freq[s.charAt(r++) - 'a']-- > 0)	// freq中还有字符来匹配当前字符。 右边界进入一个字符
				toMatch--;

			if (toMatch == 0)			// 匹配到了所有的字符，目前window里面是一个解，那么加入起始地址
				list.add(l);
			
			// 重点2: 需要从左边移走元素以供求下一个解
			if (r - l == p.length()) 	// window向前移，移走的元素得加上来，
				if (freq[s.charAt(l++) - 'a']++ >= 0)		// 移出去的字符在p里，所以我们需要再匹配
					toMatch++;
		}
		
		return list;
	}
	
	/** TLE solution */
	
    public List<Integer> findAnagrams1(String s, String p) {
        int n1 = s.length(), n2 = p.length();
        List<Integer> list = new LinkedList<>();
        
        if (s == null || p == null || n1 == 0 || n2 == 0)
            return list;
        
        for (int i = 0; i <= n1 - n2; ++i) {
            String t = s.substring(i, i + n2);
            if (isAnagram(t, p))
                list.add(i);
        }
        
        return list;
    }
    
    private boolean isAnagram(String s, String p) {
        if (s.length() != p.length())
            return false;
        if (s.equals(p))
            return true;
        
        Map<Character, Integer> map = new HashMap<>();
        
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        for (int i = 0; i < p.length(); ++i) {
            char c = p.charAt(i);
            if (!map.containsKey(c))
                return false;
            map.put(c, map.get(c) - 1);
        }
        
        for (int i : map.values()) {
            if (i != 0)
                return false;
        }
        return true;
    }
    
/*    public static void main(String[] args) {
		String s = "cbaebabacd";
		String p = "abc";
		
		System.out.println(findAnagrams(s, p).toString());
	}*/
}
