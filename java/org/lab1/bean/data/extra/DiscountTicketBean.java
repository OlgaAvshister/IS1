package org.lab1.bean.data.extra;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.lab1.data.Actions;

import lombok.Data;
@SuppressWarnings("deprecation")
@ManagedBean(name = "discountTicketBean")
@SessionScoped
@Data
public class DiscountTicketBean {
    private Integer ticketId;
    private Integer discountPercentage;
    private String newTicketDetails; 

    public void createNewTicket() {
        Actions.copyDiscountTicket(ticketId, discountPercentage); // вызываем Actions для копирования билета с новой скидкой
        // Формируем строку для отображения в результате
        this.newTicketDetails = "Создан новый билет с ID: " + ticketId + " и скидкой: " + discountPercentage + "%";
    }
}
