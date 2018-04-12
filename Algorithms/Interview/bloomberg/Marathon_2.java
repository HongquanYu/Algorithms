package bloomberg;

import java.util.HashMap;
import java.util.Map;

import List.DoublyLinkedList;

/** @author: Hongquan Yu
 *  @date: Feb 13, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class Marathon_2 {
	Map<Runner, Node> map = new HashMap<>();
	
	
	class Node {}
	
	class Runner {
		int id;
		String name;
		int pace;
		
		Runner(int id, String s, int p) {
			this.id = id;
			this.name = s;
			this.pace = p;
		}
	}
	
	class CheckPoint {
		int id;
		int checkPointDistance;
		
		public CheckPoint(int id, int dis) {
			this.id = id;
			this.checkPointDistance = dis;
		}
	}
}
