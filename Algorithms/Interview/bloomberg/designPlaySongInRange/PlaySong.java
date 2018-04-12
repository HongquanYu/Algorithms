package bloomberg.designPlaySongInRange;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import List.DoublyLinkedList;

/** @author: Hongquan Yu
 *  @date: Feb 25, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class PlaySong {
	Map<String, Song> map = new HashMap<>();
	DoublyLinkedList<Song> list = new DoublyLinkedList<>();
	
	public void playsong(String name) {
		map.get(name).play();
	}
	
	public Map<String, Integer> getCountOn(Date start, Date end) {
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		cal.add(Calendar.YEAR, -1); 		// to get previous year add -1
		Date lastYesr = cal.getTime();
		
		if (start.compareTo(lastYesr) < 0 || end.compareTo(today) > 0) {
			new Error("Time has to be within one year from today!");
			return null;
		}
		Map<String, Integer> res = new HashMap<>();
		
		for (Song s : list) {
			if (s.time.compareTo(start) > 0 && s.time.compareTo(end) < 0)
				res.put(s.name, res.getOrDefault(s.name, 0) + s.playCount);
		}
		
		return res;
	}
	
	class Song {
		String name;
		Date time;
		int playCount;
		
		public Song(String name, Date time) {
			this.name = name;
			this.time = time;
			this.playCount = 0;
		}
		
		public void play() {
			System.out.println("Playing " + this.name);
		}
	}
}
