package bloomberg.designLFU;

import java.util.HashMap;
import java.util.LinkedHashSet;

/** @author: Hongquan Yu
 *  @date: Feb 26, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */

/** 思想是：
 * 1，存key - value 记录只需要一个 HashMap 就可以搞定，但是需要追踪其使用频次，那么还需要别的数据结构：
 * 	- keyFreq  记录当前键值对的频度，以提供常数级访问
 *  - freqKeys 记录在当前频次上有多少个键值对，只有最低频度只有1个键值对的时候，要查询到当前建制对，就更新最小频次
 * 2，每一次查询 get 的时候，若存在记录，则需要更新其频次到高一个频次，若是当前频次是最低频次并且没有了其他元素在当前频次上，则自增最低频次
 * 3，每一次放入 put 的时候，
 * 	- 若存在记录，则覆盖原纪录，并做一次查询 get（以修改追踪其频度）
 *  - 若不存在记录，
 *  		- 检查当前空间是否满了，满了就移除最低频次的那个元素（这里不需要更新最小频度，新添加进去的自然成为最小频度），然后再添加进去
 *  		- 没有满，直接添加，并且设其为最小频度 */

public class LFUCache {
	
	HashMap<Integer, Integer> keyValue;					// 存储 key - value 键值对
	HashMap<Integer, Integer> keyFreq;					// 存储 key 的引用次数
	HashMap<Integer, LinkedHashSet<Integer>> freqKeys;	// 存储 频次 Freq 和 当前频次的 keys
	int cap;				// LFU 的容量
	int leastFreq = -1;	// 当前列表中最低频度，当满了需要删除的时候用，初始化为-1，当一旦插入记录进来就直接设为1
	
	public LFUCache(int capacity) {
		cap 			= capacity;
		keyValue 	= new HashMap<>();
		keyFreq 		= new HashMap<>();
		freqKeys 	= new HashMap<>();
		freqKeys.put(1, new LinkedHashSet<>());
	}
	
	/** 查询一个key, 并更新其查询频次列表 */
	
	public int get(int key) {
		if (!keyValue.containsKey(key))		// 不存在当前pair，直接返回
			return -1;
		
		int count = keyFreq.get(key);
		keyFreq.put(key, count + 1);
		
		// 移除当前频次 freq 上的记录 key，添加到 freq + 1 上的 key list
		freqKeys.get(count).remove(key);	
		
		// 查询的 key 是频度最低的key，并且没有其他 key 是当前频度
		if (count == leastFreq && freqKeys.get(count).size() == 0)	
			leastFreq++;
		
		if (!freqKeys.containsKey(count + 1))
			freqKeys.put(count + 1, new LinkedHashSet<>());
		
		freqKeys.get(count + 1).add(key);
		
		return keyValue.get(key);
	}
	
	/** 更新一个key - value pair， */
	
	public void put(int key, int value) {
		if (cap <= 0) 	return;
		
		// 存在key，更新其值，并修改其频次列表（通过调用 get(key) 方法）
		if (keyValue.containsKey(key)) {
			keyValue.put(key, value);
			get(key);
			return;
		}
		
		// 若果不存在并且没有room，那么移除最小频次的那个key - value pair
		if (keyValue.size() >= cap) {
			int evit = freqKeys.get(leastFreq).iterator().next();	// 取得一个 key
			freqKeys.get(leastFreq).remove(evit);	// 移除当前 key
			keyValue.remove(evit);			// 移除当前节点
		}
		
		// 添加当前 key - value pair，并且初始化当前key的频次为1，当前频次为1的列表添加至记录
		keyValue.put(key, value);
		keyFreq.put(key, 1);
		leastFreq = 1;		// 新插入的记录总是最低频次
		freqKeys.get(1).add(key);
	}
}