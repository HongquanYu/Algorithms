package bloomberg.designTrainStation;

import java.util.HashMap;
import java.util.Map;

/** @author: Hongquan Yu
 *  @date: Feb 26, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */

/** 人从A站上车，B站下车，问任意两个站之间的平均时间 */

public class TradinStation {
	private int stationNumber = 10;
	Map<StartEnd, Info> map = new HashMap<>();
	Map<Integer, Integer> peopleBoard = new HashMap<>();			// 人 从 哪一站上的车
	
	public TradinStation() {
		for (int i = 1; i <= stationNumber; ++i) {
			for (int j = i + 1; j <= stationNumber; ++j) {
				map.put(new StartEnd(i, j), new Info());
				map.put(new StartEnd(j, i), new Info());
			}
		}
	}
	
	public void enter(int Pid, int Sid, int time) {
		peopleBoard.put(Pid, Sid);
	}
	
	public void exit(int pid, int sid, int time) {
		int startID = peopleBoard.get(pid);
		StartEnd cur = new StartEnd(startID, sid);
		Info temp = map.get(cur);
		temp.peopleNumber++;
		temp.total += time;
		map.put(cur, temp);
	}
	
	public int getAverage(int startID, int endID) {
		Info t = map.get(new StartEnd(startID, endID));
		return t.total / t.peopleNumber;
	}
}
