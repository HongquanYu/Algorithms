package TopologicalSorting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/** @author: Hongquan Yu
 *  @date: Mar 31, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */


/** 拓扑排序，当前方案并没有在节点类中加入过多的内容 但是在图类中加入了边的集合adjNodes
 * 当前实现的思想是：
 * Graph 节点包含所有的节点，和以节点及他们的邻边的记录。排序过程就是，从入度为 0 的节点
 * 开始遍历，遍历一个节点，删除其及所有和他相连的边，直到入度为 0 的集合为空。如果结束后节点
 * 集合还有节点，说明含有环！ */

public class TopoSort {

	private static class Node {
		public Object val;
		public int pathIn = 0; // 入链路数量
		public Node(Object val) {
			this.val = val;
		}
	}

	private static class Graph {
		// 图中节点的集合
		public Set<Node> vertices = new HashSet<Node>();
		// 相邻的节点，纪录边
		public Map<Node, Set<Node>> adjNodes = new HashMap<Node, Set<Node>>();

		// 将节点加入图中
		public boolean addNode(Node start, Node end) {
			if (!vertices.contains(start))
				vertices.add(start);
			
			if (!vertices.contains(end))
				vertices.add(end);
			
			if (adjNodes.containsKey(start) && adjNodes.get(start).contains(end))
				return false;
			
			if (adjNodes.containsKey(start)) {
				adjNodes.get(start).add(end);
			} else {
				Set<Node> temp = new HashSet<Node>();
				temp.add(end);
				adjNodes.put(start, temp);
			}
			end.pathIn++;
			
			return true;
		}
	}

	// Kahn算法
	private static class KahnTopo {
		private List<Node> result; // 用来存储结果集
		private Queue<Node> zeroIndegree; // 用来存储入度为0的顶点
		private Graph graph;

		// 构造函数，初始化
		public KahnTopo(Graph di) {
			this.graph = di;
			this.result = new ArrayList<Node>();
			this.zeroIndegree = new LinkedList<>();
			// 对入度为0的集合进行初始化
			for (Node iterator : this.graph.vertices)
				if (iterator.pathIn == 0) 
					this.zeroIndegree.add(iterator);
		}

		// 拓扑排序处理过程
		private void sort() {
			while (!zeroIndegree.isEmpty()) {
				Node v = zeroIndegree.poll();

				// 将当前顶点添加到结果集中
				result.add(v);

				// 没有多余的节点和边了
				if (this.graph.adjNodes.keySet().isEmpty())
					return;

				// 遍历由v引出的所有边
				for (Node w : this.graph.adjNodes.get(v)) {
					// 将该边从图中移除，通过减少边的数量来表示
					w.pathIn--;
					if (0 == w.pathIn) // 如果入度为0，那么加入入度为0的集合
						zeroIndegree.add(w);
				}
				this.graph.vertices.remove(v);
				this.graph.adjNodes.remove(v);
			}

			// 如果此时图中还存在边，那么说明图中含有环路
			if (!this.graph.vertices.isEmpty()) {
				throw new IllegalArgumentException("Has Cycle !");
			}
		}

		// 结果集
		public Iterable<Node> getResult() {
			return result;
		}
	}

	// 测试
	public static void main(String[] args) {
		Node A = new Node("A");
		Node B = new Node("B");
		Node C = new Node("C");
		Node D = new Node("D");
		Node E = new Node("E");
		Node F = new Node("F");

		Graph graph = new Graph();
		graph.addNode(A, B);
		graph.addNode(B, C);
		graph.addNode(B, D);
		graph.addNode(D, C);
		graph.addNode(E, C);
		graph.addNode(C, F);

		KahnTopo topo = new KahnTopo(graph);
		topo.sort();
		for (Node temp : topo.getResult()) {
			System.out.print(temp.val.toString() + "-->");
		}
	}
}
