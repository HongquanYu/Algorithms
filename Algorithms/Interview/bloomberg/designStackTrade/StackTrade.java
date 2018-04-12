package bloomberg.designStackTrade;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/** @author: Hongquan Yu
 *  @date: Feb 26, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class StackTrade {
	
	private int stackNumber;
	Map<Integer, SingleStack> keyValue;	// stackID - SingleStack;
	Map<Integer, LinkedHashSet<Integer>> freqKeys;
	Map<Integer, Integer> keyFreq;
	PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
	
	private int leastFreq;
	
	public StackTrade(int p) {
		stackNumber = p;
		keyValue = new HashMap<>();
	}
	
	public void buy(int sID, int price, int num) {
		SingleStack tmp = keyValue.get(sID);
		tmp.holdingNumber += num;
		tmp.totalPrice += (price * num);
		tmp.averagePrice = tmp.totalPrice / tmp.holdingNumber;
		tmp.exchangeNumber += num;
		
		keyValue.put(sID, tmp);
	}
	
	public void sell(int sID, int price, int num) {
		SingleStack tmp = keyValue.get(sID);
		tmp.holdingNumber -= num;
		tmp.totalPrice -= (price * num);
		tmp.averagePrice = tmp.totalPrice / tmp.holdingNumber;
		tmp.exchangeNumber += num;
		
		keyValue.put(sID, tmp);
	}
	
	public List<SingleStack> getTopK(int K) {
		List<SingleStack> res = new LinkedList<>();

		return res;
	}
}
