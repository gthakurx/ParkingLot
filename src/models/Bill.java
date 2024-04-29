package models;

import java.util.Date;

public class Bill extends BaseModel {
    private Date exitTime;
    private Ticket ticket;
    private Operator billGeneratedOperator;
    private int amount;// this Needs to be Integer since

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Operator getBillGeneratedOperator() {
        return billGeneratedOperator;
    }

    public void setBillGeneratedOperator(Operator billGeneratedOperator) {
        this.billGeneratedOperator = billGeneratedOperator;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Gate getBillGeneratedGate() {
        return billGeneratedGate;
    }

    public void setBillGeneratedGate(Gate billGeneratedGate) {
        this.billGeneratedGate = billGeneratedGate;
    }

    public String getOnlinePaymentLink() {
        return onlinePaymentLink;
    }

    public void setOnlinePaymentLink(String onlinePaymentLink) {
        this.onlinePaymentLink = onlinePaymentLink;
    }

    //float always store in approximation , they dont store
    //exavct value .
    private Gate billGeneratedGate;
    private String onlinePaymentLink;
}
