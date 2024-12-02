package org.lab1.bean.data.extra;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.lab1.data.Actions;

import lombok.Data;
@SuppressWarnings("deprecation")
@ManagedBean(name = "vipTicketBean")
@SessionScoped
@Data
public class VIPTicketBean {
    private Integer ticketId;
    private String newTicketDetails;
    public void copyTicket() {
        Actions.copyVIPTicket(ticketId);
        this.newTicketDetails = "Создан новый VIP билет с ID: " + ticketId + " с удвоенной ценой";
    }
}
