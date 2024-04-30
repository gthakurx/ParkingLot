package respositories;

import models.Gate;
import models.ParkingLot;
import models.VehicleType;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class ParkingLotRepository {

    private Map<Long,ParkingLot> parkinglots=new TreeMap<>();
    public Optional<ParkingLot> getParkingLotOfGate(Gate gate){
        for(ParkingLot parkingLot: parkinglots.values()){
            if(parkingLot.getGates().contains(gate)){
                return Optional.of(parkingLot);
            }
        }
        return Optional.empty();
    }
}
