package array;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.TreeSet;

/** @author: Hongquan Yu
 *  @date: Mar 12, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class SlidingWindowMedian_480 {
	
	/** TreeMap is used to implement an ordered MultiSet.
	 * 
	 * In this problem, I use two Ordered MultiSets as Heaps. One heap maintains
	 * the lowest 1/2 of the elements, and the other heap maintains the higher
	 * 1/2 of elements.
	 * 
	 * This implementation is faster than the usual implementation that uses 2
	 * PriorityQueues, because unlike PriorityQueue, TreeMap can remove
	 * arbitrary element in logarithmic time. */
	
	public double[] medianSlidingWindow(int[] nums, int k) {
		double[] res = new double[nums.length - k + 1];
		
		TreeMap<Integer, Integer> minHeap = new TreeMap<>();
		TreeMap<Integer, Integer> maxHeap = new TreeMap<>(Collections.reverseOrder());

		int minHeapCap = k / 2; 		// smaller heap when k is odd.

		for (int i = 0; i < k; i++)
			maxHeap.put(nums[i], maxHeap.getOrDefault(nums[i], 0) + 1);
		
		int[] minHeapSize = new int[]{ 0 };
		int[] maxHeapSize = new int[]{ k };
		
		for (int i = 0; i < minHeapCap; i++)
			move1Over(maxHeap, minHeap, maxHeapSize, minHeapSize);

		res[0] = getMedian(maxHeap, minHeap, maxHeapSize, minHeapSize);
		
		int resIdx = 1;
		for (int i = 0; i < nums.length - k; i++) {
			int addee = nums[i + k];
			
			if (addee <= maxHeap.keySet().iterator().next())
				add(addee, maxHeap, maxHeapSize);
			else
				add(addee, minHeap, minHeapSize);

			int removee = nums[i];
			if (removee <= maxHeap.keySet().iterator().next())
				remove(removee, maxHeap, maxHeapSize);
			else
				remove(removee, minHeap, minHeapSize);

			// rebalance
			if (minHeapSize[0] > minHeapCap)
				move1Over(minHeap, maxHeap, minHeapSize, maxHeapSize);
			else if (minHeapSize[0] < minHeapCap)
				move1Over(maxHeap, minHeap, maxHeapSize, minHeapSize);

			res[resIdx++] = getMedian(maxHeap, minHeap, maxHeapSize, minHeapSize);
		}
		
		return res;
	}
	
	/** Helper to get Median of current window */

	private double getMedian(TreeMap<Integer, Integer> larges, TreeMap<Integer, Integer> smalls, int[] largesSize, int[] smallsSize) {
		return largesSize[0] > smallsSize[0]
				? (double) larges.keySet().iterator().next()
				: ((double) larges.keySet().iterator().next() + (double) smalls.keySet().iterator().next()) / 2.0;
	}

	/** move the top element of heap1 to heap2 */
	
	private void move1Over(TreeMap<Integer, Integer> heap1, TreeMap<Integer, Integer> heap2, int[] heap1Size, int[] heap2Size) {
		int peek = heap1.keySet().iterator().next();
		
		add(peek, heap2, heap2Size);
		remove(peek, heap1, heap1Size);	// remove peeked element from current heap
	}
	
	/** Helper to put new value into heap */
	
	private void add(int val, TreeMap<Integer, Integer> heap, int[] heapSize) {
		heap.put(val, heap.getOrDefault(val, 0) + 1);
		heapSize[0]++;
	}

	/** Helper to remove spedific value from heap */
	
	private void remove(int val, TreeMap<Integer, Integer> heap, int[] heapSize) {
		if (heap.put(val, heap.get(val) - 1) == 1)	// Only 1 occurrence, remove it.
			heap.remove(val);
		
		heapSize[0]--;
	}
	
	/** Another solution with same Idea! 
	 * Same idea but using PriorityQueue. */
	
	public double[] medianSlidingWindow2(int[] nums, int k) {
		double[] result = new double[nums.length - k + 1];
		
		PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> right = new PriorityQueue<>();

		for (int i = 0; i < nums.length; i++) {
			if (left.size() <= right.size()) {
				right.add(nums[i]);
				left.add(right.remove());
			} else {
				left.add(nums[i]);
				right.add(left.remove());
			}

			if (left.size() + right.size() == k) {
				double median;
				if (left.size() == right.size())
					median = (double) ((long) left.peek() + (long) right.peek()) / 2;
				else
					median = (double) left.peek();

				int start = i - k + 1;
				result[start] = median;
				if (!left.remove(nums[start]))
					right.remove(nums[start]);
			}
		}
		
		return result;
	}
	
	/** A really quick solution */
	
	class Node implements Comparable<Node> {
		int id, val;
		Node(int i, int v) {
			id = i;
			val = v;
		}
		public int compareTo(Node n) {
			if (val == n.val) 		return id - n.id;
			else if (val < n.val) 	return -1;
			else 					return 1;
		}
	}

	TreeSet<Node> left = new TreeSet<Node>();
	TreeSet<Node> right = new TreeSet<Node>();
	public double[] medianSlidingWindow3(int[] nums, int k) {
		if (nums == null || nums.length < k)
			return new double[0];
		double[] ans = new double[nums.length - k + 1];
		for (int i = 0; i < nums.length; i++) {
			addNode(nums[i], i);
			if (i >= k - 1) {
				ans[i - k + 1] = getAns();
				remove(nums[i - k + 1], i - k + 1);
			}
		}
		return ans;
	}

	public double getAns() {
		if (left.size() == right.size())
			return ((double) left.last().val + right.first().val) / 2;
		if (left.size() > right.size())
			return left.last().val;
		return right.first().val;
	}

	public void addNode(int num, int index) {
		Node n = new Node(index, num);
		if (left.size() == 0 || n.compareTo(left.last()) <= 0)
			left.add(n);
		else
			right.add(n);
		if (left.size() - right.size() > 1)
			right.add(left.pollLast());
		else if (right.size() - left.size() > 1)
			left.add(right.pollFirst());
	}

	public void remove(int num, int index) {
		Node n = new Node(index, num);
		if (n.compareTo(left.last()) <= 0)
			left.remove(n);
		else
			right.remove(n);
		if (left.size() == 0 && right.size() != 0)
			left.add(right.pollFirst());
	}
}
