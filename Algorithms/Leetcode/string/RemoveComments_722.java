package string;

import java.util.ArrayList;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Mar 30, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class RemoveComments_722 {
	
	/** Parsing */
	
	public List<String> removeComments(String[] source) {
		boolean inBlock = false;
		StringBuilder newline = new StringBuilder();
		List<String> ans = new ArrayList<>();
		for (String line : source) {
			int i = 0;
			char[] chars = line.toCharArray();
			if (!inBlock)
				newline = new StringBuilder();
			while (i < line.length()) {
				if (!inBlock && i + 1 < line.length() && chars[i] == '/' && chars[i + 1] == '*') {
					inBlock = true;
					i++;
				} else if (inBlock && i + 1 < line.length() && chars[i] == '*' && chars[i + 1] == '/') {
					inBlock = false;
					i++;
				} else if (!inBlock && i + 1 < line.length() && chars[i] == '/' && chars[i + 1] == '/') {
					break;
				} else if (!inBlock) {
					newline.append(chars[i]);
				}
				i++;
			}
			if (!inBlock && newline.length() > 0) {
				ans.add(new String(newline));
			}
		}
		return ans;
	}
	
	/** Regular Expression Solution */
	
	public List<String> removeComments2(String[] source) {
		String[] s = String.join("\n", source).replaceAll("//.*|/\\*(.|\n)*?\\*/", "").split("\n");
		List<String> ans = new ArrayList<>();
		for (String str : s)
			if (!str.equals(""))
				ans.add(str);
		return ans;
	}
}
