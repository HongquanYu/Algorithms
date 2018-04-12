package dp;

public class SentenceScreenFitting_418 {
	
	public int wordsTyping1(String[] sentence, int rows, int cols) {
		int[] nextIndex = new int[sentence.length];
		int[] times = new int[sentence.length];
		for (int i = 0; i < sentence.length; i++) {
			int curLen = 0;
			int cur = i;
			int time = 0;
			while (curLen + sentence[cur].length() <= cols) {
				curLen += sentence[cur++].length() + 1;
				if (cur == sentence.length) {
					cur = 0;
					time++;
				}
			}
			nextIndex[i] = cur;
			times[i] = time;
		}
		int ans = 0;
		int cur = 0;
		for (int i = 0; i < rows; i++) {
			ans += times[cur];
			cur = nextIndex[cur];
		}
		return ans;
	}
	
	public int wordsTyping(String[] sentence, int rows, int cols) {
		String s = String.join(" ", sentence) + " ";
		int start = 0, l = s.length();
		for (int i = 0; i < rows; i++) {
			start += cols;
			if (s.charAt(start % l) == ' ') {
				start++;
			} else {
				while (start > 0 && s.charAt((start - 1) % l) != ' ') {
					start--;
				}
			}
		}

		return start / s.length();
	}
}
