package bloomberg.designParkingLot;

/** @author: Hongquan Yu
 *  @date: Feb 22, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class Bus extends Vehicle {
	public Bus() {
		spotsNeeded = 5;
		size = VehicleSize.Large;
	}

	/** Checks if the spot is a Large. Doesn't check num of spots */
	
	public boolean canFitinSpot(ParkingSpot spot) {
		return false;
	}
}
