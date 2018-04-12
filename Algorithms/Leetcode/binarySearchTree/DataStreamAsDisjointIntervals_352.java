package binarySearchTree;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import array.Interval;

/** @author: Hongquan Yu
 *  @date: Mar 27, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class DataStreamAsDisjointIntervals_352 {
	
	/** TreeMap 存的是 值 和 区间
	 * 这道题主要是弄清楚有几个 corner cases！对于每一个应该怎么处理！
	 * 1，进来的数和所有的区间八竿子打不着，直接新建一个新区间
	 * 2，新来的数和一个区间临近，可以合并其进入区间
	 * 3，新来的数在两个区间的缺失的中间，直接合并两区间 */
	
	private TreeMap<Integer, Interval> tree;

	public DataStreamAsDisjointIntervals_352() {
		tree = new TreeMap<>();
	}

	public void addNum(int val) {
		if (tree.containsKey(val))
			return;
		
		Integer l = tree.lowerKey(val);		// 较底的一个 key
		Integer h = tree.higherKey(val);		// 较高的一个 key
		
		if (l != null && h != null && tree.get(l).end + 1 == val && h == val + 1) {	// 正好连接两个区间 Merge
			tree.get(l).end = tree.get(h).end;
			tree.remove(h);
		} else if (l != null && tree.get(l).end + 1 >= val) {		// 扩展低的区间
			tree.get(l).end = Math.max(tree.get(l).end, val);
		} else if (h != null && h == val + 1) {					// 扩展高的区间
			tree.put(val, new Interval(val, tree.get(h).end));
			tree.remove(h);
		} else {		// 直接建立一个 长度为 1 的区间
			tree.put(val, new Interval(val, val));
		}
	}

	public List<Interval> getIntervals() {
		return new ArrayList<>(tree.values());
	}
}
