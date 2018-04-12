package bloomberg.designFindMedianFromDataStream;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

import design.FindMedianfromDataStream_295;

/** @author: Hongquan Yu
 *  @date: Mar 12, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class FindMedianfromDataStream {
	/** 通过维持一个大顶堆和一个小顶堆来实现！
	 *  大顶堆 smalls 里面存的是 较小的一半数，从大到小有序存放的一个 lower half，peek（）能够直接获得较小队列里最大值
	 *  小顶堆 larges 里存的是 较大的一半数！从小到大有序排列的堆 higher half, peek() 能够获得较大队列里的较小值
	 *  我们保持 larges 也就是 higher half 队列里的元素比 smalls 里相等或者多一个（odd or even）
	 *  
	 *  	添加操作：
	 *  		- 添加到 larges 里，然后调整 larges 和 smalls 的元素使其满足上面的定义
	 *  中位数查询操作：
	 *  		- 奇数个数 直接返回 larges 的头元素， 偶数个数直接返回 larges 和 smalls 的均值。
	 *  
	 *  Complexity: Time, add, O(lgN), getMedian, O(1). Space: N. */
	
	public Queue<Long> smalls, larges;

	public FindMedianfromDataStream() {
		larges = new PriorityQueue<>();								// 小顶堆
		smalls = new PriorityQueue<>(Collections.reverseOrder());		// 大顶堆
	}

	public void addNum(int num) {
		larges.add((long)num);
		smalls.add(larges.poll());
		
		if (larges.size() < smalls.size())
			larges.add(smalls.poll());
	}

	public double findMedian() {
		return larges.size() > smalls.size() ? 
				larges.peek() : 
					(larges.peek() + smalls.peek()) / 2.0;
	}
	
	public static void main(String[] args) {
		FindMedianfromDataStream_295 f = new FindMedianfromDataStream_295();
		
		f.addNum(9);
		System.out.println(f.findMedian());
		System.out.println("Small " + f.smalls.toString());
		System.out.println("Large " + f.larges.toString());
		f.addNum(6);
		System.out.println(f.findMedian());
		System.out.println("Small " + f.smalls.toString());
		System.out.println("Large " + f.larges.toString());
		f.addNum(14);
		System.out.println(f.findMedian());
		System.out.println("Small " + f.smalls.toString());
		System.out.println("Large " + f.larges.toString());
		
		f.addNum(77);
		System.out.println(f.findMedian());
		System.out.println("Small " + f.smalls.toString());
		System.out.println("Large " + f.larges.toString());
		f.addNum(2);
		System.out.println(f.findMedian());
		System.out.println("Small " + f.smalls.toString());
		System.out.println("Large " + f.larges.toString());
		f.addNum(100);
		System.out.println(f.findMedian());
		System.out.println("Small " + f.smalls.toString());
		System.out.println("Large " + f.larges.toString());
		f.addNum(63);
		System.out.println(f.findMedian());
		System.out.println("Small " + f.smalls.toString());
		System.out.println("Large " + f.larges.toString());
	}
}
