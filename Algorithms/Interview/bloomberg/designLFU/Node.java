package bloomberg.designLFU;

import java.util.LinkedHashSet;

/** @author: Hongquan Yu
 *  @date: Feb 26, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class Node {
    public int frequency;				// 频度
    public LinkedHashSet<Integer> keys;	// 
    public Node prev, next;				// 双向链表
    
    public Node(int count) {
        this.frequency = count;
        keys = new LinkedHashSet<Integer>();
        prev = next = null;
    }
}
