package bitmanipulation;

import java.util.HashSet;
import java.util.Set;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class MaximumXOROfTwoNumbersInAnArray_421 {
	public int findMaximumXOR(int[] nums) {
		int max = 0, mask = 0;
		for (int i = 31; i >= 0; i--) {
			mask = mask | (1 << i);
			Set<Integer> set = new HashSet<>();
			for (int num : nums) {
				set.add(num & mask);
			}
			int tmp = max | (1 << i);
			for (int prefix : set) {
				if (set.contains(tmp ^ prefix)) {
					max = tmp;
					break;
				}
			}
		}
		
		return max;
	}
	
	/* Trie Solution */
	
	class Trie {
		Trie[] children;

		public Trie() {
			children = new Trie[2];
		}
	}

	public int findMaximumXOR2(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		// Init Trie.
		Trie root = new Trie();
		for (int num : nums) {
			Trie curNode = root;
			for (int i = 31; i >= 0; i--) {
				int curBit = (num >>> i) & 1;
				if (curNode.children[curBit] == null) {
					curNode.children[curBit] = new Trie();
				}
				curNode = curNode.children[curBit];
			}
		}
		int max = Integer.MIN_VALUE;
		for (int num : nums) {
			Trie curNode = root;
			int curSum = 0;
			for (int i = 31; i >= 0; i--) {
				int curBit = (num >>> i) & 1;
				if (curNode.children[curBit ^ 1] != null) {
					curSum += (1 << i);
					curNode = curNode.children[curBit ^ 1];
				} else {
					curNode = curNode.children[curBit];
				}
			}
			max = Math.max(curSum, max);
		}
		return max;
	}
}
