package array;

import java.util.LinkedList;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Mar 28, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class EmployeeFreeTime_759 {
	public List<Interval> employeeFreeTime(List<List<Interval>> working) {
		List<Interval> intermediate = new LinkedList<>(), merge = new LinkedList<>();
		List<Interval> res = new LinkedList<>();
		
		for (int i = 0; i < working.size(); ++i)
			for (int j = 0; j < working.get(i).size(); ++j)
				intermediate.add(working.get(i).get(j));
		
		intermediate.sort((a, b) -> a.start - b.start);
		
		int s = intermediate.get(0).start, e = intermediate.get(0).end;
		for (int i = 1; i < intermediate.size(); ++i) {
			if (intermediate.get(i).start <= e)
				e = Math.max(intermediate.get(i).end, e);
			else {
				merge.add(new Interval(s, e));
				s = intermediate.get(i).start;
				e = intermediate.get(i).end;
			}
		}
		merge.add(new Interval(s, e));
		
		for (int i = 1; i < merge.size(); ++i)
			res.add(new Interval(merge.get(i - 1).end, merge.get(i).start));
		
		return res;
	}
	
	private static void print(List<Interval> list) {
		List<String> strings = new LinkedList<>();
		for (Interval interval : list)
			strings.add(interval.toString());
		
		System.out.println(strings);
	}
	
	public static void main(String[] args) {
		List<List<Interval>> tmp = new LinkedList<>();
		List<Interval> sub = new LinkedList<>();
		sub.add(new Interval(1, 2));
		sub.add(new Interval(5, 6));
		tmp.add(sub);
		List<Interval> sub1 = new LinkedList<>();
		sub1.add(new Interval(1, 3));
		tmp.add(sub1);
		List<Interval> sub2 = new LinkedList<>();
		sub2.add(new Interval(4, 10));
		tmp.add(sub2);
		
		List<List<Interval>> tmp2 = new LinkedList<>();
		List<Interval> sub3 = new LinkedList<>();
		sub3.add(new Interval(1, 3));
		sub3.add(new Interval(6, 7));
		tmp2.add(sub3);
		List<Interval> sub4 = new LinkedList<>();
		sub4.add(new Interval(2, 4));
		tmp2.add(sub4);
		List<Interval> sub5 = new LinkedList<>();
		sub5.add(new Interval(2, 5));
		sub5.add(new Interval(9, 12));
		tmp2.add(sub5);
		
		EmployeeFreeTime_759 e = new EmployeeFreeTime_759();
		
		print(e.employeeFreeTime(tmp));
		print(e.employeeFreeTime(tmp2));
	}
}
