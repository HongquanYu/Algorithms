package string;

public class LengthLongestPath {
	public static int lengthLongestPath(String input) {
		String[] paths = input.split("\n");
		show(paths);
		int[] stack = new int[paths.length + 1];
		int maxLen = 0;
		for (String s : paths) {
			int lev = s.lastIndexOf("\t") + 1;
			System.out.println("lev: " + lev);
			int curLen = stack[lev + 1] = stack[lev] + s.length() - lev + 1;
			System.out.println("curLen: " + curLen);
			if (s.contains("."))
				maxLen = Math.max(maxLen, curLen - 1);
		}
		return maxLen;
	}

	private static void show(String[] s) {
		for (String ss : s) {
			System.out.println("---" + "length" + ss.length() + ": " + ss);
		}
	}

	public static void main(String[] args) {
		String input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
		System.out.println(lengthLongestPath(input));
	}

}
