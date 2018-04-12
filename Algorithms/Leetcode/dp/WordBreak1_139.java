package dp;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordBreak1_139 {
	
	/** Editorial solution #1 Brute Force [Time Limit Exceeded]
	 * 
	 * The naive approach to solve this problem is to use recursion and
	 * backtracking. For finding the solution, we check every possible prefix of
	 * that string in the dictionary of words, if it is found in the dictionary,
	 * then the recursive function is called for the remaining portion of that
	 * string. And, if in some function call it is found that the complete
	 * string is in dictionary, then it will return true. */
	
	public boolean wordBreak1(String s, List<String> wordDict) {
		return word_Break(s, new HashSet(wordDict), 0);
	}

	public boolean word_Break(String s, Set<String> wordDict, int start) {
		if (start == s.length())
			return true;
		
		for (int end = start + 1; end <= s.length(); end++) {
			if (wordDict.contains(s.substring(start, end)) && word_Break(s, wordDict, end)) {
				return true;
			}
		}
		return false;
	}
	
	/** Editorial solution #2 Recursion with memoization [Accepted]
	 * 
	 * In the previous approach we can see that many subproblems were redundant,
	 * i.e we were calling the recursive function multiple times for a
	 * particular string. To avoid this we can use memoization method, where an
	 * array memo is used to store the result of the subproblems. Now, when
	 * the function is called again for a particular string, value will be
	 * fetched and returned using the memo array, if its value has been
	 * already evaluated.
	 * 
	 * With memoization many redundant subproblems are avoided and recursion
	 * tree is pruned and thus it reduces the time complexity by a large factor. */
	
	public boolean wordBreak2(String s, List<String> wordDict) {
		return word_Break(s, new HashSet(wordDict), 0, new Boolean[s.length()]);
	}

	public boolean word_Break(String s, Set<String> wordDict, int start, Boolean[] memo) {
		if (start == s.length()) {
			return true;
		}
		if (memo[start] != null) {
			return memo[start];
		}
		for (int end = start + 1; end <= s.length(); end++) {
			if (wordDict.contains(s.substring(start, end)) && word_Break(s, wordDict, end, memo)) {
				return memo[start] = true;
			}
		}
		return memo[start] = false;
	}
	
	/** Editorial solution #3 Using Breadth-First-Search [Accepted]
	 * 
	 * Another approach is to use Breadth-First-Search. Visualize the string as
	 * a tree where each node represents the prefix upto index endend. Two nodes
	 * are connected only if the substring between the indices linked with those
	 * nodes is also a valid string which is present in the dictionary. In order
	 * to form such a tree, we start with the first character of the given
	 * string (say ss) which acts as the root of the tree being formed and find
	 * every possible substring starting with that character which is a part of
	 * the dictionary. Further, the ending index (say ii) of every such
	 * substring is pushed at the back of a queue which will be used for Breadth
	 * First Search. Now, we pop an element out from the front of the queue and
	 * perform the same process considering the string s(i+1,end)s(i+1,end) to
	 * be the original string and the popped node as the root of the tree this
	 * time. This process is continued, for all the nodes appended in the queue
	 * during the course of the process. If we are able to obtain the last
	 * element of the given string as a node (leaf) of the tree, this implies
	 * that the given string can be partitioned into substrings which are all a
	 * part of the given dictionary. */
	
	public boolean wordBreak3(String s, List<String> wordDict) {
		
		Set<String> wordDictSet = new HashSet(wordDict);
		Queue<Integer> queue = new LinkedList<>();
		int[] visited = new int[s.length()];
		
		queue.add(0);
		
		while (!queue.isEmpty()) {
			
			int start = queue.remove();
			if (visited[start] == 0) {
				for (int end = start + 1; end <= s.length(); end++) {
					if (wordDictSet.contains(s.substring(start, end))) {
						queue.add(end);
						if (end == s.length()) {
							return true;
						}
					}
				}
				visited[start] = 1;
			}
		}
		
		return false;
	}
	
	/** Editorial solution #4 Using Dynamic Programming [Accepted] */
	
	public boolean wordBreak4(String s, List<String> wordDict) {
		int N = s.length();
		Set<String> dictSet = new HashSet<>(wordDict);
		boolean[] dp = new boolean[N + 1];
		dp[0] = true;
		
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < i; j++) {
				if (dp[j] && dictSet.contains(s.substring(j, i))) {
					dp[i] = true;
					break;
				}
			}
		}
		return dp[s.length()];
	}
	
	
	/** dp[i] repdpents substring [0:1] can be segmented
	 *  dp[i] = dp[i-t] && wordDict.contains(s[i-t, i])
	 */
	
	public boolean wordBreak5(String s, List<String> wordDict) {
		
		boolean[] dp = new boolean[s.length() + 1];
		int maxWordLength = 0;
		
		dp[0] = true;

		for (String item : wordDict)
			maxWordLength = Math.max(maxWordLength, item.length());

		for (int i = 1; i <= s.length(); ++i) {
			for (int j = (i > maxWordLength) ? i - maxWordLength : 0; j < i; ++j)
				if (dp[j] && wordDict.contains(s.substring(j, i))) {
					dp[i] = true;
					break;
				}
		}

		return dp[s.length()];
	}
}
