package bloomberg.designParkingLot;

/** @author: Hongquan Yu
 *  @date: Feb 22, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */

public class Motorcycle extends Vehicle {
	public Motorcycle() {
		spotsNeeded = 1;
		size = VehicleSize.Motorcycle;
	}
	
	public boolean canFitinSpot(ParkingSpot spot) {
		return false;
	}
}
