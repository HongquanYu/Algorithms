package design;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ZigzagIterator_281 {
	LinkedList<Iterator> list;

	public ZigzagIterator_281(List<Integer> v1, List<Integer> v2) {
        list = new LinkedList<Iterator>();
        if(!v1.isEmpty()) list.add(v1.iterator());
        if(!v2.isEmpty()) list.add(v2.iterator());
    }

	public int next() {
		Iterator poll = list.remove();
		int result = (Integer) poll.next();
		if (poll.hasNext())
			list.add(poll);
		return result;
	}

	public boolean hasNext() {
		return !list.isEmpty();
	}
}
