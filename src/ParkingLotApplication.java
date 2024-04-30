import controllers.TicketController;
import respositories.GateRepository;
import respositories.ParkingLotRepository;
import respositories.TicketRepository;
import respositories.VehicleRepository;
import services.TicketService;
import strategies.spotAssignmentStrategies.RandomSpotAssignmentStrategy;
import strategies.spotAssignmentStrategies.SpotAssignmentStrategy;

public class ParkingLotApplication {
    public static void main(String[] args) {

        GateRepository gateRepository=new GateRepository();
        ParkingLotRepository parkingLotRepository=new ParkingLotRepository();
        TicketRepository ticketRepository=new TicketRepository();
        VehicleRepository vehicleRepository=new VehicleRepository();
        SpotAssignmentStrategy spotAssignmentStrategy=new RandomSpotAssignmentStrategy();

        TicketService ticketService=new TicketService(gateRepository,vehicleRepository,spotAssignmentStrategy,
                ticketRepository,
                parkingLotRepository);
        TicketController ticketController=new TicketController(ticketService);

        System.out.println("Hello Parking lot started the application ");
    }
}