package bloomberg.designParkingLot;

/** @author: Hongquan Yu
 *  @date: Feb 22, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class Level {
	private int floor;		// 在第几楼
	private ParkingSpot[] spots;			// 这一个level的所有spots
	private int availableSpots = 0; 		// number of free spots
	private static final int SPOTS_PER_ROW = 10;		// 一排有多少个 spot

	public Level(int flr, int numberSpots) { }

	public int availableSpots() {
		return availableSpots;
	}

	/** Find a place to park this vehicle. Return false if failed. */
	
	public boolean parkVehicle(Vehicle vehicle) {
		return false;
	}

	/** Park a vehicle starting at the spot spotNumber, and continuing until
	 * vehicle.spotsNeeded. */
	
	private boolean parkStartingAtSpot(int num, Vehicle v) {
		return false;
	}

	/** Find a spot to park this vehicle. Return index of spot, or -1 on failure. */
	
	private int findAvailableSpots(Vehicle vehicle) {
		return 0;
	}

	/** When a car was removed from the spot, increment availableSpots */
	
	public void spotFreed() {
		availableSpots++;
	}
}
