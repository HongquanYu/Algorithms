package bloomberg.designParkingLot;

/** @author: Hongquan Yu
 *  @date: Feb 22, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class ParkingSpot {
	private Vehicle vehicle;
	private VehicleSize spotSize;
	private int row;
	private int spotNumber;
	private Level level;

	public ParkingSpot(Level lvl, int r, int n, VehicleSize s) { }

	public boolean isAvailable() {
		return vehicle == null;
	}

	/* Check if the spot is big enough and is available */
	public boolean canFitVehicle(Vehicle vehicle) {
		return false;
	}

	/* Park vehicle in this spot. */
	public boolean park(Vehicle v) {
		return false;
	}

	public int getRow() {
		return row;
	}
	public int getSpotNumber() {
		return spotNumber;
	}

	/*
	 * Remove vehicle from spot, and notify level that a new spot is available
	 */
	public void removeVehicle() {
	}
}
