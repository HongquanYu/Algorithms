package process;

/** @author: Hongquan Yu
 *  @date: Feb 6, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */

public class Process {
    int pid; // Process ID
    int bt; // Burst Time
    int art; // Arrival Time
     
    public Process(int pid, int bt, int art)
    {
        this.pid = pid;
        this.bt = bt;
        this.art = art;
    }
}
