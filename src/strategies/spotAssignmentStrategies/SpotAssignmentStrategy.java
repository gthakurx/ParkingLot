package strategies.spotAssignmentStrategies;

import models.Gate;
import models.ParkingLot;
import models.ParkingSpot;
import models.VehicleType;

import java.util.Optional;

public interface SpotAssignmentStrategy {

//    /*
//    *send the complete Parking lot to the findSpot function as Parking lot will have all the infomation
//    * */

    public Optional<ParkingSpot> findSpot(VehicleType vehicleType, ParkingLot parkingLot, Gate gate);
}
