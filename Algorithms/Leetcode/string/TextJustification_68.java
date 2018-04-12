package string;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class TextJustification_68 {
	public List<String> fullJustify(String[] words, int L) {
		List<String> result = new LinkedList<String>();

		// loop one time = formation of a line
		for (int i = 0, w; i < words.length; i = w) { // i = start index of word, w = end index of
														// word each line
			int len = -1; // length of words and their spaces each line
			for (w = i; w < words.length && len + words[w].length() + 1 <= L; w++)
				len += words[w].length() + 1;

			StringBuilder sb = new StringBuilder(words[i]); // initialized with first word
			int space = 1, extra = 0;

			if (w != i + 1 && w != words.length) { // not 1 char, not last line
				space = (L - len) / (w - i - 1) + 1; // how many space that can be used to insert
														// blank spaces
				extra = (L - len) % (w - i - 1); // extra spaces to make indentation
			}

			for (int j = i + 1; j < w; j++) { //
				for (int s = space; s > 0; s--)
					sb.append(' '); // add spaces to make indentation
				if (extra-- > 0)
					sb.append(' ');
				sb.append(words[j]);
			}

			int strLen = L - sb.length(); // compensate with spaces at the end of string
			while (strLen-- > 0)
				sb.append(' ');
			result.add(sb.toString());
		}

		return result;
	}
	
	public List<String> fullJustify2(String[] words, int L) {
		List<String> lines = new ArrayList<String>();

		int index = 0;
		while (index < words.length) {
			int count = words[index].length();
			int last = index + 1;
			while (last < words.length) {
				if (words[last].length() + count + 1 > L)
					break;
				count += words[last].length() + 1;
				last++;
			}

			StringBuilder builder = new StringBuilder();
			int diff = last - index - 1;

			// if last line or number of words in the line is 1, left-justified
			if (last == words.length || diff == 0) {
				for (int i = index; i < last; i++) {
					builder.append(words[i] + " ");
				}
				builder.deleteCharAt(builder.length() - 1);
				for (int i = builder.length(); i < L; i++) {
					builder.append(" ");
				}
			} else { // middle justified
				int spaces = (L - count) / diff;
				int r = (L - count) % diff;
				for (int i = index; i < last; i++) {
					builder.append(words[i]);
					if (i < last - 1) {
						for (int j = 0; j <= (spaces + ((i - index) < r ? 1 : 0)); j++)
							builder.append(" ");
					}
				}
			}
			lines.add(builder.toString());
			index = last;
		}

		return lines;
	}
}
