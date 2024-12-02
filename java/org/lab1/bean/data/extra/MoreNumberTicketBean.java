package org.lab1.bean.data.extra;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.lab1.data.Actions;

import lombok.Data;
@SuppressWarnings("deprecation")
@ManagedBean(name = "moreNumberTicketBean")
@SessionScoped
@Data
public class MoreNumberTicketBean {
    private int minNumber;
    private Integer objectCount;
    public void count() {
        this.objectCount = Actions.getNumberOfMoreNumberTickets(minNumber);
    }
}
