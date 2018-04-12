package bloomberg.designPackets;

import java.util.HashMap;

/** @author: Hongquan Yu
 *  @date: Feb 23, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */

/** 需要考虑的问题： storage， neg number，duplicate number etc
 *  重复怎办，要进入的Packet是已经发送过的怎么办
 *  Follow Up: Optimization time and Space! */

public class SendPackets {
	private HashMap<Integer, Packet> map = new HashMap<>();
	Packet outed = null;		// Last outed Packet
	Packet first = null;
	
	public boolean enter(Packet p) {
		if (p.seq >= 0 && (outed == null || p.seq > outed.seq)) {
			System.out.println("Entering Packet: " + p.seq);
			if (first == null)
				first = p;
			if (map.containsKey(p.seq)) 	map.replace(p.seq, p);
			else 						map.put(p.seq, p);
			
			while (outed == null || map.containsKey(outed.seq + 1))
				out();
			return true;
		}
		
		return false;
	}
	
	/** out one Packet */
	public Packet out() {
		Packet res = null;
		if (outed == null) {
			outed = first;
			res = map.remove(first.seq);
		} else if (map.containsKey(outed.seq + 1)) {
			outed = map.remove(outed.seq + 1);
			res = outed;
		}
		System.out.println("---Outing Packet: " + res.seq);
		
		return null;
	}
	
	public static void main(String[] args) {
		SendPackets s = new SendPackets();
		
		Packet[] pp = new Packet[9];
		pp[0] = new Packet(0, 0);
		pp[1] = new Packet(1, 0);
		pp[2] = new Packet(2, 0);
		pp[3] = new Packet(4, 0);
		pp[4] = new Packet(5, 0);
		pp[5] = new Packet(8, 0);
		pp[6] = new Packet(3, 0);
		pp[7] = new Packet(6, 0);
		pp[8] = new Packet(7, 0);
		
		for (int i = 0; i < 9; ++i)
			s.enter(pp[i]);
	}
}
