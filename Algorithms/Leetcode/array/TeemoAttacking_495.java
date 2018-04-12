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
	
    public int findPoisonedDuration2(int[] timeSeries, int duration) {
        int total = 0;
        
        if (timeSeries == null || timeSeries.length == 0)
            return total;
        
        for (int i = 0; i < timeSeries.length - 1; ++i) {
            if (timeSeries[i] + duration <= timeSeries[i + 1])
                total += duration;
            else if (timeSeries[i] == timeSeries[i + 1])
                continue;
            else
                total += (timeSeries[i + 1] - timeSeries[i]);
        }
        
        return total + duration;
    }
}
