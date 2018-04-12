package bloomberg;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/** @author: Hongquan Yu
 *  @date: Feb 24, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class AddDiffToList {
	List<List<Integer>> res = new ArrayList<>();
	
	public List<List<Integer>> getAll(List<Integer> list) {
		dfs(list, new HashSet<>(list));
		
		return res;
	}
	
	private void dfs(List<Integer> sub, Set<Integer> set) {
		System.out.print("Current list: ");
		System.out.println(sub);
		System.out.print("Current Set: ");
		System.out.println(set);
		System.out.println(" -  -------       --- --- -- -- -- - ");
		for (int i = 0; i < sub.size() - 1; i++) {
			int count = 0;
			
			// 当前的是否可以加进去元素
			int diff = Math.abs(sub.get(sub.size() - 1) - sub.get(i));
			if (!set.contains(diff)) {
				sub.add(diff);
				set.add(diff);
				count++;
				dfs(sub, set);
			}
			if (count == 0 && !res.contains(sub))
				res.add(sub);
				
		}
	}
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(30);
		list.add(5);
		
		AddDiffToList ad = new AddDiffToList();
		ad.print(ad.getAll(list));
	}
	
	private void print(List<List<Integer>> list) {
		for (int i = 0; i < list.size(); ++i) {
			for (int p : list.get(i)) {
				System.out.print(p + ", ");
			}
			System.out.println();
		}
	}
}
