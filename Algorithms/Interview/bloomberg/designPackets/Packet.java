package bloomberg.designPackets;

/** @author: Hongquan Yu
 *  @date: Feb 23, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class Packet {
	int seq;
	int data;
	
	public Packet(int s, int d) {
		this.seq = s;
		this.data = d;
	}
}
