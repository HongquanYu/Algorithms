package bloomberg.designParkingLot;

/** @author: Hongquan Yu
 *  @date: Feb 22, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class Car extends Vehicle {
	public Car() {
		spotsNeeded = 1;
		size = VehicleSize.Compact;
	}

	/** Checks if the spot is a Compact or a Large. */
	
	public boolean canFitinSpot(ParkingSpot spot) {
		return false;
	}
}
