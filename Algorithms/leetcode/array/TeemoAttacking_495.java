package array;

public class TeemoAttacking_495 {
	public int findPoisonedDuration(int[] timeSeries, int duration) {
		int time = 0;
		int until = -1;

		for (int i = 0; i < timeSeries.length; ++i) {
			int t = timeSeries[i] + duration - 1;

			if (until < timeSeries[i])
				time += duration;
			else
				time += (t - until);

			until = t;
		}

		return time;
	}
}
