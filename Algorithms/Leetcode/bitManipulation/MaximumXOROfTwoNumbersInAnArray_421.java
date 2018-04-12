package bitManipulation;

import java.util.HashSet;
import java.util.Set;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class MaximumXOROfTwoNumbersInAnArray_421 {
	
	/* Observation: If we are going to find two numbers whose XOR result be biggest! Then the final result must
	 * be two numbers whose bit-set are very different. So in order to find that, we can start from MSB to shrink
	 * our search area by selecting numbers whose bit-set differ, and then we can shrink further in next bit iteration
	 * 
	 * The general idea is:
	 * We build the result from setting bit one by one started from MSB. 
	 * 
	 * that we check every bit of all numbers in array from MSB.
	 * Use a set to record all the unique prefixes of numbers, 
	 */
	
	public int findMaximumXOR(int[] nums) {
		int max = 0, mask = 0;
		
		// Traverse every bit of a number, -32
		for (int i = 31; i >= 0; i--) {
			
			mask |= (1 << i);		// mask will set one more bit to '1' from MSB in every iteration
			Set<Integer> set = new HashSet<>();
			
			for (int num : nums)			// Stored prefixes of possible result in the array
				set.add(num & mask);
			
			int tmp = max | (1 << i);	// turn current bit on - potential max
			
			/* All the candidates in the set is a potential one for solution. 
			 * What we are going to do is in every iteration, if there's two numbers diff in current bit
			 * then we can set current bit on in final result. */
			
			// tmp ^ prefix: 
			
			for (int prefix : set) {
				if (set.contains(tmp ^ prefix)) {	
					max = tmp;
					break;
				}
			}
		}
		
		return max;
	}
	
	/* 2-way Trie Solution */
	
	private class Trie {
		private Trie[] next;

		public Trie() {
			next = new Trie[2];
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
				
				if (curNode.next[curBit] == null) {
					curNode.next[curBit] = new Trie();
				}
				
				curNode = curNode.next[curBit];
			}
		}
		
		int max = Integer.MIN_VALUE;
		
		for (int num : nums) {
			
			Trie curNode = root;
			int curSum = 0;
			
			for (int i = 31; i >= 0; i--) {
				
				int curBit = (num >>> i) & 1;
				
				if (curNode.next[curBit ^ 1] != null) {	// check if another is 0
					curSum += (1 << i);
					curNode = curNode.next[curBit ^ 1];	// move to child
				} else {
					curNode = curNode.next[curBit];		// No sibling, go down directly
				}
			}
			
			max = Math.max(curSum, max);
		}
		
		return max;
	}
}
