package process;

/** @author: Hongquan Yu
 *  @date: Feb 6, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class SJBScheduling {
	
	// Method to find the waiting time for all processes
	static void findWaitingTime(Process proc[], int n, int wt[]) {
		int rt[] = new int[n];

		for (int i = 0; i < n; i++)
			rt[i] = proc[i].bt;

		int complete = 0, t = 0, minm = Integer.MAX_VALUE;
		int shortest = 0, finish_time;
		boolean check = false;

		while (complete != n) {
			for (int j = 0; j < n; j++) {
				if ((proc[j].art <= t) && (rt[j] < minm) && rt[j] > 0) {
					minm = rt[j];
					shortest = j;
					check = true;
				}
			}

			if (check == false) {
				t++;
				continue;
			}

			rt[shortest]--;

			minm = rt[shortest];
			if (minm == 0)
				minm = Integer.MAX_VALUE;

			if (rt[shortest] == 0) {
				complete++;
				finish_time = t + 1;
				wt[shortest] = finish_time - proc[shortest].bt - proc[shortest].art;

				if (wt[shortest] < 0)
					wt[shortest] = 0;
			}
			t++;
		}
	}

	// Method to calculate turn around time
	static void findTurnAroundTime(Process proc[], int n, int wt[], int tat[]) {
		for (int i = 0; i < n; i++)
			tat[i] = proc[i].bt + wt[i];
	}

	// Method to calculate average time
	static void findavgTime(Process proc[], int n) {
		int wt[] = new int[n], tat[] = new int[n];
		int total_wt = 0, total_tat = 0;

		findWaitingTime(proc, n, wt);

		findTurnAroundTime(proc, n, wt, tat);

		System.out.println("Processes " + " Burst time " + " Waiting time " + " Turn around time");

		for (int i = 0; i < n; i++) {
			total_wt = total_wt + wt[i];
			total_tat = total_tat + tat[i];
			System.out.println(" " + proc[i].pid + "\t\t" + proc[i].bt + "\t\t "
					+ wt[i] + "\t\t" + tat[i]);
		}

		System.out.println( "Average waiting time = " + (float) total_wt / (float) n);
		System.out.println( "Average turn around time = " + (float) total_tat / (float) n);
	}

	// Driver Method
	public static void main(String[] args) {
		Process proc[] = {new Process(1, 6, 1), new Process(2, 8, 1),
				new Process(3, 7, 2), new Process(4, 3, 3)};

		findavgTime(proc, proc.length);
	}
}