package respositories;

import models.Ticket;

import java.util.Map;
import java.util.TreeMap;

public class TicketRepository {
    private Map<Long,Ticket> tickets=new TreeMap<>();
    private long lastSaveID=0L;
    public Ticket saveTicket(Ticket ticket){
        ticket.setId(lastSaveID+1);
        lastSaveID+=1;
        tickets.put(lastSaveID,ticket);
        return ticket;
    }
}
