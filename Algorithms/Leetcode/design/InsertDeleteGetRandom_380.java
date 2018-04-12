package design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class InsertDeleteGetRandom_380 {
	ArrayList<Integer> nums;
	HashMap<Integer, Integer> map;
	Random rand = new Random();

	/** Initialize your data structure here. */
	
	public InsertDeleteGetRandom_380() {
		nums = new ArrayList<Integer>();
		map = new HashMap<Integer, Integer>();	// 数字 和 在数组里的索引
	}

	/**
	 * Inserts a value to the set. Returns true if the 
	 * set did not already contain the specified element. */
	
	public boolean insert(int val) {
		if (map.containsKey(val))
			return false;
		
		map.put(val, nums.size());	// 存当前值和在数组的index
		nums.add(val);
		
		return true;
	}

	/** Removes a value from the set. Returns true if the
	 *  set contained the specified element. */
	
	public boolean remove(int val) {
		if (!map.containsKey(val))
			return false;
		
		int index = map.get(val);	// 要移除元素的索引
		
		if (index < nums.size() - 1) { 		// 不在列表最后一个，和最后一个交换
			int lastItem = nums.get(nums.size() - 1);	// 最后一个元素
			nums.set(index, lastItem);		// 数组最后一个元素交换到index
			map.put(lastItem, index);		// 更新map里最后一个元素的索引
		}
		
		map.remove(val);		// hash 表移除当前元素
		nums.remove(nums.size() - 1);	// 数组里移除最后一个元素
		
		return true;
	}

	/** Get a random element from the set. */
	public int getRandom() {
		return nums.get(rand.nextInt(nums.size()));
	}
	
	/** Follow Up:
	 * 要是getRandom变成getRandom(k)怎么整？
	 * 直接用一个计数器或者list来装这k个不重复数，装满了返回 */
}
