package segmentTree;

/** @author: Hongquan Yu
 *  @date: Mar 26, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class Test {
	public static void main(String[] args) {
		SegmentTree st = new SegmentTree();
		
		st.build(6, 20);
		System.out.println(st.preOrder().toString());
	}
}
