package hashTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/** @author: Hongquan Yu
 *  @date: Jan 26, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class TopKFrequentWords_692 {
	
	/* The idea is to keep a count of each word in a HashMap and then insert in a Priority Queue.
	 * While inserting in pq, if the count of two words is same then insert based on string compare of the keys. */
	
	public List<String> topKFrequent(String[] words, int k) {

		List<String> result = new LinkedList<>();
		Map<String, Integer> map = new HashMap<>();
		
		for (int i = 0; i < words.length; i++) 
			map.put(words[i], map.getOrDefault(words[i], 0) + 1);		// FREQUENCY of each word
		
		// 优先队列先根据频率来排序，要是相同的string就根据字典序
		PriorityQueue<Map.Entry<String, Integer>> pq = 
				new PriorityQueue<>( (a, b) -> a.getValue() == b.getValue() ? b.getKey().compareTo(a.getKey()) : a.getValue() - b.getValue());

		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			pq.offer(entry);
			if (pq.size() > k)
				pq.poll();
		}

		while (!pq.isEmpty())
			result.add(0, pq.poll().getKey());

		return result;
	}
	
	/* Traditional Solution */
	
	public List<String> topKFrequent1(String[] words, int k) {
		HashMap<String, Integer> map = new HashMap<>();
		int max = 0;
		for (String w : words) {
			map.put(w, map.getOrDefault(w, 0) + 1);
			max = Math.max(max, map.get(w));
		}
		
		List<String>[] bucket = new ArrayList[max + 1];
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			int fre = entry.getValue();
			if (bucket[fre] == null) {
				bucket[fre] = new ArrayList<>();
			}
			bucket[fre].add(entry.getKey());
		}
		
		List<String> res = new ArrayList<>();
		for (int i = max; i >= 0 && res.size() < k; i--) {
			if (bucket[i] != null) {
				Collections.sort(bucket[i]);
				res.addAll(bucket[i]);
			}
		}
		
		return res.subList(0, k);
	}
	
	/* Trie Solution */
	public List<String> topKFrequent2(String[] words, int k) {
		// calculate frequency of each word
		Map<String, Integer> freqMap = new HashMap<>();
		for (String word : words)
			freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
		
		// build the buckets
		TrieNode[] count = new TrieNode[words.length + 1];
		for (String word : freqMap.keySet()) {
			int freq = freqMap.get(word);
			if (count[freq] == null) {
				count[freq] = new TrieNode();
			}
			addWord(count[freq], word);
		}
		// get k frequent words
		List<String> list = new LinkedList<>();
		for (int f = count.length - 1; f >= 1 && list.size() < k; f--) {
			if (count[f] == null)
				continue;
			getWords(count[f], list, k);
		}
		return list;
	}

	private void getWords(TrieNode node, List<String> list, int k) {
		if (node == null)
			return;
		if (node.word != null) {
			list.add(node.word);
		}
		if (list.size() == k)
			return;
		for (int i = 0; i < 26; i++) {
			if (node.next[i] != null) {
				getWords(node.next[i], list, k);
			}
		}
	}

	private boolean addWord(TrieNode root, String word) {
		TrieNode curr = root;
		for (char c : word.toCharArray()) {
			if (curr.next[c - 'a'] == null) {
				curr.next[c - 'a'] = new TrieNode();
			}
			curr = curr.next[c - 'a'];
		}
		curr.word = word;
		return true;
	}

	class TrieNode {
		TrieNode[] next;
		String word;

		TrieNode() {
			this.next = new TrieNode[26];
			this.word = null;
		}
	}
}
