package design;

import java.util.Iterator;
import java.util.List;

public class Flatten2DVector_251 {
	private Iterator<List<Integer>> i;
	private Iterator<Integer> j;

	public Flatten2DVector_251(List<List<Integer>> vec2d) {
		i = vec2d.iterator();
	}

	public int next() {
		hasNext();
		return j.next();
	}

	public boolean hasNext() {
		while ((j == null || !j.hasNext()) && i.hasNext())
			j = i.next().iterator();
		return j != null && j.hasNext();
	}
}
