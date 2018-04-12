package bloomberg;

/** @author: Hongquan Yu
 *  @date: Feb 22, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
/*import junit.framework.Assert;
import org.junit.Test;*/

/** 维持一个 HashEntry 数组，
 * 每一个数组元素都是一个 HashEntry 节点，which is a 链表！
 * 插入元素：
 * 找到该节点，要是不能 */

public class HashMapImpl {

	private final int TABLE_SIZE = 128;
	private HashEntry[] table;
	
	// 给 HashMap 数组分配空间并全部初始化为 null
	public HashMapImpl() {
		table = new HashEntry[TABLE_SIZE];
		for (int i = 0; i < TABLE_SIZE; i++)
			table[i] = null;
	}

	public void put(int key, int value) {
		int index = hashCodeNew(key);
		
		System.out.println("Key: " + key + ", Index: " + index);
		
		HashEntry hashEntry = new HashEntry(key, value);
		
		if (table[index] == null) {		// 新加入一个 链表
			table[index] = hashEntry;
		} else {		// 更新老链表
			HashEntry entryRef = table[index];
			
			// 找看当前链表有没有和key相同的节点，有的话要覆盖旧值
			while (entryRef.next != null) {
				if (entryRef.key == hashEntry.key) {		// 当前 entry 链表的 key 和要插入的 key 相等，覆盖原来的值
					entryRef.value = hashEntry.value;
					break;
				} 
				entryRef = entryRef.next;
			}
			// 当前节点在此链表的末尾，要么覆盖末尾节点，要么插入下一个新节点
			if (entryRef.next == null) {
				if (entryRef.key == hashEntry.key)
					entryRef.value = hashEntry.value;
				else
					entryRef.next = hashEntry;
			}
		}

	}
	// 查询一个 entry！
	public int get(int key) {
		int index = hashCodeNew(key);
		
		if (table[index] != null) {
			HashEntry entryRef = table[index];
			if (entryRef.key == key)
				return entryRef.value;
			
			while (entryRef.next != null) {
				if (entryRef.key == key)
					return entryRef.value;
			}
		}
		
		return -1;
	}
	
	/** 此方法 取得 map 数组的索引 */
	public int hashCodeNew(int h) {
		h ^= (h >>> 20) ^ (h >>> 12);
		int hashCode = h ^ (h >>> 7) ^ (h >>> 4);
		return hashCode % TABLE_SIZE;
		
		/* int result = 31 * result + c */
	}

	private class HashEntry {
		int key, value;
		HashEntry next = null;

		HashEntry() { }

		public HashEntry(int key, int value) {
			this.key = key;
			this.value = value;
		}

		public int getKey() { return this.key; }
		public int getValue() { return this.value; }
	}
	
	public static void main(String[] args) {
		HashMapImpl h = new HashMapImpl();
		
		h.put(10, 20);
		h.put(20, 11);
		h.put(21, 1);
		h.put(20, 10);
		
		System.out.println(h.get(20));
	}

/*	@Test
	public void testBasic() {
		HashMap hashMap = new HashMap();
		hashMap.put(10, 20);
		hashMap.put(20, 11);
		hashMap.put(21, 1);
		hashMap.put(20, 10);

		int value = hashMap.get(20);
		Assert.assertEquals(10, value);
	}*/
}
