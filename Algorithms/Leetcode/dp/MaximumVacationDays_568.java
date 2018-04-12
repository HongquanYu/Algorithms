package dp;

import java.util.Arrays;

public class MaximumVacationDays_568 {
	public int maxVacationDays(int[][] flights, int[][] days) {
		if (days.length == 0 || flights.length == 0)
			return 0;
		int[][] dp = new int[days.length][days[0].length + 1];
		for (int week = days[0].length - 1; week >= 0; week--) {
			for (int cur_city = 0; cur_city < days.length; cur_city++) {
				dp[cur_city][week] = days[cur_city][week] + dp[cur_city][week + 1];
				for (int dest_city = 0; dest_city < days.length; dest_city++) {
					if (flights[cur_city][dest_city] == 1) {
						dp[cur_city][week] =
								Math.max(days[dest_city][week] + dp[dest_city][week + 1],
										dp[cur_city][week]);
					}
				}
			}
		}
		return dp[0][0];
	}
	
	/* DFS */
	int max = 0, N = 0, K = 0;

	public int maxVacationDays1(int[][] flights, int[][] days) {
		N = flights.length;
		K = days[0].length;
		dfs(flights, days, 0, 0, 0);

		return max;
	}

	private void dfs(int[][] f, int[][] d, int curr, int week, int sum) {
		if (week == K) {
			max = Math.max(max, sum);
			return;
		}

		for (int dest = 0; dest < N; dest++) {
			if (curr == dest || f[curr][dest] == 1) {
				dfs(f, d, dest, week + 1, sum + d[dest][week]);
			}
		}
	}
	
	/* DP */
	
	public int maxVacationDays2(int[][] flights, int[][] days) {
		int N = flights.length;
		int K = days[0].length;
		int[] dp = new int[N];
		Arrays.fill(dp, Integer.MIN_VALUE);
		dp[0] = 0;

		for (int i = 0; i < K; i++) {
			int[] temp = new int[N];
			Arrays.fill(temp, Integer.MIN_VALUE);
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if (j == k || flights[k][j] == 1) {
						temp[j] = Math.max(temp[j], dp[k] + days[j][i]);
					}
				}
			}
			dp = temp;
		}

		int max = 0;
		for (int v : dp) {
			max = Math.max(max, v);
		}

		return max;
	}
}
