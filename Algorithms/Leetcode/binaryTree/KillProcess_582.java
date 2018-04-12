package binaryTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 582. Kill Process
 * 
 * Given n processes, each process has a unique PID (process id) and its PPID
 * (parent process id).
 * 
 * Each process only has one parent process, but may have one or more children
 * processes. This is just like a tree structure. Only one process has PPID that
 * is 0, which means this process has no parent process. All the PIDs will be
 * distinct positive integers.
 * 
 * We use two list of integers to represent a list of processes, where the first
 * list contains PID for each process and the second list contains the
 * corresponding PPID.
 * 
 * Now given the two lists, and a PID representing a process you want to kill,
 * return a list of PIDs of processes that will be killed in the end. You should
 * assume that when a process is killed, all its children processes will be
 * killed. No order is required for the final answer.
 * 
 * Note: The given kill id is guaranteed to be one of the given PIDs. n >= 1. */

public class KillProcess_582 {
	
	/** The solution cases TLE， Time: N^N, Space: N^N. DFS. 遍历process，看他的父列表要是*/
	
	public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
		List<Integer> res = new LinkedList<>();
		res.add(kill);

		for (int i = 0; i < pid.size(); ++i) {		// 遍历当前process list
			if (ppid.get(i) == kill) {		// 当前有父process是kill
				int t = pid.get(i);
				res.add(t);
				res.addAll(killProcess(pid, ppid, t));
			}
		}

		return res;
	}
    
    /** Tree Simulation
     *  Time: N, Space: N. 
     */
    
	private class Node {
		int val;
		List<Node> children = new ArrayList<>();
	}

	public List<Integer> killProcess2(List<Integer> pid, List<Integer> ppid, int kill) {
		
		HashMap<Integer, Node> map = new HashMap<>();	// pid - it's node mapping
		
		for (int id : pid) {		
			Node node = new Node();
			node.val = id;
			map.put(id, node);
		}
		
		for (int i = 0; i < ppid.size(); i++) {
			if (ppid.get(i) > 0) {						// for every non-root node
				Node par = map.get(ppid.get(i));
				par.children.add(map.get(pid.get(i)));	// children list
			}
		}
		
		List<Integer> l = new ArrayList<>();
		l.add(kill);
		getAllChildren(map.get(kill), l);
		
		return l;
	}

	public void getAllChildren(Node pn, List<Integer> l) {
		for (Node n : pn.children) {
			l.add(n.val);
			getAllChildren(n, l);
		}
	}
	
	/**  HashMap + Depth First Search  */
	
	public List<Integer> killProcess3(List<Integer> pid, List<Integer> ppid, int kill) {
		
		HashMap<Integer, List<Integer>> map = new HashMap<>();	// pid - 和他的子孙们的列表
		
		for (int i = 0; i < ppid.size(); i++) {	// 遍历父节点表
			if (ppid.get(i) > 0) {		// 不是根节点
				List<Integer> l = map.getOrDefault(ppid.get(i), new ArrayList<Integer>());
				l.add(pid.get(i));		// 找到他的孩子
				map.put(ppid.get(i), l);
			}
		}
		List<Integer> res = new ArrayList<>();
		res.add(kill);
		dfs(map, res, kill);
		
		return res;
	}

	public void dfs(HashMap<Integer, List<Integer>> map, List<Integer> l, int kill) {
		if (map.containsKey(kill))	// 对每一个存在的节点， dfs找到他的所有后代
			for (int id : map.get(kill)) {
				l.add(id);
				dfs(map, l, id);
			}
	}
    
    /** HashMap + Breadth First Search */
    
	public List<Integer> killProcess4(List<Integer> pid, List<Integer> ppid, int kill) {
		
		HashMap<Integer, List<Integer>> map = new HashMap<>();
		
		for (int i = 0; i < ppid.size(); i++) {
			if (ppid.get(i) > 0) {
				List<Integer> l = map.getOrDefault(ppid.get(i), new ArrayList<Integer>());
				l.add(pid.get(i));
				map.put(ppid.get(i), l);
			}
		}
		
		Queue<Integer> queue = new LinkedList<>();
		List<Integer> l = new ArrayList<>();
		queue.add(kill);
		
		while (!queue.isEmpty()) {
			int r = queue.remove();
			l.add(r);
			if (map.containsKey(r))
				for (int id : map.get(r))
					queue.add(id);
		}
		
		return l;
	}
}
