package services;

import exceptions.InvalidGateException;
import exceptions.NoAvaiLableParkingSpot;
import models.*;
import respositories.GateRepository;
import respositories.ParkingLotRepository;
import respositories.TicketRepository;
import respositories.VehicleRepository;
import strategies.spotAssignmentStrategies.SpotAssignmentStrategy;

import java.util.Date;
import java.util.Optional;



public class TicketService {
    private GateRepository gateRepository;
    private VehicleRepository vehicleRepository;
    private SpotAssignmentStrategy spotAssignmentStrategy;
    private TicketRepository ticketRepository;
    private ParkingLotRepository parkingLotRepository;
    public TicketService(GateRepository gateRepository,
                         VehicleRepository vehicleRepository,
                         SpotAssignmentStrategy spotAssignmentStrategy,
                         TicketRepository ticketRepository,
                         ParkingLotRepository parkingLotRepository){
        this.gateRepository=gateRepository;
        this.vehicleRepository=vehicleRepository;
        this.spotAssignmentStrategy=spotAssignmentStrategy;
        this.ticketRepository=ticketRepository;
        this.parkingLotRepository=parkingLotRepository;
    }

    //controller will call the service generateTicket function using these parameter in the request
    public Ticket generateTicket(Long gateID, VehicleType vehicleType,String vehicleNumber) throws InvalidGateException, NoAvaiLableParkingSpot {

        /*
        * what all things required to Create Ticket Object and return it
        * Gate =get gate from the ID from DB , Else throw exception
        * Operator = from gate we can get the Operator
        * parkingSpot = we can get by strategy //here we need strategy
        * Vehicle = check if already in DB , if Yes get that else create and save in DB .
        * Ticket ticket=
        * here we can use Builder as well if the parameters are more in the arguments
        *
        * We want to get the ticket object , final goal
        *
        * Ticket ticket = something
        *
        *
        *
        * */
        Optional<Gate> gateOptional = gateRepository.findGateById(gateID); //what will happen if there is no Gate with gateID
        //it will retun Nothing .use of Optional

        //https://abseil.io/tips/123

        if(gateOptional.isEmpty()){
            throw new InvalidGateException();
        }

        Gate gate= gateOptional.get();
        Operator operator= gate.getOperator();
        Optional<Vehicle> vehicleOptional=vehicleRepository.findVehicleByNumber(vehicleNumber);
        Vehicle vehicle;
        if(vehicleOptional.isEmpty()){
            vehicle=new Vehicle();
            vehicle.setVehicleNumber(vehicleNumber);
            vehicle.setVehicleType(vehicleType);
            //here we have to save this vehicle
            vehicle=vehicleRepository.save(vehicle);
        }else{
            vehicle=vehicleOptional.get();
        }
        Optional<ParkingLot> parkingLotOptional=parkingLotRepository.getParkingLotOfGate(gate);
        ParkingLot parkingLot= parkingLotOptional.get();
        Optional<ParkingSpot> parkingSpotOptional=spotAssignmentStrategy.findSpot(vehicleType,
                parkingLot,gate);
        if(parkingSpotOptional.isEmpty()){
            throw new NoAvaiLableParkingSpot();
        }
        ParkingSpot parkingSpot=parkingSpotOptional.get();
        Ticket ticket=new Ticket();
        ticket.setParkingSpot(parkingSpot);
        ticket.setGate(gate);
        ticket.setEntryTime(new Date());
        ticket.setVehicle(vehicle);
        ticket.setOperator(operator);

        return ticketRepository.saveTicket(ticket);
    }
}
