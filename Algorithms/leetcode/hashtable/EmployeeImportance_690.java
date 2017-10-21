package hashtable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

public class EmployeeImportance_690 {
	public int getImportance(List<Employee> employees, int id) {
		int total = 0;
		Map<Integer, Employee> map = new HashMap<>();
		for (Employee employee : employees) {
			map.put(employee.id, employee);
		}
		Queue<Employee> queue = new LinkedList<>();
		queue.offer(map.get(id));
		while (!queue.isEmpty()) {
			Employee current = queue.poll();
			total += current.importance;
			for (int subordinate : current.subordinates) {
				queue.offer(map.get(subordinate));
			}
		}
		return total;
	}

	public int getImportance1(List<Employee> employees, int id) {
		Map<Integer, Employee> map = new HashMap<>();
		for (Employee employee : employees) {
			map.put(employee.id, employee);
		}
		return getImportanceHelper(map, id);
	}

	private int getImportanceHelper(Map<Integer, Employee> map, int rootId) {
		Employee root = map.get(rootId);
		int total = root.importance;
		for (int subordinate : root.subordinates) {
			total += getImportanceHelper(map, subordinate);
		}
		return total;
	}
	
	int total = 0;

	public int getImportance2(List<Employee> employees, int id) {
		Employee manager =
				employees.stream().filter(e -> e.id == id).collect(Collectors.toList()).get(0);
		total += manager.importance;
		manager.subordinates.forEach(subId -> getImportance(employees, subId));
		return total;
	}
	
    
    class Employee {
    		public int id;
    		public int importance;
    		public List<Integer> subordinates;
    }
}
