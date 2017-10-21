package hashtable;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class RearrangeStringkDistanceApart_358 {
	
	/*
	 * This is a greedy problem. Every time we want to find the best candidate: which is the
	 * character with the largest remaining count. Thus we will be having two arrays. One count
	 * array to store the remaining count of every character. Another array to keep track of the
	 * most left position that one character can appear. We will iterated through these two array to
	 * find the best candidate for every position. Since the array is fixed size, it will take
	 * constant time to do this. After we find the candidate, we update two arrays.
	 */
	
	public String rearrangeString(String str, int k) {
		int length = str.length();
		int[] count = new int[26];
		int[] valid = new int[26];
		for (int i = 0; i < length; i++) {
			count[str.charAt(i) - 'a']++;
		}
		StringBuilder sb = new StringBuilder();
		for (int index = 0; index < length; index++) {
			int candidatePos = findValidMax(count, valid, index);
			if (candidatePos == -1)
				return "";
			count[candidatePos]--;
			valid[candidatePos] = index + k;
			sb.append((char) ('a' + candidatePos));
		}
		return sb.toString();
	}

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
	
	/*
	 * The greedy algorithm is that in each step, select the char with highest remaining count if
	 * possible (if it is not in the waiting queue). PQ is used to achieve the greedy. A regular
	 * queue waitQueue is used to "freeze" previous appeared char in the period of k.
	 * 
	 * In each iteration, we need to add current char to the waitQueue and also release the char at
	 * front of the queue, put back to maxHeap. The "impossible" case happens when the maxHeap is
	 * empty but there is still some char in the waitQueue.
	 */
	
	public String rearrangeString1(String str, int k) {

		StringBuilder rearranged = new StringBuilder();
		// count frequency of each char
		Map<Character, Integer> map = new HashMap<>();
		for (char c : str.toCharArray()) {
			if (!map.containsKey(c)) {
				map.put(c, 0);
			}
			map.put(c, map.get(c) + 1);
		}

		// construct a max heap using self-defined comparator, which holds all Map entries, Java is
		// quite verbose
		Queue<Map.Entry<Character, Integer>> maxHeap =
				new PriorityQueue<>(new Comparator<Map.Entry<Character, Integer>>() {
					public int compare(Map.Entry<Character, Integer> entry1,
							Map.Entry<Character, Integer> entry2) {
						return entry2.getValue() - entry1.getValue();
					}
				});

		Queue<Map.Entry<Character, Integer>> waitQueue = new LinkedList<>();
		maxHeap.addAll(map.entrySet());

		while (!maxHeap.isEmpty()) {

			Map.Entry<Character, Integer> current = maxHeap.poll();
			rearranged.append(current.getKey());
			current.setValue(current.getValue() - 1);
			waitQueue.offer(current);

			if (waitQueue.size() < k) { // intial k-1 chars, waitQueue not full yet
				continue;
			}
			// release from waitQueue if char is already k apart
			Map.Entry<Character, Integer> front = waitQueue.poll();
			// note that char with 0 count still needs to be placed in waitQueue as a place holder
			if (front.getValue() > 0) {
				maxHeap.offer(front);
			}
		}

		return rearranged.length() == str.length() ? rearranged.toString() : "";
	}
}
