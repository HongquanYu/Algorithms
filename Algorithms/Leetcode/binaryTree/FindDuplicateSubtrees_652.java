package binaryTree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/** @author: Hongquan Yu
 *  @date: Mar 5, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class FindDuplicateSubtrees_652 {
	
	/** Post-Order solution
	 * 对树进行 post order traversal，然后对每一个子树进行 serialized 成 string，再用 HashMap 存其出现次数
	 * 若是当前子树在HashMap 出现不止一次，就把当前节点加到结果里。 */
	
	public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
		List<TreeNode> res = new LinkedList<>();
		postorder(root, new HashMap<>(), res);
		
		return res;
	}

	public String postorder(TreeNode cur, Map<String, Integer> map, List<TreeNode> res) {
		if (cur == null)
			return "#";
		String serial = cur.val + "," + postorder(cur.left, map, res) + "," + postorder(cur.right, map, res);
		if (map.getOrDefault(serial, 0) == 1)
			res.add(cur);
		map.put(serial, map.getOrDefault(serial, 0) + 1);
		
		return serial;
	}
}
