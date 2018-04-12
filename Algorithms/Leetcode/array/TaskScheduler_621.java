package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/** @author: Hongquan Yu
 *  @date: Mar 28, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class TaskScheduler_621 {
	
	/**  */
	
	public int leastInterval(char[] tasks, int n) {

		int[] chars = new int[26];
		for (char t : tasks)
			chars[t - 'A']++;
		
		Arrays.sort(chars);
		int i = 25;
		while (i >= 0 && chars[i] == chars[25])
			i--;

		return Math.max(tasks.length, (chars[25] - 1) * (n + 1) + 25 - i);
	}
	
	/**  */
	
	public int leastInterval2(char[] tasks, int n) {
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < tasks.length; i++)
			map.put(tasks[i], map.getOrDefault(tasks[i], 0) + 1);
		
		PriorityQueue<Map.Entry<Character, Integer>> q = new PriorityQueue<>(
				(a, b) -> !a.getValue().equals(b.getValue()) ? b.getValue() - a.getValue() : a.getKey() - b.getKey());
		q.addAll(map.entrySet());

		int count = 0;
		while (!q.isEmpty()) {	// 对于每一个task，在下一次执行之前都得有 n 个其他的 task 或者 idle，所以是 n + 1
			int toBeExecuted = n + 1;
			List<Map.Entry<Character, Integer>> tmp = new ArrayList<>();
			
			while (toBeExecuted > 0 && !q.isEmpty()) {
				Map.Entry<Character, Integer> top = q.poll(); 	// most frequency task
				top.setValue(top.getValue() - 1); 	// decrease frequency, meaning it got executed
				tmp.add(top); 	// collect task to add back to queue
				toBeExecuted--;
				count++; 		// successfully executed task
			}

			for (Map.Entry<Character, Integer> e : tmp) {	// 将还需要处理的 task 重新入 PQ
				if (e.getValue() > 0)
					q.add(e); 	// add valid tasks
			}

			if (q.isEmpty()) 	break;	// 处理完成，返回！
			count += toBeExecuted; 	// if k > 0, then it means we need to be idle
		}
		return count;
	}
	
	public static void main(String[] args) {
		char[] tmp = new char[] { 'A', 'A', 'A', 'A', 'B', 'B', 'B', 'E', 'E', 'F', 'F', 'G', 'G' };
		TaskScheduler_621 t = new TaskScheduler_621();
		
		System.out.println(t.leastInterval2(tmp, 3));
	}
}
