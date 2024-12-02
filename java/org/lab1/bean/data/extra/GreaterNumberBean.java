package org.lab1.bean.data.extra;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.lab1.data.Actions;
import org.lab1.data.entity.Ticket;

import lombok.Data;

@SuppressWarnings("deprecation")
@ManagedBean(name = "greaterNumberBean")
@SessionScoped
@Data
public class GreaterNumberBean {

    private int minNumber = 1000;
    public List<Ticket> getTickets() {
        return Actions.getTicketsWithGreaterNumber(minNumber);
    }
}
