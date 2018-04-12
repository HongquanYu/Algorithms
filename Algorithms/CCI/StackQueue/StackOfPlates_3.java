package StackQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/** @author: Hongquan Yu
 *  @date: Jan 31, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class StackOfPlates_3 {
	private List<Stack<Integer>> list;
	private int capacity;
	private int index = -1;
	
	public StackOfPlates_3() {
		list = new ArrayList<>();
	}
	public StackOfPlates_3(int cap) {
		this.capacity = cap;
		list = new ArrayList<>();
	}
	
	public void push(int value) {
		int s = size();
		if (s == 0 || s % this.capacity == 0)
			list.add(++index, new Stack<>());
		list.get(index).add(value);
	}
	
	public int size() {
		if (index == -1)
			return 0;
		return index * this.capacity + this.list.get(index).size();
	}
	
	public int pop() {
		int res = Integer.MIN_VALUE;
		if (size() > 0) {
			res = list.get(index).pop();
			if (list.get(index).size() == 0)
				list.remove(index--);
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		StackOfPlates_3 sp = new StackOfPlates_3(3);
		System.out.println("Before input, size: " + sp.list.size());
		sp.push(2);
		System.out.println("input 1, size: " + sp.list.size());
		sp.push(3);
		sp.push(4);
		System.out.println("input 3, size: " + sp.list.size());
		sp.push(5);
		System.out.println("input 4, size: " + sp.list.size());
		
		System.out.println(sp.pop());
		System.out.println("Poped one: " + sp.list.size());
		System.out.println(sp.pop());
		System.out.println(sp.pop());
		System.out.println(sp.pop());
		System.out.println("After remove all: " + sp.list.size());
		System.out.println(sp.pop());
		
		for (int i = 0; i < 20; ++i)
			sp.push(i);
		System.out.println("Size: " + sp.list.size());
		System.out.println("ele count: " + sp.size());
	}
}
