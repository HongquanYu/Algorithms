package hashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FindDuplicateFileinSystem_609 {
	public List<List<String>> findDuplicate(String[] paths) {

		HashMap<String, List<String>> map = new HashMap<>();		// 存储文件内容 和 文件链表

		for (String path : paths) {
			String[] values = path.split(" ");

			for (int i = 1; i < values.length; i++) {		// 遍历所有的文件，name_cont[0] 是路径
				
				String[] nameContent = values[i].split("\\(");
				nameContent[1] = nameContent[1].replace(")", "");		// nameContent[1]获取文件内容
				
				List<String> list = map.getOrDefault(nameContent[1], new ArrayList<String>());
				list.add(values[0] + "/" + nameContent[0]);	// 拼接路径 和 文件名
				map.put(nameContent[1], list);		// 文件内容 和 文件名链表入hashmap
			}
		}

		List<List<String>> res = new ArrayList<>();

		for (String key : map.keySet())
			if (map.get(key).size() > 1)
				res.add(map.get(key));

		return res;
	}
}
