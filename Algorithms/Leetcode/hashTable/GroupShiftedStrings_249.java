package hashTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupShiftedStrings_249 {
	public static List<List<String>> groupStrings(String[] strings) {
		
		List<List<String>> result = new ArrayList<List<String>>();
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		
		for (String str : strings) {
			
			int offset = str.charAt(0) - 'a';		// 单词第一个字母和a的距离,使得只要是shifted words，最后都会得到同一个key
			System.out.println("offset: " + offset);
			String key = "";
			
			for (int i = 0; i < str.length(); i++) {		// 对每个单词，找到它相对应的key
				char c = (char) (str.charAt(i) - offset);
				if (c < 'a') 	c += 26;
				key += c;
				System.out.println("tem Key: " + key);
			}
			
			System.out.println("Word: " + str + ",	Key: " + key);
			
			if (!map.containsKey(key)) {
				List<String> list = new ArrayList<String>();
				map.put(key, list);
			}
			
			map.get(key).add(str);
		}
		
		for (String key : map.keySet()) {
			List<String> list = map.get(key);
			Collections.sort(list);
			result.add(list);
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		String[] string = new String[5];
		string[0] = "acd";
		string[1] = "dfg";
		string[2] = "wyz";
		string[3] = "bdfh";
		string[4] = "moqs";
		
		System.out.println(groupStrings(string));
	}
}
