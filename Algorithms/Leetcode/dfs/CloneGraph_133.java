package dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Oct 17, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class CloneGraph_133 {
	
	/** DFS
	 * 使用 HashMap 来记录 label 和新建的 clone 节点
	 * 然后再给进行 neighbors 遍历 */
	
	private HashMap<Integer, UndirectedGraphNode> map = new HashMap<>();

	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		return clone(node);
	}

	private UndirectedGraphNode clone(UndirectedGraphNode node) {
		if (node == null)
			return null;

		if (map.containsKey(node.label))
			return map.get(node.label);
		
		UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
		map.put(clone.label, clone);
		
		for (UndirectedGraphNode neighbor : node.neighbors)
			clone.neighbors.add(clone(neighbor));
		
		return clone;
	}
	
	/** BFS */
	
	public UndirectedGraphNode cloneGraph2(UndirectedGraphNode node) {
		if (node == null)
			return null;

		UndirectedGraphNode newNode = new UndirectedGraphNode(node.label); 	// new node for return
		HashMap<Integer, UndirectedGraphNode> map = new HashMap<>(); 			// store visited nodes

		map.put(newNode.label, newNode);

		LinkedList<UndirectedGraphNode> queue = new LinkedList<>(); // **original** nodes need to be visited
		queue.add(node); // add first **original** node to queue
		
		// 对当前节点进行处理，然后对他的邻居
		while (!queue.isEmpty()) {
			UndirectedGraphNode poped = queue.pop();
			
			for (UndirectedGraphNode neighbor : poped.neighbors) {
				if (!map.containsKey(neighbor.label)) { // 没有访问过，加入队列并设访问标志
					map.put(neighbor.label, new UndirectedGraphNode(neighbor.label));
					queue.add(neighbor);
				}
				map.get(poped.label).neighbors.add(map.get(neighbor.label));		// 
			}
		}

		return newNode;
	}
	
	class UndirectedGraphNode {
		int label;
		List<UndirectedGraphNode> neighbors;

		UndirectedGraphNode(int x) {
			label = x;
			neighbors = new ArrayList<UndirectedGraphNode>();
		}
	};
}
