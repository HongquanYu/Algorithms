package uncategorized;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Feb 11, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */

public class LexicographicalNumbers_386 {
	
	/** Java DFS Solution 
	 *  观察前 500 个的片段，我们可以得出一些结论：
	 *  每一个数，我们会进行对他 10 次遍历，并对他的十倍也进行遍历，当然每一次
	 *  迭代都得检查是否是处于有效 range 之内的
	 *  
	 * 	1,  10, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 
    	 *		11, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 
    	 *		12, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 
    	 *		13, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 
     *		14, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 
     * 		15, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 
     * 		16, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 
     * 		17, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 
     *		18, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 
    	 *		19, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 

	 *	2,  20, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 
    	 *		21, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 
    	 *		22, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 
    	 *		23, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 
    	 *		24, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 
    	 *		25, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 
    	 *		26, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 
    	 *		27, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 
    	 *		28, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 
    	 *		29, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 

	 *	3,  30, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 
    	 *		31, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 
    	 *		32, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329 .........
	 * */
	
	public static List<Integer> lexicalOrder(int N) {
		List<Integer> res = new ArrayList<>();
		for (int i = 1; i < 10; ++i)
			dfs(i, N, res);
		return res;
	}

	public static void dfs(int cur, int N, List<Integer> res) {
		if (cur <= N) {
			res.add(cur);	// 添加当前数到集合里
			for (int i = 0; i < 10; ++i) {	// 
				if (10 * cur + i <= N)
					dfs(10 * cur + i, N, res);
			}
		}
	}
	
	/** 十分巧妙的解法：basic idea is to find the next number to add！
	 *  下一个数有一种可能的情况（按照优先顺序来排列）：
	 *  1，当前位的十倍，即在后面添个0，
	 *  2，或者是自然顺位后一位
	 *  3，或者是需要在最高位上进1
	 *  
	 * 	Take 45 for example: 
	 * 
	 *  if the current number is 45, the next one will be 
	 *  	-	450 	(450 == 45 * 10)	(if 450 <= n), 	or 
	 *  	-	46 	(46 == 45 + 1) 	(if 46 <= n) 	or 
	 *  	-	5 	(5 == 45 / 10 + 1)	(5 is less than 45 so it is for sure less than n). 
	 *  
	 *  We should also consider 
	 *  	-	n = 600, and the current number = 499. 
	 *  the next number is 5 because there are all ‘9’s after ‘4’ in “499” so we should 
	 *  divide 499 by 10 until the last digit is not “9”. 
	 *  
	 *  It is like a tree, and we are easy to get a sibling, a left most child and the 
	 *  parent of any node. */
	
	public List<Integer> lexicalOrder2(int n) {
		List<Integer> list = new ArrayList<>(n);
		int curr = 1;
		
		for (int i = 1; i <= n; i++) {	// 遍历所有数
			list.add(curr);
			if (curr * 10 <= n)			// 看当前数后面添个0是不是存在，若存在就添加
				curr *= 10;
			else if (curr % 10 != 9 && curr + 1 <= n)	// 不是要进位并且下一个是存在的
				curr++;
			else {						// 最高位需要进1，先得到最高位
				while ((curr / 10) % 10 == 9)
					curr /= 10;
				curr = curr / 10 + 1;
			}
		}
		
		return list;
	}
	
	public static void main(String[] args) {
		System.out.println(lexicalOrder(5));
	}
}
