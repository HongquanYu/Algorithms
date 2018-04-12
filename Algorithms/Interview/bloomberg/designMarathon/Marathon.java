package bloomberg.designMarathon;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Feb 13, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */

/** 设计思想：
 * 1， 每一个 checkPoint 都维持一个 double linkedlist 来管理当前越过的 runner，
 * 2， Main Class 维持一个 CheckPoint 的 list，以维护管理 checkPoint
 * 3， 维持一个 从选手到 checkPoint 链表的 Map 以提供常数查询
 * 4， 维持一个 从
 * 5， 当一个 Runner 从一个 checkPoint 到 下一个 的时候
 * */

public class Marathon {
	Node<Runner> head = null;
	Node<Runner> tail = null;
	int checkNumber;
	int runnerNumber;
	LinkedList<CheckPoint> checkPoints;		// 检查点 list， 检查点排序
	HashMap<Integer, Node<Runner>> runnerNode;			// runnerID - 选手在 checkPoints 链表中的节点
	HashMap<Integer, Integer> runnerCheckPoint;	// runnerID - 第几个 checkPoint
	
	public Marathon() { }
	
	/** 实时更新 top k 的选手 */
	
	public List<Runner> topK(int k) {
		return getTopKRunner(k);
	}
	
	/** 生成一个 dashboard 显示 runner 现在的名次/实时更新排名表 */
	
	public List<Runner> dashboard() {
		return getTopKRunner(runnerNumber);
	}
	
	private List<Runner> getTopKRunner(int K) {
		List<Runner> res = new LinkedList<>();
		
		int cnt = 0;
		while (K > 0) {
			Node<Runner> ptr = checkPoints.get(cnt).head;
			while (ptr != null) {
				if (K == 0)
					return res;
				res.add(ptr.data);
				ptr = ptr.next;
				K--;
			}
			cnt++;
		}
		
		return res;
	}
	
	public void update(int runnerID, int cpID) {
		int runnerPos = runnerCheckPoint.get(runnerID);
		
		if (runnerPos <= 1 || runnerPos > checkNumber)
			return;

		Node<Runner> node = runnerNode.get(runnerID);
		
		checkPoints.get(runnerPos).removeRunner(node);
		checkPoints.get(runnerPos - 1).addRunner(node);
	}
}
