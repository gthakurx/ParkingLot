package controllers;

import dtos.GenerateTicketRequestDto;
import dtos.GenerateTicketResponseDto;
import dtos.ResponseStatus;
import exceptions.InvalidGateException;
import exceptions.NoAvaiLableParkingSpot;
import models.Ticket;
import models.VehicleType;
import services.TicketService;

public class TicketController {

            // Here we are trying to understand what the controller should take as iput and
        //what controller should return
        // idea is to use DTOs data transfer object
        // use service class where business logic is there , and should service takes dto or return dto or not
        // we can have
// in order to generate ticket we can use this take vehicle object and gate information and generate ticket
    // but going forward if the ticket generation needs to return multiple other things then this ti tightly coupled
    //so this is not good idea
//        public void generateTicket(Vehicle vehicle, Gate gate){
//
//        }
    // second idea is to use the Data transfer object , DTO are used to establish communication between frontend and
    // backend , and provide only necessary details and get only necessary details from frontend .

    //Service should always be generic and not hard coded to controller since service will be used by multiple controller
    //so it should not hard coded to controller
    private TicketService ticketService;

    //Here we are injecting dependecy of ticketService and getting object of ticketService
    public TicketController(TicketService ticketService){
        this.ticketService=ticketService;
    }
    public GenerateTicketResponseDto generateTicket(GenerateTicketRequestDto requestDto) throws InvalidGateException {
        String vehicleNumber = requestDto.getVehicleNumber();
        VehicleType vehicleType=requestDto.getVehicleType();
        Long gateId= requestDto.getGateId();;

        Ticket ticket;
        GenerateTicketResponseDto responseDto=new GenerateTicketResponseDto();
        try{
            ticket=ticketService.generateTicket(gateId,vehicleType,vehicleNumber);
        }catch (InvalidGateException e){
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            responseDto.setMessage("Not valid Gate");
            return responseDto;
        }catch (NoAvaiLableParkingSpot noAvaiLableParkingSpot){
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            responseDto.setMessage("No available Parking lot");
            return responseDto;
        }


        responseDto.setTicketId(ticket.getId());
        responseDto.setOperatorName(ticket.getOperator().getName());
        responseDto.setSpotNumber(ticket.getParkingSpot().getParkingSpotNo());
        responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        return responseDto;

    }

}
