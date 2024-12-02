package org.lab1.bean.data.extra;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.lab1.data.Actions;

import lombok.Data;
@SuppressWarnings("deprecation")
@ManagedBean(name = "lessVenueTicketBean")
@SessionScoped
@Data
public class LessVenueTicketBean {
    private int maxVenue;
    private Integer objectCount;
    public void count() {
        this.objectCount = Actions.getNumberOfLessVenueTickets(maxVenue);
    }
}
