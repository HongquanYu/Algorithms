package hashTable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class RearrangeStringkDistanceApart_358 {
	
	/* This is a greedy problem. Every time we want to find the best candidate: 
	 * which is the character with the largest remaining count. Thus we will be having two arrays. 
	 * 
	 * - count array: to store the remaining count of every character. 
	 * - valid array: to keep track of the most left position that one character can appear. 
	 * 
	 * We will iterated through these two array to find the best candidate for every position. 
	 * Since the array is fixed size, it will take constant time to do this. 
	 * After we find the candidate, we update two arrays. */
	
	public String rearrangeString(String str, int k) {
		int n = str.length();
		int[] count = new int[26];
		int[] valid = new int[26];
		
		for (int i = 0; i < n; i++)		// 计数每一个字母的次数
			count[str.charAt(i) - 'a']++;
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < n; i++) {
			int candidatePos = findValidMax(count, valid, i);
			if (candidatePos == -1)
				return "";
			count[candidatePos]--;
			valid[candidatePos] = i + k;
			sb.append((char) ('a' + candidatePos));
		}
		
		return sb.toString();
	}
	
	// 从给定的index的前面找，找到还剩最多次数那一个
	private int findValidMax(int[] count, int[] valid, int index) {
		int max = Integer.MIN_VALUE;
		int candidatePos = -1;
		for (int i = 0; i < count.length; i++) {
			if (count[i] > 0 && count[i] > max && index >= valid[i]) {
				max = count[i];
				candidatePos = i;
			}
		}
		
		return candidatePos;
	}
	
	/** The greedy algorithm is that in each step, select the char with highest remaining count if
	 * possible (if it is not in the waiting queue). PQ is used to achieve the greedy. A regular
	 * queue waitQueue is used to "freeze" previous appeared char in the period of k.
	 * 
	 * In each iteration, we need to add current char to the waitQueue and also release the char at
	 * front of the queue, put back to maxHeap. The "impossible" case happens when the maxHeap is
	 * empty but there is still some char in the waitQueue. */
	
	public String rearrangeString1(String str, int k) {
		StringBuilder sb = new StringBuilder();
		Map<Character, Integer> map = new HashMap<>();
		for (char c : str.toCharArray()) 
			map.put(c, map.getOrDefault(c, 0) + 1);

		Queue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(
				(a, b) -> a.getValue() != b.getValue() ? a.getValue() - b.getValue() : a.getKey() - b.getKey());

		Queue<Map.Entry<Character, Integer>> waitQueue = new LinkedList<>();
		pq.addAll(map.entrySet());

		while (!pq.isEmpty()) {
			Map.Entry<Character, Integer> poped = pq.poll();
			sb.append(poped.getKey());
			poped.setValue(poped.getValue() - 1);
			waitQueue.offer(poped);

			if (waitQueue.size() < k) 	// intial k-1 chars, waitQueue not full yet
				continue;
			
			Map.Entry<Character, Integer> front = waitQueue.poll();
			if (front.getValue() > 0)
				pq.offer(front);
		}

		return sb.length() == str.length() ? sb.toString() : "";
	}
}
